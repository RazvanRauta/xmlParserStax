public class Product {
    private String description;
    private long gtin;
    private double price;
    private String supplier;
    private int ordeId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getGtin() {
        return gtin;
    }

    public void setGtin(long gtin) {
        this.gtin = gtin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Product() {
    }

    public int getOrdeId() {
        return ordeId;
    }

    public void setOrdeId(int ordeId) {
        this.ordeId = ordeId;
    }

}
