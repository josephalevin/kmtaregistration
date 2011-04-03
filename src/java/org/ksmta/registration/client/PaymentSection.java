/*
 * PaymentSection.java
 *
 * Created on June 16, 2007, 2:17 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ksmta.registration.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;

/**
 *
 * @author Joseph A. Levin
 */
public class PaymentSection extends FormSection{
    protected Label g_total;
    protected RegistrationForm c_form;
    /**
     * Creates a new instance of PaymentSection
     */
    public PaymentSection(RegistrationData data, RegistrationForm form) {
        super(data);
        c_form = form;
        setTitleHtml("Payment");
        setIntroHtml("Complete your online registration by clicking the checkout button below.  A $3.00 online convenience fee is included in the total.");
        VerticalPanel panel = new VerticalPanel();
        setForm(panel);
        
        g_total = new Label("$0.00");
        g_total.addStyleName("registration-total");
        panel.add(g_total);
        
        final Button checkout = new Button("Checkout");
        panel.add(checkout);
        
        checkout.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                checkout.setEnabled(false);
                if (!c_form.checkout()){
                    checkout.setEnabled(true);
                }                
            }
        });
        
        
    }
    
    protected String formatPrice(int price){
        return "$" + price + ".00";
    }
    
    protected void updateTotal(){        
        g_total.setText(formatPrice(c_data.getTotal()));
    }
        
    
    public void loadRegistrationData() {
        updateTotal();
    }
    
    public void storeRegistrationData() {
    }
    
    public void validate(List errors) {
    }
    
}
