
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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;

/**
 *
 * @author Joseph A. Levin
 */
public class BuddySection extends FormSection{
    protected CheckBox g_bringBuddy;
    protected VerticalPanel g_buddySubPanel;
    protected TextBox g_buddyName;
    
    protected RadioButton g_both;
    protected RadioButton g_friday;
    protected RadioButton g_saturday; 
    
    /** Creates a new instance of BuddySection */
    public BuddySection(RegistrationData data) {
        super(data);
        
        setTitleHtml("Bring a Buddy");        
        
        VerticalPanel panel = new VerticalPanel();
        setForm(panel);
        
        g_bringBuddy = new CheckBox("Bring a Buddy");
        g_bringBuddy.addStyleName("section-label");
        panel.add(g_bringBuddy);
        
        g_buddySubPanel = new VerticalPanel();
        g_buddySubPanel.addStyleName("section-sub");
        g_buddySubPanel.setVisible(false);
        panel.add(g_buddySubPanel);
        
        Label buddyLabel = new Label("Buddy's Name:");
        buddyLabel.addStyleName("section-label");
        g_buddySubPanel.add(buddyLabel);
        
        g_buddyName = new TextBox();
        g_buddyName.setVisibleLength(50);
        g_buddySubPanel.add(g_buddyName);
        
        g_bringBuddy.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                storeRegistrationData();
            }
        });
        
        g_buddySubPanel.add(new Label("Buddy's Days:"));
        
        HorizontalPanel dayPanel = new HorizontalPanel();
        g_buddySubPanel.add(dayPanel);
        
        g_both = new RadioButton("buddy days", "Friday & Saturday");
        dayPanel.add(g_both);
                
        g_friday = new RadioButton("buddy days", "Friday Only");
        dayPanel.add(g_friday);
        
       g_saturday = new RadioButton("buddy days", "Saturday Only");
        dayPanel.add(g_saturday);
        
        ClickListener listener = new ClickListener() {
            public void onClick(Widget sender) {
                storeRegistrationData();
            }
        };
        
        g_both.addClickListener(listener);
        //g_both.setChecked(true);
//        g_both.setEnabled(false);
//        g_friday.setVisible(false);
//        g_saturday.setVisible(false);
        g_friday.addClickListener(listener);
        g_saturday.addClickListener(listener);
        
        
    }
    
    protected void updateAvalibility(){
        if (c_data.getType() == RegistrationData.TYPE_MEMBER){
            setIntroHtml("KMTA welcomes non-members to attend the conference as an invited \"buddy\" of a member teacher. As an invited buddy, non-members pay the same registration rate as members.  Each buddy is encouraged to send their registration information along with their inviting KMTA member's registration.");
            getForm().setVisible(true);
        }
        else{
            setIntroHtml("Bringing a buddy is available to KMTA members only.");
            getForm().setVisible(false);
        }
    }
    
    protected void updateSubPanelVisibility(){
        
        if (c_data.getBuddy()){
            g_buddySubPanel.setVisible(true);
        } else{
            g_buddySubPanel.setVisible(false);
        }  
    }
    
    public void loadRegistrationData() {
        g_bringBuddy.setChecked(c_data.getBuddy());
        int days = c_data.getBuddyDays();
        if (days == 0){
            days = c_data.getDays();
        }
        switch(days){
            case RegistrationData.DAY_BOTH:
                g_both.setChecked(true);
                break;
            case RegistrationData.DAY_FRIDAY:
                g_friday.setChecked(true);
                break;
            case RegistrationData.DAY_SATURDAY:
                g_saturday.setChecked(true);
                break;
        }
        
        g_buddyName.setText(c_data.getBuddyName());
        updateAvalibility();
        updateSubPanelVisibility();
    }
    
    public void storeRegistrationData() {
        if (g_bringBuddy.isChecked()){ 
            c_data.setBuddy(true);
            c_data.setBuddyName(g_buddyName.getText());
            if (g_both.isChecked()){                
                c_data.setBuddyDays(RegistrationData.DAY_BOTH);            
            }
            else if (g_friday.isChecked()){
                c_data.setBuddyDays(RegistrationData.DAY_FRIDAY);
            }
            else if (g_saturday.isChecked()){
                c_data.setBuddyDays(RegistrationData.DAY_SATURDAY);
            }
            else{
                c_data.setBuddyDays(0);
            }
        }
        else{
            c_data.setBuddy(false);            
        }

    }
    
    public void validate(List errors) {
        if (c_data.getType() == RegistrationData.TYPE_MEMBER && c_data.getBuddy()){
            test(errors, c_data.getBuddyName(), "Buddy's name");
        }
    }
    
    protected void test(List errors, String value, String prompt){
        if (value == null || value.trim().length() == 0){
            errors.add(prompt + " is required.");
        }
    }
    
}
