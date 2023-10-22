package com.example.springmodels.models;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String reason;
    @Column
    private String text;
    @Column
    private LocalDate localDate = LocalDate.now();

    public Application() {
    }

    public Application(int id, String reason, String text, LocalDate localDate) {
        this.id = id;
        this.reason = reason;
        this.text = text;
        this.localDate = localDate;
    }

    public Application(String reason, String text, LocalDate localDate) {
        this.reason = reason;
        this.text = text;
        this.localDate = localDate;
    }

    public Application(String reason, String text) {
        this.reason = reason;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
