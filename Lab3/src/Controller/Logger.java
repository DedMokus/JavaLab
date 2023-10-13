package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Logger {

    File log;
    FileWriter fw;
    Properties prop;

    public enum Level {
        FATAL("FATAL"),
        ERROR("ERROR"),
        WARN("WARN"),
        INFO("INFO"),
        DEBUG("DEBUG");

        private String title;

        Level(String title){
            this.title = title;
        }
        public String getTitle(){
            return title;
        }
    }

    public Logger(File file,Properties properties) {
        try {
            fw = new FileWriter(file, true);
            prop = properties;
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void Log(String log,Level level){
        boolean alllog = Boolean.parseBoolean(prop.getProperty("log"));
        try {
            if (alllog) {
                LocalTime time = LocalTime.now();
                System.out.printf("%s %s %s%n", time.format(DateTimeFormatter.ofPattern("H:mm:ss")), level.getTitle(), log);
                fw.write(String.format(time.format(DateTimeFormatter.ofPattern("H:mm:ss")), level.getTitle(), log));
                fw.flush();
            } else {
                if (level.ordinal() < 3) {
                    LocalTime time = LocalTime.now();
                    System.out.printf("%s %s %s%n", time.format(DateTimeFormatter.ofPattern("H:mm:ss")), level.getTitle(), log);
                    fw.write(String.format(time.format(DateTimeFormatter.ofPattern("H:mm:ss")), level.getTitle(), log));
                    fw.flush();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
