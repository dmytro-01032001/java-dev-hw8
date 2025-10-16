package hw8.model;

public class Queries {
    public static final String FIND_LONGEST_PROJECT = """
        SELECT 
            pp.id,
            pp.name,
            EXTRACT(YEAR FROM AGE(pp.FINISH_DATE, pp.START_DATE)) * 12 + EXTRACT(MONTH FROM AGE(pp.FINISH_DATE, pp.START_DATE)) as MONTH_COUNT,
            pp.START_DATE ,
            pp.FINISH_DATE
        FROM project pp
        WHERE EXTRACT(YEAR FROM AGE(pp.FINISH_DATE, pp.START_DATE)) * 12 + EXTRACT(MONTH FROM AGE(pp.FINISH_DATE, pp.START_DATE)) =
            (SELECT max(EXTRACT(YEAR FROM AGE(p.FINISH_DATE, p.START_DATE)) * 12 + EXTRACT(MONTH FROM AGE(p.FINISH_DATE, p.START_DATE)))
                FROM project p
            );
    """;
    public static final String FIND_MAX_PROJECTS_CLIENT = """
        SELECT c.id, c.name, COUNT(p.client_id) as PROJECT_COUNT FROM client c
        JOIN project p ON c.id = p.client_id
        GROUP BY c.id
        HAVING COUNT(p.client_id) = (
            SELECT MAX(cnt)
            FROM (
                SELECT COUNT(*) AS cnt
                FROM project
                GROUP BY client_id
            ) t
        );
    """;
    public static final String FIND_MAX_SALARY_WORKER = """
        SELECT name, salary
        FROM worker WHERE salary = (SELECT MAX(salary) from worker);
    """;
    public static final String FIND_YOUNGEST_ELDEST_WORKER = """
        SELECT 'YOUNGEST' as TYPE, name, birthday
        FROM worker WHERE (CURRENT_DATE::date - birthday::date) = (SELECT MIN(CURRENT_DATE::date - birthday::date) from worker)

        UNION ALL

        SELECT 'OLDEST' as TYPE, name, birthday
        FROM worker WHERE (CURRENT_DATE::date - birthday::date) = (SELECT MAX(CURRENT_DATE::date - birthday::date) from worker);
    """;
    public static final String PROJECT_PRICES = """
        SELECT p.name, (EXTRACT(YEAR FROM AGE(p.FINISH_DATE, p.START_DATE)) * 12 + 
            EXTRACT(MONTH FROM AGE(p.FINISH_DATE, p.START_DATE))) * SUM(w.salary) as price
        FROM project p
        JOIN project_worker pw ON p.id = pw.project_id
        JOIN worker w ON pw.worker_id = w.id
        GROUP BY p.name, p.finish_date, p.start_date
        ORDER BY price DESC;
    """;

    public static final String CREATE_WORKER_TABLE = """
        CREATE TABLE IF NOT EXISTS worker (
            ID SERIAL PRIMARY KEY,
            NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) BETWEEN 1 AND 1001),
            BIRTHDAY DATE CHECK (BIRTHDAY >= '1900-01-01'),
            LEVEL VARCHAR(10) NOT NULL,
            SALARY INT CHECK (SALARY BETWEEN 100 AND 100000),
            CONSTRAINT chk_level 
                CHECK (LEVEL IN ('Trainee', 'Junior', 'Middle', 'Senior'))
        );
    """;
    public static final String CREATE_CLIENT_TABLE = """
        CREATE TABLE IF NOT EXISTS client (
            ID SERIAL PRIMARY KEY,
            NAME VARCHAR NOT NULL CHECK (LENGTH(NAME) BETWEEN 1 AND 1001)
        );
    """;
    public static final String CREATE_PROJECT_TABLE = """
            CREATE TABLE IF NOT EXISTS project (
                ID SERIAL PRIMARY KEY,
                NAME VARCHAR NOT NULL CHECK (LENGTH(NAME) BETWEEN 1 AND 1001),
                CLIENT_ID INT NOT NULL,
                FOREIGN KEY(CLIENT_ID) REFERENCES client(ID),
                START_DATE DATE CHECK (START_DATE >= '1900-01-01'),
                FINISH_DATE DATE CHECK (FINISH_DATE >= '1900-01-01')
            );    
        """;
    public static final String CREATE_PROJECT_CLIENT_TABLE = """
            CREATE TABLE IF NOT EXISTS project_worker (
                PROJECT_ID INT NOT NULL,
                WORKER_ID INT NOT NULL,
                PRIMARY KEY (PROJECT_ID, WORKER_ID),
                FOREIGN KEY (PROJECT_ID) REFERENCES project(ID),
                FOREIGN KEY (WORKER_ID) REFERENCES worker(ID)
            );
        """;
}
