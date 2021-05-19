package licenta.backend.helpers;

import licenta.backend.model.Erole;

public class UserHelper {
public  String name;
public  String email;
public  String username;
public  String password;
public  Erole type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Erole getType() {
        return type;
    }

    public void setType(Erole type) {
        this.type = type;
    }
}
