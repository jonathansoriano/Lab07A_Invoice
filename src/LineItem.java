public class LineItem extends Product {//Extends Product so we can access the Product Name and price
    private int productQuantity = 0;
    private double productSubtotal = 0.0;

    //Super keyword shows inheritance!
    public LineItem(String name, int productQuantity, double price) {
        super(name, price);// inherited attributes. No need to type out get or set methods for these since we also inherited those methods from Product class
        this.productQuantity = productQuantity;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductSubtotal(){
        return productSubtotal = productQuantity * super.getPrice();
    }

    @Override
    public String toString() { // This returns a String of the information of the instance of that LineItem
        return String.format("%s%20s%20s%20s",getName() , getProductQuantity() ,"$" + getPrice(), "$" + getProductSubtotal());
    }
}
