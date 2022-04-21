/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sales.invoice.generator.model;

/**
 *
 * @author hussein
 */
public class InvoiceLine {
    
    private String productName;
    private int productQuintity;
    private double price;
    private InvoiceHeader header;

    public InvoiceLine(String productName, int productQuintity, double price, InvoiceHeader header) {
        this.productName = productName;
        this.productQuintity = productQuintity;
        this.price = price;
        this.header = header;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuintity() {
        return productQuintity;
    }

    public void setProductQuintity(int productQuintity) {
        this.productQuintity = productQuintity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }
    
    
    
    
}
