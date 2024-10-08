import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Invoice extends JFrame {
    private String title = "INVOICE";
    private double grandTotal = 0.0;
    private ArrayList<LineItem> items = new ArrayList<>();

    //Panel
    JPanel mainPanel;
    JPanel inputPanel;
    JPanel buttonPanel;
    JPanel invoicePanel;

    JLabel productLabel;
    JLabel priceLabel;
    JLabel quantityLabel;

    JTextField productTF;
    JTextField priceTF;
    JTextField quantityTF;

    JTextArea invoiceTA;


    public Invoice(){
        mainPanel = new JPanel(new BorderLayout()); // Allows me to use North, Center, South locations on the Panel.
        this.add(mainPanel); // Adding Main panel (hold the other 3 panels) to the JFrame (Invoice).

        generateInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        generateButtonPanel();
        mainPanel.add(buttonPanel,BorderLayout.CENTER);

        generateInvoicePanel();
        mainPanel.add(invoicePanel, BorderLayout.SOUTH);


        this.setSize(500, 400);//600 height
        this.setTitle("Invoice Application");
        this.setLocationRelativeTo(null); // Centers frame to the center of the computer screen
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void generateInputPanel(){
        inputPanel = new JPanel(new GridLayout(3, 2));

        productLabel = new JLabel("Please enter the name of your item: ");
        priceLabel = new JLabel("Please enter the price of the item: ");
        quantityLabel = new JLabel("Please enter the quantity: ");

        productTF = new JTextField(10);
        priceTF = new JTextField(10);
        quantityTF = new JTextField(10);

        inputPanel.add(productLabel);
        inputPanel.add(productTF);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityTF);
        inputPanel.add(priceLabel);
        inputPanel.add(priceTF);
    }

    public void generateButtonPanel(){
        buttonPanel = new JPanel();

        JButton clearButton = new JButton("Clear Items");
        clearButton.setFocusable(false);
        clearButton.addActionListener((ActionEvent ae) -> {
            productTF.setText("");
            quantityTF.setText("");
            priceTF.setText("");
        });

        JButton addItemButton = new JButton("Add Item");
        addItemButton.setFocusable(false);
        addItemButton.addActionListener((ActionEvent ae) ->{
            // Since we are going to convert text to int and doubles, we need to use the Integer.parseInt() method and Double.parseDouble() method
            // That being said, if the user puts in the wrong int or double format type, it will throw an exception (NumberFormatException).
            //This is why we need to use Try Catch block to deal with Exception.
            try {
                if (productTF.getText().isEmpty() || quantityTF.getText().isEmpty() || priceTF.getText().isEmpty()){ // If the text fields are empty, show a dialog box to fill text fields to continue
                    JOptionPane.showMessageDialog(this, "Please enter values for all the text fields");// Parent component is this JFrame(Invoice)
                }else {
                    LineItem item = new LineItem(productTF.getText(), Integer.parseInt(quantityTF.getText()), Double.parseDouble(priceTF.getText()));
                    // We need an instance of the LineItem class every time we add
                    //an item to the ArrayList called "items" (Line 9)
                    // We need a new instance for each item added so we can get and use each subtotal
                    // from each item to get grandTotal.
                    items.add(new LineItem(productTF.getText(), Integer.parseInt(quantityTF.getText()), Double.parseDouble(priceTF.getText()))); // adding to the ArrayList using LineItem contructor

                }
            } catch (NumberFormatException e) { // Code that might throw an exception
                JOptionPane.showMessageDialog(this, "Please enter valid text into the fields!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Something went wrong...");
            }



        });
        JButton generateInvoiceButton = new JButton("Generate Invoice");
        generateInvoiceButton.setFocusable(false);
        generateInvoiceButton.addActionListener((ActionEvent ae) -> {
            if (productTF.getText().isEmpty() || quantityTF.getText().isEmpty() || priceTF.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter values for all the text fields");
            }
            else {
                String invoice = "";
                invoice += String.format("%50s\n", title); //Line 8
                invoice += "Sam's Small Appliances\n100 Main Street\nAnytown, CA 98765\n";
                invoice += "=================================================================================\n";
                invoice += String.format("%s%20s%20s%20s\n\n","Product", "Quantity","Price","Subtotal");

                for (int x = 0; x < items.size(); x++) { // for loop so we can go through each item's information
                    invoice += items.get(x).toString() + "\n";// Instead of printing it out to the console, set the set of the TextArea
                }
                invoice += "=================================================================================\n";
                for (int i = 0; i < items.size(); i++){
                    //item.get(index).getProductSubtotal() wil work since the objects in the ArrayList are type LineItem and this method is available to the LineItem class and objects
                    grandTotal += items.get(i).getProductSubtotal();
                }
                invoice += "Amount due: " + "$" + grandTotal + "\n";

                invoiceTA.setText(invoice);
                items.clear();// Empties all items in "items" ArrayList.
                grandTotal = 0.0; // Reset Grand total so if Generate Button is pressed again, the value of grandTotal doesn't increment
                                //through the for loop.
            }


        });
        buttonPanel.add(clearButton);
        buttonPanel.add(addItemButton);
        buttonPanel.add(generateInvoiceButton);
    }

    public void generateInvoicePanel(){
        invoicePanel = new JPanel();
        invoiceTA = new JTextArea(10, 40); //height(rows) and length(columns)
        invoiceTA.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(invoiceTA);

        invoicePanel.add(scrollPane);
    }




}
