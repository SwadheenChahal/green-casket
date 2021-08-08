package com.unicofox.greencasket.Model;

public class ModelOrder {


    public ModelOrder(){}


    String orderTitle,orderImage,orderStatus;
    int orderIId,orderPrice;


    public String getOrderTitle() {
        return orderTitle;
    }

    public String getOrderImage() {
        return orderImage;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public int getOrderIId() {
        return orderIId;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public void setOrderImage(String orderImage) {
        this.orderImage = orderImage;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderIId(int orderIId) {
        this.orderIId = orderIId;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }
}
