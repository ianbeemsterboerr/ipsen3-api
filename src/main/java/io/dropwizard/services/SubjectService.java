package io.dropwizard.services;

import io.dropwizard.models.Subject;
import io.dropwizard.persistence.DAO.SubjectDAO;

public class SubjectService {

    SubjectDAO dao;
    public SubjectService(SubjectDAO dao) {
        this.dao = dao;
    }

    public Subject getSubjectByPIDSName(int projectID, String subjectName) {
        return this.dao.getSubjectByPIDSName(projectID, subjectName);
    }
}
