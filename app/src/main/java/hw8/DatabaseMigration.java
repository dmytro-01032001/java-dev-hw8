package hw8;

import org.flywaydb.core.Flyway;

public class DatabaseMigration {
    public static void main(String[] args) {
        // Configure Flyway
        Flyway flyway = Flyway.configure()
                .dataSource(
                    PropertyReader.getUrl(),
                    PropertyReader.getUser(),
                    PropertyReader.getPassword()
                )
                .load();

        // Perform migration
        flyway.migrate();

        System.out.println("Database migration completed successfully!");
    }
}