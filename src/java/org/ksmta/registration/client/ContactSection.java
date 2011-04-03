/*
 * ContactSection.java
 *
 * Created on June 16, 2007, 2:09 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ksmta.registration.client;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import java.util.List;

/**
 *
 * @author Joseph A. Levin
 */
public class ContactSection extends FormSection{
    protected TextBox g_firstName;
    protected TextBox g_lastName;
    protected TextBox g_email;
    protected TextBox g_phone_a;
    protected TextBox g_phone_b;
    protected TextBox g_phone_c;
    protected TextBox g_address;
    protected TextBox g_city;
    protected TextBox g_state;
    protected TextBox g_zip;
    
    
    
    /** Creates a new instance of ContactSection */
    public ContactSection(RegistrationData data) {
        super(data);
        setTitleHtml("Contact Information");
        
//        Grid grid = new Grid(8, 2);
        Grid grid = new Grid(2, 2);
        setForm(grid);
        
        g_firstName = new TextBox();
        grid.setWidget(0,1, g_firstName);
        grid.setWidget(0,0, new Label("First Name:"));
        g_firstName.setVisibleLength(50);
        
        g_lastName = new TextBox();
        grid.setWidget(1,1, g_lastName);
        grid.setWidget(1,0, new Label("Last Name:"));
        g_lastName.setVisibleLength(50);
        
//        g_email = new TextBox();
//        grid.setWidget(2,1, g_email);
//        grid.setWidget(2,0, new Label("Email:"));
//        g_email.setVisibleLength(50);
//        
//        g_phone_a = new TextBox();
//        g_phone_b = new TextBox();
//        g_phone_c = new TextBox();
//        HorizontalPanel phoneParts = new HorizontalPanel();
//        phoneParts.add(new Label("("));
//        phoneParts.add(g_phone_a);
//        phoneParts.add(new Label(") "));
//        phoneParts.add(g_phone_b);
//        phoneParts.add(new Label("-"));
//        phoneParts.add(g_phone_c);
//        
//        g_phone_a.setMaxLength(3);
//        g_phone_a.setVisibleLength(3);
//        
//        g_phone_b.setMaxLength(3);
//        g_phone_b.setVisibleLength(3);
//        
//        g_phone_c.setMaxLength(4);
//        g_phone_c.setVisibleLength(4);
//        
//        grid.setWidget(3,1, phoneParts);
//        
//        grid.setWidget(3,0, new Label("Phone:"));
//        
//        
//        g_address = new TextBox();
//        grid.setWidget(4,1, g_address);
//        grid.setWidget(4,0, new Label("Address:"));
//        g_address.setVisibleLength(50);
//        
//        g_city = new TextBox();
//        grid.setWidget(5,1, g_city);
//        grid.setWidget(5,0, new Label("City:"));
//        g_city.setVisibleLength(50);
//        
//        g_state = new TextBox();
//        grid.setWidget(6,1, g_state);
//        grid.setWidget(6,0, new Label("State:"));        
//        g_state.setMaxLength(2);
//        g_state.setVisibleLength(2);
//        
//        
//        g_zip = new TextBox();
//        grid.setWidget(7,1, g_zip);
//        grid.setWidget(7,0, new Label("Zip:"));
//        g_zip.setMaxLength(5);
        
    }
    
    public void loadRegistrationData() {
        g_firstName.setText(c_data.getFirstName());
        g_lastName.setText(c_data.getLastName());
//        g_email.setText(c_data.getEmail());
//        g_phone_a.setText(c_data.getPhone_A());
//        g_phone_b.setText(c_data.getPhone_C());
//        g_phone_c.setText(c_data.getPhone_C());
//        g_address.setText(c_data.getAddress());
//        g_city.setText(c_data.getCity());
//        g_state.setText(c_data.getState());
//        g_zip.setText(c_data.getZip());                
    }
    
    public void storeRegistrationData() {
        c_data.setFirstName(g_firstName.getText());
        c_data.setLastName(g_lastName.getText());
//        c_data.setEmail(g_email.getText());
//        c_data.setPhone_A(g_phone_a.getText());
//        c_data.setPhone_B(g_phone_b.getText());
//        c_data.setPhone_C(g_phone_c.getText());
//        c_data.setAddress(g_address.getText());
//        c_data.setCity(g_city.getText());
//        c_data.setState(g_state.getText());
//        c_data.setZip(g_zip.getText());                
    }
    
    public void validate(List errors) {        
        test(errors, c_data.getFirstName(), "First name");
        test(errors, c_data.getLastName(), "Last name");
//        test(errors, c_data.getEmail(), "Email");        
//        test(errors, c_data.getPhone_A(), "Area code");
//        test(errors, c_data.getPhone_B(), "Phone number");
//        test(errors, c_data.getPhone_C(), "Phone number");
//        test(errors, c_data.getAddress(), "Address");
//        test(errors, c_data.getCity(), "City");
//        test(errors, c_data.getState(), "State");
//        test(errors, c_data.getZip(), "Zip");
    }
    
    protected void test(List errors, String value, String prompt){
        if (value == null || value.trim().length() == 0){
            errors.add(prompt + " is required.");
        }
    }
    
    
}
