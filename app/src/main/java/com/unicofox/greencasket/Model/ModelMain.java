package com.unicofox.greencasket.Model;

public class ModelMain {

    String productTitle,productBrandName,productImageUrl,productDescription;
    Double productRatings;
    int productRaters,productCurrentPrice,productRealPrice,productId;

    public ModelMain(){}

    public ModelMain(int productId, String productTitle, String productBrandName, String productImageUrl, Double productRatings, int productRaters, int productCurrentPrice, int productRealPrice, String productDescription){
        this.productTitle=productTitle;
        this.productBrandName=productBrandName;
        this.productImageUrl=productImageUrl;
        this.productRatings=productRatings;
        this.productRaters=productRaters;
        this.productCurrentPrice=productCurrentPrice;
        this.productRealPrice=productRealPrice;
        this.productDescription=productDescription;
        this.productId=productId;
    }


    public String getProductTitle() {
        return productTitle;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public Double getProductRatings() {
        return productRatings;
    }

    public int getProductRaters() {
        return productRaters;
    }

    public int getProductCurrentPrice() {
        return productCurrentPrice;
    }

    public int getProductRealPrice() {
        return productRealPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getProductId() { return productId; }




}
