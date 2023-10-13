package Controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Proper {

    Properties properties = new Properties();
    String login;
    String password;
    String group;
    boolean log;
    boolean checkout;
    Logger logger = new Logger(new File("log.txt"),properties);
    public Proper(File file) {
        try {

            properties.load(new FileReader(file));
            login = properties.getProperty("login");
            password = properties.getProperty("password");
            group = properties.getProperty("group");
            log = Boolean.parseBoolean(properties.getProperty("log","false"));
            checkout = Boolean.parseBoolean(properties.getProperty("checkout","false"));
            logger.Log("Properties loading", Logger.Level.INFO);
            logger.Log(String.format("%s %s %s",login,password,group), Logger.Level.DEBUG);
            logger.Log(String.format("%s %s",log,checkout), Logger.Level.DEBUG);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            logger.Log(e.getMessage(), Logger.Level.ERROR);
        }

    }

    public Properties getProperties() {
        return properties;
    }
}
