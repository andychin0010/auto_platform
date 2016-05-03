package com.auto.platform.domain;

import java.util.List;

/**
 * Created by chishingchin on 3/2/16.
 */
public class Product {

    private long id;
    private ProductState state;
    private String make;
    private String model;
    private ProductStatus status;
    private int year;
    private int mileage;
    private String color;
    private List<Image> images;
    private String description;
    private long creationTimestamp;
    private long modificationTimestamp;

    public Product() {
    }

    public Product(long id,
                   String make,
                   String model,
                   ProductStatus status,
                   int year,
                   int mileage,
                   List<Image> images,
                   String description,
                   long creationTimestamp,
                   long modificationTimestamp) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.status = status;
        this.year = year;
        this.mileage = mileage;
        this.images = images;
        this.description = description;
        this.creationTimestamp = creationTimestamp;
        this.modificationTimestamp = modificationTimestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductState getState() {
        return state;
    }

    public void setState(ProductState state) {
        this.state = state;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public long getModificationTimestamp() {
        return modificationTimestamp;
    }

    public void setModificationTimestamp(long modificationTimestamp) {
        this.modificationTimestamp = modificationTimestamp;
    }
}
