package vn.edu.hcmuaf.fit.bean;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1;
    private int id;
    private String username;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int level;
    private String token;

    public User(int id, String username, String name, String address, String phone, String email, int level, String token) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.level = level;
        this.token = token;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", level='" + level + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public boolean available() {

        return this.level < 0 ? false : true;
    }
    public boolean isAdmin(){
        return this.level ==1 ? true : false;
    }

    public String hideEmail(String email) {
        String[] emailSplit = email.split("@");
        String username = emailSplit[0];
        String domain = emailSplit[1];

        String hideUsername = "";
        for (int i = 1; i < username.length()-1; i++) {
            hideUsername += '*';
        }
        String hideDomain = "";
        for (int i = 1; i < domain.length()-1; i++) {
            hideDomain += '*';
        }


        return username.charAt(0) + hideUsername + username.charAt(username.length()-1) + "@" + domain.charAt(0) + hideDomain + domain.charAt(domain.length()-1);

    }

    public static void main(String[] args) {
        System.out.println(new User().hideEmail("tinhle2772002@gmail.com"));
    }
}
