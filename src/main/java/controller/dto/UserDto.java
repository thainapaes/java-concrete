package controller.dto;

import entity.Phone;

import java.time.LocalDateTime;
import java.util.List;

public class UserDto {

    private String name;

    private String email;

    private String password;

    private List<PhoneDto> phonesDtoList;

    private LocalDateTime created;

    private LocalDateTime modifided;

    private LocalDateTime last_login;

    private String token;

    public UserDto() {}

    public UserDto(String name, String email, String password, List<PhoneDto> phonesDtoList,
                   LocalDateTime created, LocalDateTime modifided, LocalDateTime last_login, String token) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phonesDtoList = phonesDtoList;
        this.created = created;
        this.modifided = modifided;
        this.last_login = last_login;
        this.token = token;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneDto> getPhonesDtoList() {
        return phonesDtoList;
    }

    public void setPhonesDtoList(List<PhoneDto> phonesDtoList) {
        this.phonesDtoList = phonesDtoList;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModifided() {
        return modifided;
    }

    public void setModifided(LocalDateTime modifided) {
        this.modifided = modifided;
    }

    public LocalDateTime getLast_login() {
        return last_login;
    }

    public void setLast_login(LocalDateTime last_login) {
        this.last_login = last_login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
