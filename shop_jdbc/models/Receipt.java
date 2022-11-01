package Lesson31.shop_jdbc.models;

import java.util.Date;

public class Receipt {
    private int id;
    private Date addDate;
    private int sellerId;

    public Receipt(int id, Date addDate, int sellerId) {
        this.id = id;
        this.addDate = addDate;
        this.sellerId = sellerId;
    }

    public Receipt(Date addDate, int sellerId) {
        this.addDate = addDate;
        this.sellerId = sellerId;
    }

    public int getId() {
        return id;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", addDate=" + addDate +
                ", sellerId=" + sellerId +
                '}';
    }
}
