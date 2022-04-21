/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sales.invoice.generator.design;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author hussein
 */
public class NewInvoice extends JFrame {
public JTextField NI_name_TF , NI_date_TF;
private JLabel NI_name_LB , NI_date_LB , temp;
private JButton NI_addNI_BTN;
private JPanel panel;

public NewInvoice(InoviceForm form) {
NI_name_LB = new JLabel("Customer name");
NI_date_LB = new JLabel("Invoice date");
temp = new JLabel();
NI_name_TF = new JTextField();
NI_date_TF = new JTextField();


NI_addNI_BTN = new JButton("Add new invoice");
NI_addNI_BTN.setActionCommand("add_new_invoice");
NI_addNI_BTN.addActionListener(form);

this.setLayout(new GridLayout(3, 2));

this.add(NI_date_LB);
this.add(NI_date_TF);
this.add(NI_name_LB);
this.add(NI_name_TF);
this.add(NI_addNI_BTN);
this.add(temp);


this.setSize(350, 100);
this.setDefaultCloseOperation(EXIT_ON_CLOSE);


}

    
}
