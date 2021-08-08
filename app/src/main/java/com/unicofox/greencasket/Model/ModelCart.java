package com.unicofox.greencasket.Model;

public class ModelCart {

    String productTitle,productImageUrl;
    int productCurrentPrice,productRealPrice,productId,cartItems;

    public ModelCart(){}

    public String getProductTitle() {
        return productTitle;
    }


    public String getProductImageUrl() {
        return productImageUrl;
    }

    public int getProductCurrentPrice() {
        return productCurrentPrice;
    }

    public int getProductRealPrice() {
        return productRealPrice;
    }

    public int getProductId() {
        return productId;
    }

    public int getCartItems() {
        return cartItems;
    }


    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public void setProductCurrentPrice(int productCurrentPrice) {
        this.productCurrentPrice = productCurrentPrice;
    }

    public void setProductRealPrice(int productRealPrice) {
        this.productRealPrice = productRealPrice;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setCartItems(int cartItems) {
        this.cartItems = cartItems;
    }


}
