package com.example.bolg_BE.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "ma_post")
private int maPost;
    @Column(name = "title")
private String title;
    @Column(name = "description")
private String description;
    @Column(name = "status")
private String status;
    @Column(name = "thumbnail")
private String thumbnail;
    @Column(name = "created_at")
private Date created_at;
    @Column(name = "updated_at")
private Date updated_at;
    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "ma_category", nullable = false)
private Category category;
@ManyToOne(cascade = {CascadeType.ALL})
@JoinColumn(name="ma_user",nullable=false)
private User user;

    public int getMaPost() {
        return maPost;
    }

    public void setMaPost(int maPost) {
        this.maPost = maPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategorie(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
