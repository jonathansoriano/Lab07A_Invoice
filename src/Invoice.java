import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Invoice extends JFrame {
    private String title = "";
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
        mainPanel = new JPanel(new BorderLayout()); //
        this.add(mainPanel);

        generateInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        generateButtonPanel();
        mainPanel.add(buttonPanel,BorderLayout.CENTER);

        generateInvoicePanel();
        mainPanel.add(invoicePanel, BorderLayout.SOUTH);


        this.setSize(500, 400);//600 height
        this.setTitle("Invoice Application");
        this.setLocationRelativeTo(null);
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
        clearButton.addActionListener((ActionEvent ae) -> {
            productTF.setText("");
            quantityTF.setText("");
            priceTF.setText("");
        });

        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener((ActionEvent ae) ->{

            try {
                // Code that might throw an exception
                if (productTF.getText().isEmpty() || quantityTF.getText().isEmpty() || priceTF.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please enter values for all the text fields");
                }else {
                    LineItem item = new LineItem(productTF.getText(), Integer.parseInt(quantityTF.getText()), Double.parseDouble(priceTF.getText()));// Do we need to make a reference for every
                    // item in the LineItem arrayList? Probaby need to so we can use each subtotal
                    // from each item to get grandtotal
                    //item.get(index).getProductSubtotal to get a specific item's subtotal from the LineItem arrayList. (it should work anyways)
                    items.add(new LineItem(productTF.getText(), Integer.parseInt(quantityTF.getText()), Double.parseDouble(priceTF.getText())));

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid text into the fields!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Something went wrong...");
            }
            //Need an if statement.



        });
        JButton generateInvoiceButton = new JButton("Generate Invoice");
        generateInvoiceButton.addActionListener((ActionEvent ae) -> {
            if (productTF.getText().isEmpty() || quantityTF.getText().isEmpty() || priceTF.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter values for all the text fields");
            }
            else {
                String invoice = "";
                invoice += String.format("%50s\n", "INVOICE");
                invoice += "Sam's Small Appliances\n100 Main Street\nAnytown, CA 98765\n";
                invoice += "=================================================================================\n";
                invoice += String.format("%s%20s%20s%20s\n\n","Product", "Quantity","Price","Subtotal");

                for (int x = 0; x < items.size(); x++) { // for loop so we can go through each item's information
                    invoice += items.get(x).toString() + "\n";// Instead of printing it out to the console, set the set of the TextArea
                }
                invoice += "=================================================================================\n";
                for (int i = 0; i < items.size(); i++){
                    grandTotal += items.get(i).getProductSubtotal();
                }
                //System.out.println(grandTotal);// use String.format to add it to the TextArea
                invoice += "Amount due: " + "$" + grandTotal + "\n";

                invoiceTA.setText(invoice);
                items.clear();
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
        invoiceTA = new JTextArea(10, 40);
        invoiceTA.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(invoiceTA);

        invoicePanel.add(scrollPane);
    }




}
