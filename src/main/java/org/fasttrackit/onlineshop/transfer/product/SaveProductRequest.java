package org.fasttrackit.onlineshop.transfer.product;

import javax.validation.constraints.NotNull;

public class SaveProductRequest {

    @NotNull
    private String description;
    @NotNull
    private double price;
    @NotNull
    private String name;

    private String imageUrl;
    @NotNull
    private int quantity;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SaveProductRequest{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
