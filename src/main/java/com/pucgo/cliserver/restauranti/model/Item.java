package com.pucgo.cliserver.restauranti.model;

import java.net.MalformedURLException;
import java.net.URL;

public class Item {

    public Item() {
    }

    public Item(Long id, String product, String description, URL imageUrl) {
        this.id = id;
        this.product = product;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Item(Long id, String product, String description, String imageUrl) throws MalformedURLException {
        this.id = id;
        this.product = product;
        this.description = description;
        this.imageUrl = new URL(imageUrl);
    }

    private Long id;
    private String product;
    private String description;
    private URL imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageUrl(String imageUrl) throws MalformedURLException {
        this.imageUrl = new URL(imageUrl);
    }
}
