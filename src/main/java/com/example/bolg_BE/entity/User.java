package com.example.bolg_BE.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_user")
    private int maUser;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="ten_dang_nhap", nullable = false, unique = true)
    private String tenDangNhap;
    @Column(name = "password", length = 255)
    private String password;
    @Column(name = "status")
    private int status;
    @Column(name = "created_at")
    private Date created_at;
    @Column(name = "update_at")
    private Date Update_at;
    @Column(name = "da_kich_hoat")
    private Boolean daKichHoat;
    @Column(name = "ma_kich_hoat")
    private String maKichHoat;
    @Column(name = "avatar", columnDefinition = "LONGTEXT")
    @Lob
    private String avatar;
@ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH
})
@JoinTable(
        name="user_quyen",
        joinColumns =@JoinColumn(name="ma_user"),
inverseJoinColumns =@JoinColumn(name = "ma_quyen")
)
    private List<Quyen> quyens;
@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
private List<Post> posts;
    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdate_at() {
        return Update_at;
    }

    public void setUpdate_at(Date update_at) {
        Update_at = update_at;
    }

    public Boolean getDaKichHoat() {
        return daKichHoat;
    }

    public void setDaKichHoat(Boolean daKichHoat) {
        this.daKichHoat = daKichHoat;
    }

    public String getMaKichHoat() {
        return maKichHoat;
    }

    public void setMaKichHoat(String maKichHoat) {
        this.maKichHoat = maKichHoat;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Quyen> getQuyens() {
        return quyens;
    }

    public void setQuyens(List<Quyen> quyens) {
        this.quyens = quyens;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
