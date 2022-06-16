package com.example.serverrest.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;


@ToString


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String pictureUrl;
    private boolean status;
    private Timestamp timestamp;


    public User(Long id, String name, String email, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
    }

    public User(String name, String email, boolean status, Timestamp timestamp) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.timestamp = timestamp;
    }

    public User(String name, String email, boolean status) {
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(Long id, String name, String email, String pictureUrl, boolean status, Timestamp timestamp) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User(Long id, String name, String email, String pictureUrl, boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public User(String name, String email, String pictureUrl, boolean status, Timestamp timestamp) {
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.status = status;
        this.timestamp = timestamp;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User(String name, String email, String pictureUrl) {
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
