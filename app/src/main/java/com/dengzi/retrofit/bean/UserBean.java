package com.dengzi.retrofit.bean;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/14.
 * @Version:1.0.0
 */
public class UserBean {
    private String login;
    private int id;
    private String url;
    private String name;
    private Object company;
    private String location;
    private Object email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", company=" + company +
                ", location='" + location + '\'' +
                ", email=" + email +
                '}';
    }
}
