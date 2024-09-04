package server.repository;

import java.io.FileReader;
import java.io.FileWriter;

public class FileStorage implements Repository{
    private static final String LOG_PATH = "src/server/repository/log.txt";


    @Override
    public void saveInLog(String message) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(message);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getHistory() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH)){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length(), stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}