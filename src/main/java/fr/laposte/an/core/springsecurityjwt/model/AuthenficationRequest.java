package fr.laposte.an.core.springsecurityjwt.model;

public class AuthenficationRequest {

    private String userName;
    private String password;

    public AuthenficationRequest() {
    }

    public AuthenficationRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
