/*
 * RegistrationForm.java
 *
 * Created on June 13, 2007, 7:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ksmta.registration.client;

import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joseph A. Levin
 */
public class RegistrationForm extends VerticalPanel implements RegistrationDataListener{
    private RegistrationData c_data;
    private ErrorPanel g_errorPanel1;
    private ErrorPanel g_errorPanel2;
    private FormSection[] c_sections;
    
    private PleaseWaitPanel c_waitPanel;
    
    public RegistrationForm(){
        addStyleName("registration-form");
        c_data = new RegistrationData();
        c_data.addChangeListener(this);
        
        //Create error panel1
        g_errorPanel1 = new ErrorPanel();
        g_errorPanel1.setVisible(false);
        add(g_errorPanel1);
        
        c_waitPanel = new PleaseWaitPanel();
        c_waitPanel.setVisible(false);
        add(c_waitPanel);
        
        
        c_sections = new FormSection[5];
        int index = 0;
        c_sections[index++] = new MembershipSection(c_data);
        c_sections[index++] = new ContactSection(c_data);
        c_sections[index++] = new RegistrationSection(c_data);
        c_sections[index++] = new BuddySection(c_data);
        //don't need the lab fee section.
        //c_sections[index++] = new LabFeeSection(c_data);
        c_sections[index++] = new PaymentSection(c_data, this);
        
        for (int i = 0; i < c_sections.length; i++){
            if (c_sections[i] != null){
                add(c_sections[i]);
            }
        }
        
        //Create error panel2
        g_errorPanel2 = new ErrorPanel();
        g_errorPanel2.setVisible(false);
        add(g_errorPanel2);
        
        loadRegistrationData();
        //validateRegistrationData();
    }
    
    public boolean checkout(){       
        storeRegistrationData();
        loadRegistrationData();
        if (validateRegistrationData()){
            //Tell the user we're doing something            
            c_waitPanel.setVisible(true);
            for (int j = 0; j < c_sections.length; j++){
                c_sections[j].setVisible(false);
            }
                        
            
            //valid data, create the form and checkout
            FormPanel form = new FormPanel("_top");
            add(form);
            
            VerticalPanel panel = new VerticalPanel();
            form.setWidget(panel);
            
            form.setAction("https://www.paypal.com/us/cgi-bin/webscr");
            form.setMethod(FormPanel.METHOD_POST);
            
            panel.add(new Hidden("cmd", "_cart"));
//            panel.add(new Hidden("cmd", "_ext-enter"));  //Allows extra information to be passed
//            panel.add(new Hidden("redirect_cmd", "_cart"));  //Redirect to the cart after adding the extra info
            panel.add(new Hidden("upload", "1"));    //1 = uploading an entire cart
            
            //paypal account info
            panel.add(new Hidden("business", "orders@ksmta.org"));
            panel.add(new Hidden("return", "http://www.ksmta.org/registration_thankyou.php"));
            panel.add(new Hidden("cancel_return", "http://www.ksmta.org/registration.php"));
            panel.add(new Hidden("currency_code", "USD"));
            panel.add(new Hidden("image_url", "http://www.ksmta.org/images/paypall_kmta.gif"));
            
            //contact information
//            panel.add(new Hidden("email", c_data.getEmail()));
//            panel.add(new Hidden("first_name", c_data.getFirstName()));
//            panel.add(new Hidden("last_name", c_data.getLastName()));
//            panel.add(new Hidden("address1", c_data.getAddress()));
//            panel.add(new Hidden("city", c_data.getCity()));
//            panel.add(new Hidden("state", c_data.getState()));
//            panel.add(new Hidden("zip", c_data.getZip()));
//            panel.add(new Hidden("day_phone_a", c_data.getPhone_A()));
//            panel.add(new Hidden("day_phone_b", c_data.getPhone_B()));
//            panel.add(new Hidden("day_phone_c", c_data.getPhone_C()));
            
            int i = 1;
            //registration
            String type = "UNKNOWN";
            switch (c_data.getType()){
                case RegistrationData.TYPE_MEMBER:
                    type = "Member";
                    break;
                case RegistrationData.TYPE_STUDENT:
                    type = "Student";
                    panel.add(new Hidden("on1_" + i, "School"));
                    panel.add(new Hidden("os1_" + i, c_data.getSchool()));
                    break;
                case RegistrationData.TYPE_BUDDY:
                    type = "Buddy";
                    panel.add(new Hidden("on1_" + i, "Inviter"));
                    panel.add(new Hidden("os1_" + i, c_data.getInviter()));
                    break;
                case RegistrationData.TYPE_NONMEMBER:
                    type = "Non-Member";
                    break;
            }
            
            panel.add(new Hidden("item_name_" + i, type + " Registration"));            
            int price = PriceMatrix.getPrice(c_data, c_data.getDays());
            panel.add(new Hidden("amount_" + i, price + ".00"));
            
            //add registration name
            panel.add(new Hidden("on0_" + i, "Name"));
            panel.add(new Hidden("os0_" + i, c_data.getFirstName() + " " + c_data.getLastName()));
            
            
            
            //add registration days            
            String days = "UNKNOWN";
            switch (c_data.getDays()){
                case RegistrationData.DAY_BOTH:
                    days = "Friday & Saturday";
                    break;
                    
                case RegistrationData.DAY_FRIDAY:
                    days = "Friday Only";
                    break;
                    
                case RegistrationData.DAY_SATURDAY:
                    days = "Saturday Only";
                    break;
            }
            panel.add(new Hidden("item_number_" + i, days));
            
            //next item
            i++;
            
            
            //buddy registration
            if (c_data.getType() == RegistrationData.TYPE_MEMBER && c_data.getBuddy()){
                panel.add(new Hidden("item_name_" + i, "Buddy Registration"));                
                price = PriceMatrix.getPrice(c_data, c_data.getBuddyDays());
                panel.add(new Hidden("amount_" + i, price + ".00"));
                
                //add registration name
                panel.add(new Hidden("on0_" + i, "Name"));
                panel.add(new Hidden("os0_" + i, c_data.getBuddyName()));
                
                panel.add(new Hidden("on1_" + i, "Inviter"));
                panel.add(new Hidden("os1_" + i, c_data.getFirstName() + " " + c_data.getLastName()));
                
                
                //add registration days                
                days = "UNKNOWN";
                switch (c_data.getBuddyDays()){
                    case RegistrationData.DAY_BOTH:
                        days = "Friday & Saturday";
                        break;
                        
                    case RegistrationData.DAY_FRIDAY:
                        days = "Friday Only";
                        break;
                        
                    case RegistrationData.DAY_SATURDAY:
                        days = "Saturday Only";
                        break;
                }                
                panel.add(new Hidden("item_number_" + i, days));
                
                //next item
                i++;
            }
            
            //lab usage fee
            if (c_data.getEnrollWebsite()){
                panel.add(new Hidden("item_number_" + i, "LAB_FEE"));
                panel.add(new Hidden("item_name_" + i, "Website Design Session Enrollment"));            
                panel.add(new Hidden("amount_" + i, "15.00"));
                i++;
            }
            
            
            //fee
            panel.add(new Hidden("item_name_" + i, "Online Convenience Fee"));            
            panel.add(new Hidden("amount_" + i, "3.00"));
            
            //Used for debugging
            //panel.add(new Hidden("amount_" + i, "0.01"));
            i++;
            
            //submit the form
            form.submit();
            return true;            
            
        } else{
            return false;
        }
    }
    
    public void loadRegistrationData(){
        for (int i = 0; i < c_sections.length; i++){
            if (c_sections[i] != null){
                c_sections[i].loadRegistrationData();
            }
        }
    }
    
    public void storeRegistrationData(){
        for (int i = 0; i < c_sections.length; i++){
            if (c_sections[i] != null){
                c_sections[i].storeRegistrationData();
            }
        }
    }
    
    public boolean validateRegistrationData(){
        List errors = new ArrayList();
        for (int i = 0; i < c_sections.length; i++){
            if (c_sections[i] != null){
                c_sections[i].validate(errors);
            }
        }
        
        if (errors.size() > 0){
            g_errorPanel1.setErrors(errors);
            g_errorPanel1.setVisible(true);
            g_errorPanel2.setErrors(errors);
            g_errorPanel2.setVisible(true);
            return false;
        } else{
            g_errorPanel1.setVisible(false);
            g_errorPanel2.setVisible(false);
            return true;
        }
    }
    
    boolean lock = false;
    public void changeNotify() {
        if (lock){return;}
        
        lock = true;
        storeRegistrationData();
        lock = false;
        loadRegistrationData();
    }
}
