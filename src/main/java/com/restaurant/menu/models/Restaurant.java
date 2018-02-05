package com.restaurant.menu.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Document(collection="restaurants")
@JsonIgnoreProperties(value = {"createdAt"})
public class Restaurant {
    @Id
    private String id;

    @NotNull
    @Size(max=100)
    @Indexed(unique=true)
    private String name;

    private Date createdAt = new Date();

    private String address;

    private List<Menu> menu;

    public Restaurant(String id, String name, Boolean completed, Date createdAt, String address, List<Menu> menu) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.address = address;
        this.menu = menu;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }
}
