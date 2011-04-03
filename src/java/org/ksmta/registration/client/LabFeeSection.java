/*
 * BuddySection.java
 *
 * Created on June 16, 2007, 2:12 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ksmta.registration.client;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;

/**
 *
 * @author Joseph A. Levin
 */
public class LabFeeSection extends FormSection{
    protected CheckBox g_enroll;
    
    /** Creates a new instance of LabFeeSection */
    public LabFeeSection(RegistrationData data) {
        super(data);
        
        setTitleHtml("Website Design Session");       
        setIntroHtml("For those enrolling in website design session there is an additional college lab usage fee of $15.");
        
        VerticalPanel panel = new VerticalPanel();
        setForm(panel);
        
        g_enroll = new CheckBox("Enroll in the website design session.");
        g_enroll.addStyleName("section-label");
        panel.add(g_enroll);
        

        
        g_enroll.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                storeRegistrationData();
            }
        });
        
        
        
        
    }
    
    
    public void loadRegistrationData() {
        g_enroll.setChecked(c_data.getEnrollWebsite());       
    }
    
    public void storeRegistrationData() {
        if (g_enroll.isChecked()){ 
            c_data.setEnrollWebsite(true);
        }
        else{
            c_data.setEnrollWebsite(false);            
        }

    }
    
    public void validate(List errors) { 
        //nothing to validate
    }

    
}

