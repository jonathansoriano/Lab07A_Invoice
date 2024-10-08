public class LineItem extends Product {
    private int productQuantity = 0;
    private double productSubtotal = 0.0;

    public LineItem(String name, int productQuantity, double price) {
        super(name, price);
        this.productQuantity = productQuantity;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductSubtotal(){
        return productSubtotal = productQuantity * getPrice();
    }

    @Override
    public String toString() {
        return String.format("%s%20s%20s%20s",getName() , getProductQuantity() ,"$" + getPrice(), "$" + getProductSubtotal());
    }
}
