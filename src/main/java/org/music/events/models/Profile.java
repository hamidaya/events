package org.music.events.models;
import jakarta.persistence.*;
import org.springframework.objenesis.SpringObjenesis;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    @OneToOne
    private Photo photo;
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
      public Long getProfileId() {
        return profileId;
    }
    public void setProfileId(Long profileId) {
        this.profileId = profileId;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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
    public Photo getPhoto() {
        return photo;
    }
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }



}