package com.example.todo.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(access="hidden", hidden=true)
    private Integer id;
    private String desc;
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(access="hidden", hidden=true)
    private Date modifiedDate = new Date() ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
