package hw8.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import hw8.Database;

public class DatabasePopulateService {
    private static Object[][] workers = {
        {"Олена Коваленко", "1990-03-15", "Trainee", 300},
        {"Іван Петренко", "1985-07-22", "Junior", 500},
        {"Марія Іванова", "1993-11-05", "Middle", 1000},
        {"Дмитро Шевченко", "1988-01-30", "Senior", 2000},
        {"Софія Бондаренко", "2000-06-18", "Trainee", 300},
        {"Андрій Мельник", "1991-12-09", "Junior", 500},
        {"Наталія Ткаченко", "1987-04-25", "Middle", 2000},
        {"Вікторія Поліщук", "1996-08-14", "Senior", 4000},
        {"Олександр Кравчук", "1992-02-28", "Trainee", 300},
        {"Анна Сидоренко", "1989-09-11", "Junior", 500},
        {"Руслан Лисенко", "1994-05-03", "Middle", 3000},
        {"Євген Козак", "1997-10-20", "Senior", 6000},
        {"Тетяна Грищенко", "1984-07-07", "Trainee", 300},
        {"Михайло Зінченко", "1999-12-30", "Junior", 500},
        {"Людмила Павленко", "1995-03-23", "Middle", 6000}
    };
    private static String[] clients = {
        "Alice Johnson",
        "Bob Smith",
        "Charlie Brown",
        "Diana Prince",
        "Edward Kenway",
        "Fiona Glenanne",
        "George Washington",
        "Hannah Baker",
        "Ian Shaw",
        "Jane Doe"
    };

    private static void fillWorkers(Connection conn){
        try (
            PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?)"
            )
        )
        {
            for (int i = 0; i < workers.length; i++){
                statement.setString(1, (String) workers[i][0]);
                statement.setString(2, (String) workers[i][1]);
                statement.setString(3, (String) workers[i][2]);
                statement.setInt(4, (int) workers[i][3]);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fillClients(Connection conn){
        try (
            PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO client (name) VALUES (?)"
            )
        )
        {
            for (int i = 0; i < clients.length; i++){
                statement.setString(1, clients[i]);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fillProjects(Connection conn){
        String[][] projectParams = {
            {"Project A", "2025-08-01", "2025-10-25", clients[0]},
            {"Project B", "2025-08-10", "2025-11-25", clients[1]},
            {"Project C", "2025-08-01", "2025-12-25", clients[2]},
            {"Project D", "2025-08-01", "2026-02-25", clients[3]},
            {"Project E", "2025-08-01", "2026-06-25", clients[4]},
            {"Project F", "2025-08-01", "2026-10-25", clients[5]},
            {"Project G", "2025-08-01", "2027-10-25", clients[6]},
            {"Project H", "2025-09-01", "2028-10-25", clients[7]}
        };
        try (
            PreparedStatement statement = conn.prepareStatement(
                """
                    INSERT INTO project (client_id, name, START_DATE, FINISH_DATE)
                        SELECT client.id, ?, ?::DATE, ?::DATE
                        FROM client WHERE client.name = ?
                """
            )
        )
        {
            for (int i = 0; i < projectParams.length; i++){
                statement.setString(1, projectParams[i][0]);
                statement.setString(2, projectParams[i][1]);
                statement.setString(3, projectParams[i][2]);
                statement.setString(4, projectParams[i][3]);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fillProjectworkerTable(Connection conn){
        String[][] params = {
            {(String) workers[2][0], (String) workers[12][0], (String) workers[14][0], clients[0]},
            {(String) workers[11][0], (String) workers[5][0], (String) workers[8][0], clients[1]},
            {(String) workers[3][0], (String) workers[13][0], (String) workers[11][0], clients[2]},
        };
        try (
            PreparedStatement statement = conn.prepareStatement(
                """
                    INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
                    SELECT p.id, w.id
                    FROM project p
                    JOIN client c ON p.client_id = c.id
                    JOIN worker w ON w.name IN (
                        ?,
                        ?,
                        ?
                    )
                    WHERE c.name = ?
                """
            )
        )
        {
            for (int i = 0; i < params.length; i++){
                statement.setString(1, params[i][0]);
                statement.setString(2, params[i][1]);
                statement.setString(3, params[i][2]);
                statement.setString(4, params[i][3]);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Connection conn = Database.getConnection();
        fillWorkers(conn);
        fillClients(conn);
        fillProjects(conn);
        fillProjectworkerTable(conn);
    }
}
