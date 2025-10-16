package hw8;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static Properties loadProp() {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("app.properties")) {

            Properties prop = new Properties();

            if(input == null) {
                System.out.println("Error: cannot read app.properties");
                return null;
            }

            prop.load(input);

            return prop;

        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    static String getUrl() {

        Properties prop = loadProp();

        return new StringBuilder("jdbc:postgresql://")
            .append(prop.getProperty("postgres.db.host"))
            .append(":")
            .append(prop.getProperty("postgres.db.port"))
            .append("/")
            .append(prop.getProperty("postgres.db.database"))
            .append("?allowPublicKeyRetrieval=true&useSSL=false")
            .toString();

    }

    static String getUser() {
            Properties prop = loadProp();
            return prop.getProperty("postgres.db.user");
    }

    static String getPassword() {
        Properties prop = loadProp();
        return prop.getProperty("postgres.db.password");
    }
}
