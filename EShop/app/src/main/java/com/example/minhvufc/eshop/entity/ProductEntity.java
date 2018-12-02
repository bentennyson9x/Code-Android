package com.example.minhvufc.eshop.entity;

public class ProductEntity {
    private int id; //"1",
    private int idcat; //"1",
    private String name; //"HP Probook",
    private float price; //"15000",
    private String description; //"Đập không vỡ",
    private String detail; //"Đến cửa hàng mà xem kỹ nhá",
    private String imagelink; //"images/hp123.png",
    private String inputdate; //"2018-08-02 11:56:22",
    private int status; //"1"

    public ProductEntity(int id, int idcat, String name,
                         float price, String description,
                         String detail, String imagelink,
                         String inputdate, int status) {
        this.id = id;
        this.idcat = idcat;
        this.name = name;
        this.price = price;
        this.description = description;
        this.detail = detail;
        this.imagelink = imagelink;
        this.inputdate = inputdate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcat() {
        return idcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getInputdate() {
        return inputdate;
    }

    public void setInputdate(String inputdate) {
        this.inputdate = inputdate;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
