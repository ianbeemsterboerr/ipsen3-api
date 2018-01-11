package io.dropwizard.persistence.DAO;

import io.dropwizard.models.Project;
import io.dropwizard.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO extends DAO{

    public ProjectDAO(ConnectionPool pool){
        super(pool);
    }

    public void add(String projectName, int customerId){
        Connection con = pool.checkout();
        try{
            PreparedStatement statement = con.prepareStatement("INSERT INTO project (project_naam, klant_ID) VALUES (?, ?)");
            statement.setString(1, projectName);
            statement.setInt(2, customerId);


        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
    }

    public Project getProjectByCIdAndPName(String projectName, int customerId) {
        Connection con = pool.checkout();
        Project project = null;
        try{
            PreparedStatement getProject = con.prepareStatement("SELECT project_ID, project_naam, klant_ID FROM project WHERE klant_ID = (?) and project_naam = (?)");
            getProject.setInt(1,customerId);
            getProject.setString(2, projectName);
            ResultSet results = getProject.executeQuery();
            if (results.next()) {
                project = new Project(results.getInt("project_ID"), results.getString("project_naam"), results.getInt("klant_ID"));
            }else {
                System.out.println("niks");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
        return project;

    }

    public void addProject(String projectnaam){
        Connection con = pool.checkout();
        Project project = null;
        try {
            PreparedStatement addProject = con.prepareStatement("INSERT INTO project (project_naam), VALUE (?)");
            addProject.setString(1, projectnaam);
            addProject.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }

    }

    public List<Project> getProjectByID(int customerID) {
        Connection con = pool.checkout();
        List<Project> projects = null;
        try {
            PreparedStatement getProjects = con.prepareStatement("SELECT project_ID, project_naam, klant_ID FROM project WHERE klant_ID = (?)");
            getProjects.setInt(1, customerID);

            ResultSet results = getProjects.executeQuery();
            projects = new ArrayList<>();
            if (results != null) {
                while(results.next()) {
                    projects.add(new Project(results.getInt("project_ID"), results.getString("project_naam"), results.getInt("klant_ID")));
                    System.out.println(results.getString(2));
                }
            } else {
                System.out.println("geen projecten");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }

        return projects;
    }
}
