package hw8.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hw8.Database;
import hw8.model.LongestProject;
import hw8.model.MaxProjectCountClient;
import hw8.model.MaxSalaryWorker;
import hw8.model.OldestYoungestWorker;
import hw8.model.ProjectPrice;
import hw8.model.Queries;


public class DatabaseQueryService {
    public static List<LongestProject> findLongestProject() throws IOException{
        String query = Queries.FIND_LONGEST_PROJECT;
        Connection conn = Database.getConnection();
        List<LongestProject> projects = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                projects.add(new LongestProject(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("month_count")
                    )
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        };
        return projects;
    }

    public static List<MaxProjectCountClient> findMaxProjectsClient() throws IOException {
        String query = Queries.FIND_MAX_PROJECTS_CLIENT;
        Connection conn = Database.getConnection();
        List<MaxProjectCountClient> projects = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                projects.add(new MaxProjectCountClient(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("PROJECT_COUNT")
                    )
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public static List<MaxSalaryWorker> findMaxSalaryWorker() throws IOException {
        String query = Queries.FIND_MAX_SALARY_WORKER;
        Connection conn = Database.getConnection();
        List<MaxSalaryWorker> workers = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                workers.add(new MaxSalaryWorker(
                        result.getString("name"),
                        result.getInt("salary")
                    )
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    public static List<OldestYoungestWorker> findOldestYoungestWorker() throws IOException {
        String query = Queries.FIND_YOUNGEST_ELDEST_WORKER;
        Connection conn = Database.getConnection();
        List<OldestYoungestWorker> workers = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                workers.add(new OldestYoungestWorker(
                        result.getString("type"),
                        result.getString("name"),
                        result.getString("birthday")
                    )
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    public static List<ProjectPrice> getProjectPrices() throws IOException {
        String query = Queries.PROJECT_PRICES;
        Connection conn = Database.getConnection();
        List<ProjectPrice> projects = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                projects.add(new ProjectPrice(
                        result.getString("name"),
                        result.getInt("price")
                    )
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }
}
