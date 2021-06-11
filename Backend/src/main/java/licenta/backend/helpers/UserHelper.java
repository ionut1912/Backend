package licenta.backend.helpers;

import licenta.backend.model.Erole;

import java.util.Random;

public class UserHelper {
public  String name;
public  String email;
public  String username;
public  String password;
public String usercode;
public  Erole type;
public  String hotelreview;

    public String getHotelreview() {
        return hotelreview;
    }

    public void setHotelreview(String hotelreview) {
        this.hotelreview = hotelreview;
    }

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

    public String getUsercode() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() <=6) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
}
