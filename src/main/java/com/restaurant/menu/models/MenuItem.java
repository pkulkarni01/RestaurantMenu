package com.restaurant.menu.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection="menuitems")
@JsonIgnoreProperties(value = {"createdAt"})
public class MenuItem {

    @Id
    private String id;

    @NotNull
    @Size(max=100)
    @Indexed(unique=true)
    private String name;

    private Date createdAt = new Date();

    private String description;

    private float price;

    public MenuItem(String id, String name, Date createdAt, String description, float price) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
