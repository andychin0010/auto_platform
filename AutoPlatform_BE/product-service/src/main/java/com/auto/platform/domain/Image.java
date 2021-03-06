package com.auto.platform.domain;

/**
 * Created by chishingchin on 3/2/16.
 */
public class Image {

    private long id;
    private long productId;
    private String path;
    private ImageType type;
    private long creationTimestamp;
    private long modificationTimestamp;

    public Image() {
    }

    public Image(long id, long productId, String path, ImageType type, long creationTimestamp, long modificationTimestamp) {
        this.id = id;
        this.productId = productId;
        this.path = path;
        this.type = type;
        this.creationTimestamp = creationTimestamp;
        this.modificationTimestamp = modificationTimestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
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