package io.dropwizard.persistence.DAO;

import io.dropwizard.models.Klant;
import io.dropwizard.models.Project;
import io.dropwizard.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectDAO {

    private ConnectionPool pool;

    public ProjectDAO(){
        this.pool = new ConnectionPool("org.mariadb.jdbc.Driver","jdbc:mariadb://localhost:3306:/UrenregistratieDatabase", "root", "ipsen123");
    }

    public void add(Project project){
        try{
            Connection con = pool.checkout();
            PreparedStatement statement = con.prepareStatement("INSERT INTO project (project_naam, klant_ID) VALUES (?, ?)");
            statement.setString(1, project.getProjectNaam());


        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
