package io.dropwizard.persistence.DAO;

import io.dropwizard.models.Subject;
import io.dropwizard.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDAO {

    private ConnectionPool pool;

    public SubjectDAO() {
        this.pool = new ConnectionPool("org.mariadb.jdbc.Driver","jdbc:mariadb://localhost:3306:/UrenregistratieDatabase", "root", "ipsen123");
    }


    public Subject getSubjectByPIDSName(int projectID, String subjectName) {
        Connection con = pool.checkout();
        Subject subject = null;
        try {
            PreparedStatement getSubject = con.prepareStatement("SELECT onderwerp_ID, onderwerp_naam, project_ID FROM onderwerp WHERE project_ID = (?) and onderwerp_naam = (?) ");
            getSubject.setInt(1, projectID);
            getSubject.setString(2, subjectName);
            ResultSet results = getSubject.executeQuery();
            if (results.next()) {
                subject = new Subject(results.getInt("onderwerp_ID"), results.getString("onderwerp_naam"), results.getInt("project_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
        return subject;
    }
}