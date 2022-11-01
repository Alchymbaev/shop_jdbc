package Lesson31.shop_jdbc.models;

public class ProductReceipt {
    private int id;
    private int receiptId;
    private int productId;

    public ProductReceipt(int id, int receiptId, int productId) {
        this.id = id;
        this.receiptId = receiptId;
        this.productId = productId;
    }

    public ProductReceipt(int receiptId, int productId) {
        this.receiptId = receiptId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductReceipt{" +
                "id=" + id +
                ", receiptId=" + receiptId +
                ", productId=" + productId +
                '}';
    }
}
