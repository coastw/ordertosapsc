/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coast.model;

/**
 *
 * @author Coast
 */
public class Product {
    private String fullSn;
    private String snCode;
    private String colorCode;
    private String sizeCode;
    private String type;
    private String color;
    private String brandSize;
    private String orgPrice;
    private int amount;

    public Product(String fullSn, String snCode, String colorCode, String sizeCode, String type, String color, String size, String orgPrice, int amount) {
        this.fullSn = fullSn;
        this.snCode = snCode;
        this.colorCode = colorCode;
        this.sizeCode = sizeCode;
        this.type = type;
        this.color = color;
        this.brandSize = size;
        this.orgPrice = orgPrice;
        this.amount = amount;
    }

    public String getFullSn() {
        return fullSn;
    }

    public void setFullSn(String fullSn) {
        this.fullSn = fullSn;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return brandSize;
    }

    public void setSize(String size) {
        this.brandSize = size;
    }

    public String getOrgPrice() {
        return orgPrice;
    }

    public void setOrgPrice(String orgPrice) {
        this.orgPrice = orgPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" + "fullSn=" + fullSn + ", snCode=" + snCode + ", colorCode=" + colorCode + ", sizeCode=" + sizeCode + ", type=" + type + ", color=" + color + ", size=" + brandSize + ", orgPrice=" + orgPrice + ", amount=" + amount + '}';
    }

}
