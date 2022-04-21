/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sales.invoice.generator.design;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import sales.invoice.generator.model.InvoiceHeader;
import sales.invoice.generator.model.InvoiceLine;

/**
 *
 * @author hussein
 */
public class InoviceForm extends JFrame implements ActionListener, ListSelectionListener {

    private List<InvoiceHeader> InvoicesList = new ArrayList<>();
    private NewInvoice newInvoice = new NewInvoice(this);
    File filepathHeader;
    File filepathLine;

    // left side
    private JButton createNewInvoice, deleteInvoice;
    private DefaultTableModel modelInvoice = new DefaultTableModel();
    private JTable invoiceTable;

    //right side
    private JLabel invoiceNumber, inoviceNumbertxt;
    private JLabel inoviceDatetxt;
    private JTextField invoiceDateTF;
    private JLabel customerNametxt;
    private JTextField customerNameTF;
    private JLabel inoviceTotal, inoviceTotaltxt;
    private JTable DetialsInvoiceTable;
    private JButton saveInvoice, cancelInvoice;
    private JScrollPane jScrollPaneleft, jScrollPaneRight;
    private DefaultTableModel modelDetailsInvoice = new DefaultTableModel();

    //Menu bar
    private JMenu Menu;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem loadMenuItem, saveMenuItem;

    public InoviceForm() {

//intial left side    
        invoiceTable = new JTable(modelInvoice);
        jScrollPaneleft = new JScrollPane(invoiceTable);

        createNewInvoice = new JButton();
        createNewInvoice.addActionListener(this);
        deleteInvoice = new JButton();
        deleteInvoice.addActionListener(this);

//intial right side    
        invoiceNumber = new JLabel();
        inoviceNumbertxt = new JLabel();
        inoviceDatetxt = new JLabel();
        invoiceDateTF = new JTextField();
        customerNametxt = new JLabel();
        customerNameTF = new JTextField();
        inoviceTotal = new JLabel();
        inoviceTotaltxt = new JLabel();
        DetialsInvoiceTable = new JTable(modelDetailsInvoice);
        jScrollPaneRight = new JScrollPane(DetialsInvoiceTable);
        DetialsInvoiceTable.getSelectionModel().addListSelectionListener(this);
        saveInvoice = new JButton();
        saveInvoice.addActionListener(this);
        cancelInvoice = new JButton();
        cancelInvoice.addActionListener(this);

//menu bar
        MenuBar = new javax.swing.JMenuBar();
        Menu = new javax.swing.JMenu();
        loadMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();

// set defulat text to left side
        createNewInvoice.setText("Create New Invoice");
        createNewInvoice.setActionCommand("Create_New_Invoice");
        deleteInvoice.setText("Delete Invoice");
        deleteInvoice.setActionCommand("Delete_Invoice");

// set defulat text to right side
        inoviceNumbertxt.setText("Invoice Number");
        inoviceDatetxt.setText("Invoide Date");
        customerNametxt.setText("Customer name");
        inoviceTotaltxt.setText("Invoice Total");
        saveInvoice.setText("Save");
        saveInvoice.setActionCommand("save_Invoice");
        saveInvoice.addActionListener(this);
        cancelInvoice.setText("cancel");
        cancelInvoice.setActionCommand("Cancel_Invoice");

// set defulat text to menu bar
        Menu.setText("File");
        loadMenuItem.setText("Load File");
        loadMenuItem.setActionCommand("Load_File");
        loadMenuItem.addActionListener(this);
        Menu.add(loadMenuItem);
        saveMenuItem.setText("Save File");
        saveMenuItem.setActionCommand("Save_File");
        saveMenuItem.addActionListener(this);
        Menu.add(saveMenuItem);
        MenuBar.add(Menu);
        setJMenuBar(MenuBar);

        this.setLayout(new GridLayout(1, 1, 10, 0));
        JPanel panel_left = new JPanel(new GridLayout(2, 1));
        JPanel panel_right = new JPanel(new GridLayout(3, 1));
        this.add(panel_left);
        this.add(panel_right);

// add our components to the left side
        panel_left.add(jScrollPaneleft);
        JPanel bottom_left = new JPanel(new BorderLayout());
        JPanel button_panel_left = new JPanel(new GridLayout(1, 2, 10, 0));
        button_panel_left.add(createNewInvoice);
        button_panel_left.add(deleteInvoice);
        bottom_left.add(button_panel_left, BorderLayout.SOUTH);
        panel_left.add(bottom_left);

        JPanel top_Right = new JPanel(new GridLayout(4, 2, 10, 10));
        top_Right.add(inoviceNumbertxt);
        top_Right.add(invoiceNumber);
        top_Right.add(inoviceDatetxt);
        top_Right.add(invoiceDateTF);
        top_Right.add(customerNametxt);
        top_Right.add(customerNameTF);
        top_Right.add(inoviceTotaltxt);
        top_Right.add(inoviceTotal);
        panel_right.add(top_Right);

        panel_right.add(jScrollPaneRight);
        JPanel bottom_right = new JPanel(new BorderLayout());
        JPanel button_panel_right = new JPanel(new GridLayout(1, 2, 10, 0));
        button_panel_right.setSize(160, 40);
        button_panel_right.add(saveInvoice);
        button_panel_right.add(cancelInvoice);
        bottom_right.add(button_panel_right, BorderLayout.SOUTH);
        panel_right.add(bottom_right);

        modelInvoice.addColumn("No.");
        modelInvoice.addColumn("Customer");
        modelInvoice.addColumn("Date");
        modelInvoice.addColumn("Total");

        modelDetailsInvoice.addColumn("No.");
        modelDetailsInvoice.addColumn("Item Name");
        modelDetailsInvoice.addColumn("Item Price");
        modelDetailsInvoice.addColumn("Count");
        modelDetailsInvoice.addColumn("Item Total");

        //this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        invoiceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                chooseRow();

            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Load_File")) {
            loadInvoices();
            fillInvoiceTable(InvoicesList);

        } else if (e.getActionCommand().equals("Create_New_Invoice")) {
            showNewInvoice();
        } else if (e.getActionCommand().equals("add_new_invoice")) {
            saveNewInvoice();
            System.out.println("tttttttttt");
        } else if (e.getActionCommand().equals("Delete_Invoice")) {
            deleteInvoice();
        } else if (e.getActionCommand().equals("Save_File")) {
            saveInvoice();
        } else if (e.getActionCommand().equals("Cancel_Invoice")) {
            modelDetailsInvoice.setRowCount(0);
        } else if (e.getActionCommand().equals("save_Invoice")) {
              save_changes();
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

//--------------------------------------------------------------------------------
// fill data in tables
    private void fillInvoiceTable(List<InvoiceHeader> list) {
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            String date = new SimpleDateFormat("dd-mm-yyyy").format(list.get(i).getInvoiceDate());
            String[] invoiceHeaderRow = {list.get(i).getInvoiceNumber() + "", list.get(i).getInvoiceCustomer(), date, list.get(i).getTotal() + ""};
            System.out.println(list.get(i).getProducts());
            modelInvoice.addRow(invoiceHeaderRow);
        }
    }

    private void fillDetialsInvoiceTable(List<InvoiceLine> list, InvoiceHeader header) {
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            double totalItem = list.get(i).getPrice() * list.get(i).getProductQuintity();
            String[] invoiceLineRow = {header.getInvoiceNumber() + "", list.get(i).getProductName(), list.get(i).getPrice() + "", list.get(i).getProductQuintity() + "", totalItem + ""};
            modelDetailsInvoice.addRow(invoiceLineRow);
        }
    }

    //---------------------------------------------------------------------------------
    //----   menu bar funcations
    public void loadInvoices() {

        InvoicesList.clear();
        InvoiceHeader invoice = null;
        JOptionPane.showMessageDialog(this, "Please, choose two files [Header and Lines]", "Attension", JOptionPane.INFORMATION_MESSAGE);

        JFileChooser openFile = new JFileChooser();
        int result = openFile.showOpenDialog(this);
        System.out.println(result + "hhj");
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileChooser = openFile.getSelectedFile();
            filepathHeader = fileChooser;
            String filepath = fileChooser.getPath();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
                String brString = null;
                while ((brString = bufferedReader.readLine()) != null) {
                    String[] InvoiceHeaders = brString.split(",");
                    String invoice_Number = InvoiceHeaders[0];
                    String invoice_Date = InvoiceHeaders[1];
                    String customer_Name = InvoiceHeaders[2];
                    int invoiceNumber = Integer.parseInt(invoice_Number);

                    Date invoiceDate = new SimpleDateFormat("dd-mm-yyyy").parse(invoice_Date);
                    invoice = new InvoiceHeader(invoiceNumber, invoiceDate, customer_Name);
                    InvoicesList.add(invoice);

                }

                result = openFile.showOpenDialog(this);
                System.out.println(result + "oooo");

                if (result == JFileChooser.APPROVE_OPTION) {
                    File fileChooser2 = openFile.getSelectedFile();
                    filepathLine = fileChooser2;
                    String filepath2 = fileChooser2.getPath();
                    System.out.println(filepath2 + "oooo");

                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(filepath2));
                    String brString2 = null;
                    while ((brString = bufferedReader2.readLine()) != null) {
                        String[] Lines = brString.split(",");
                        String invoice_Number = Lines[0];
                        String product_Name = Lines[1];
                        String product_Price = Lines[2];
                        String product_Count = Lines[3];

                        System.out.println(invoice_Number + "ppoooo");

                        int invoiceNumber = Integer.parseInt(invoice_Number);
                        double productPrice = Double.parseDouble(product_Price);
                        int productCount = Integer.parseInt(product_Count);
                        InvoiceHeader header = null;

                        for (InvoiceHeader invoiceHeader : InvoicesList) {
                            if (invoiceNumber == invoiceHeader.getInvoiceNumber()) {
                                header = invoiceHeader;
                                break;
                            }
                        }
                        InvoiceLine product = new InvoiceLine(product_Name, productCount, productPrice, header);
                        header.getProducts().add(product);
                        System.out.println(header + "oooo");

                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public void saveInvoice() {

        String invoices = "";
        String productLines = "";

        for (int i = 0; i < InvoicesList.size(); i++) {

            invoices += InvoicesList.get(i).getInvoiceNumber() + ",";
            invoices += new SimpleDateFormat("dd-mm-yyyy").format(InvoicesList.get(i).getInvoiceDate()) + ",";
            invoices += InvoicesList.get(i).getInvoiceCustomer() + "\n";
            List<InvoiceLine> products = InvoicesList.get(i).getProducts();

            for (int x = 0; x < products.size(); x++) {
                productLines += InvoicesList.get(i).getInvoiceNumber() + ",";
                productLines += products.get(x).getProductName() + ",";
                productLines += products.get(x).getPrice() + ",";
                productLines += products.get(x).getProductQuintity() + "\n";

            }
        }
        JOptionPane.showMessageDialog(this, "Choose location to Save [header_file , lines_file]", "Attension", JOptionPane.INFORMATION_MESSAGE);
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File mainFile = fileChooser.getSelectedFile();
            try {
                FileWriter invoicesFile = new FileWriter(mainFile);
                invoicesFile.write(invoices);
                invoicesFile.close();

                result = fileChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File filechoose2 = fileChooser.getSelectedFile();
                    FileWriter productsLineFile = new FileWriter(filechoose2);
                    productsLineFile.write(productLines);
                    productsLineFile.close();
                }
            } catch (IOException ex) {

            }
        }
    }

//---------------------------------------------------------------------------------------------      
//   left side opreation [ InvoiceHeader ]           
    public void showNewInvoice() {
        newInvoice.setVisible(true);
    }

    public void saveNewInvoice() {

        int number = addAutoNumber();
        String name = newInvoice.NI_name_TF.getText();
        String date = newInvoice.NI_date_TF.getText();
        Date IN_date = null;

        try {
            IN_date = new SimpleDateFormat("dd-mm-yyyy").parse(date);
        } catch (ParseException ex) {

        }

        InvoiceHeader header = new InvoiceHeader(number, IN_date, name);
        InvoicesList.add(header);

        String[] rowData = {number + "", date, name, "0"};
        modelInvoice.addRow(rowData);

        newInvoice.NI_name_TF.setText("");
        newInvoice.NI_date_TF.setText("");

    }

    private void deleteInvoice() {

        int invoiceIndex = invoiceTable.getSelectedRow();
        modelInvoice.removeRow(invoiceIndex);
    }

    //-------------------------------------------------------------------------------------------
    // select row to show deatiels   
    private void chooseRow() {

        modelDetailsInvoice.setRowCount(1);
        int choosedRow = invoiceTable.getSelectedRow();

        if (choosedRow >= 0) {
            InvoiceHeader header = InvoicesList.get(choosedRow);
            invoiceNumber.setText(header.getInvoiceNumber() + "");
            customerNameTF.setText(header.getInvoiceCustomer());
            invoiceDateTF.setText(new SimpleDateFormat("dd-mm-yyyy").format(header.getInvoiceDate()));
            inoviceTotal.setText(header.getTotal() + "");

            modelDetailsInvoice.setRowCount(0);
            fillDetialsInvoiceTable(header.getProducts(), header);
        }
    }

    //------------------------------------------------------------------------
    // utils 
    private int addAutoNumber() {
        int max = 0;
        for (int i = 0; i < InvoicesList.size(); i++) {
            max = Integer.max(max, InvoicesList.get(i).getInvoiceNumber());
        }

        return max + 1;
    }

    private void save_changes() {
        
        String invoices = "";
        String productLines = "";
        int numberChange = Integer.parseInt(modelDetailsInvoice.getValueAt(0, 0)+"");
        List<InvoiceLine> products = new ArrayList<>();
        
        

   // like save invoice function but we replace old values
        for (int i = 0; i < InvoicesList.size(); i++) {
            
            if (InvoicesList.get(i).getInvoiceNumber() == numberChange) {
                invoices += updateHeader(numberChange);
                products = InvoicesList.get(i).getProducts();
            } else {
                invoices += InvoicesList.get(i).getInvoiceNumber() + ",";
                invoices += new SimpleDateFormat("dd-mm-yyyy").format(InvoicesList.get(i).getInvoiceDate()) + ",";
                invoices += InvoicesList.get(i).getInvoiceCustomer() + "\n";
                products = InvoicesList.get(i).getProducts();
            }
            

            for (int x = 0; x < products.size(); x++) {
                if (InvoicesList.get(i).getInvoiceNumber() == numberChange) {
                    productLines += updateLines();

                } else {

                    productLines += InvoicesList.get(i).getInvoiceNumber() + ",";
                    productLines += products.get(x).getProductName() + ",";
                    productLines += products.get(x).getPrice() + ",";
                    productLines += products.get(x).getProductQuintity() + "\n";

                }

            }
        }
        
        FileWriter productsHeaderFile;
        FileWriter productsLineFile;
        try {
            
            productsHeaderFile = new FileWriter(filepathHeader);
            productsHeaderFile.write(invoices);
            productsHeaderFile.close();
            
            productsLineFile = new FileWriter(filepathLine);
            productsLineFile.write(productLines);
            productsLineFile.close();
            
        } catch (IOException ex) {
            Logger.getLogger(InoviceForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        System.out.println("--------------------------------------------");
        System.out.println(invoices);
        System.out.println(productLines);

        
    }
    
    
    
    
    
    private String updateHeader(int num){
        String invoices = "";
        invoices += num + ",";
        invoices += invoiceDateTF.getText() + ",";
        invoices += customerNameTF.getText() + "\n";
        
        return invoices;
    }
    
    
    private String updateLines() {
        String productLines = "";
        for (int count = 0; count < modelDetailsInvoice.getRowCount(); count++) {
            productLines += modelDetailsInvoice.getValueAt(count, 0).toString() + ",";
            productLines += modelDetailsInvoice.getValueAt(count, 1).toString() + ",";
            productLines += modelDetailsInvoice.getValueAt(count, 2).toString() + ",";
            productLines += modelDetailsInvoice.getValueAt(count, 3).toString() + "\n";
        }
        return productLines;
    }

}
