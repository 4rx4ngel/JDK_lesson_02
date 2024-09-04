package server.repository;


public interface Repository {

    void saveInLog (String message);
    String getHistory();
}