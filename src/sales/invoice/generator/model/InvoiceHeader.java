/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sales.invoice.generator.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hussein
 */
public class InvoiceHeader {
    
 private int invoiceNumber;
 private Date invoiceDate;
 private String invoiceCustomer;
 private double total=0.0;
 List<InvoiceLine> products = new ArrayList<>();

    public InvoiceHeader(int invoiceNumber, Date invoiceDate, String invoiceCustomer ) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.invoiceCustomer = invoiceCustomer;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceCustomer() {
        return invoiceCustomer;
    }

    public void setInvoiceCustomer(String invoiceCustomer) {
        this.invoiceCustomer = invoiceCustomer;
    }

    public List<InvoiceLine> getProducts() {
        return products;
    }

    public void setProducts(List<InvoiceLine> products) {
        this.products = products;
    }
    
    
    public double getTotal(){
        total = 0.0;
        for(int i=0 ; i<products.size() ; i++)
            total+= (products.get(i).getPrice()*products.get(i).getProductQuintity());
        
        return total;
    }
 
    
    public void setTotal(){
        
        this.total=total;
    }
 
    
    
}
