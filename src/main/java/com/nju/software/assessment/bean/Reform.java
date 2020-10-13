package com.nju.software.assessment.bean;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reform")
public class Reform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String publishId;
    private int userId;
    private int isReform;
    private String reformContent;
    private Timestamp reformTime;

    public Reform(String publishId, int userId, int isReform, String reformContent, Timestamp reformTime) {
        this.publishId = publishId;
        this.userId = userId;
        this.isReform = isReform;
        this.reformContent = reformContent;
        this.reformTime = reformTime;
    }

    public Reform() {
    }

    public String getReformContent() {
        return reformContent;
    }

    public void setReformContent(String reformContent) {
        this.reformContent = reformContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getReformTime() {
        return reformTime;
    }

    public void setReformTime(Timestamp reformTime) {
        this.reformTime = reformTime;
    }

    public int getIsReform() {
        return isReform;
    }

    public void setIsReform(int isReform) {
        this.isReform = isReform;
    }
}
