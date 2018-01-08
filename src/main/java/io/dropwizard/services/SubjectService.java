package io.dropwizard.services;

import io.dropwizard.models.Subject;
import io.dropwizard.persistence.DAO.SubjectDAO;

import javax.inject.Inject;

public class SubjectService {

    SubjectDAO dao;
    @Inject
    SubjectService(SubjectDAO dao) {
        this.dao = dao;
    }

    public Subject getSubjectByPIDSName(int projectID, String subjectName) {
        return this.dao.getSubjectByPIDSName(projectID, subjectName);
    }
}
