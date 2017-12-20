package io.dropwizard.models;

public class Subject {

    private int subjectId;
    private String subjectName;
    private int projectId;

    Subject(int subjectId, String subjectName, int projectId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.projectId = projectId;
    }
}
