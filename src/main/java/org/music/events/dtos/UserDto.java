package org.music.events.dtos;

import org.music.events.models.Authority;

import java.util.Set;

public class UserDto {

    public String username;
    public String password;
    public Boolean enabled;
    public String apikey;
    public String email;
    public String adress;
    private String name;
    private String phone;
    private String city;
    private String state;
    private String zip;
    private String country;

    public Set<Authority> authorities;

    public UserDto() {

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }




    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public UserDto(String username, String password, Boolean enabled, String apikey, String adress, String name, String email1, String phone, String city, String state, String zip, String country) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.apikey = apikey;
        this.adress = adress;
        this.name = name;
        this.email = email1;
        this.phone = phone;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;


    }

    public UserDto(Set<Authority> authorities) {
        this.authorities = authorities;
    }

}




