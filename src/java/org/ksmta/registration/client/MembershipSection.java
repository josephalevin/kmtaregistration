/*
 * MembershipSection.java
 *
 * Created on June 15, 2007, 11:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ksmta.registration.client;

import com.google.gwt.user.client.ui.ClickListener;
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
public class MembershipSection extends FormSection{
    protected RadioButton g_member;
    protected RadioButton g_student;
    protected VerticalPanel g_studentSubPanel;
    protected VerticalPanel g_buddySubPanel;
    protected TextBox g_school;
    protected TextBox g_inviter;
    protected RadioButton g_buddy;
    protected RadioButton g_nonmember;
    
    /** Creates a new instance of MembershipSection */
    public MembershipSection(RegistrationData data) {
        super(data);
        
        setTitleHtml("Membership");
        setIntroHtml("Select your membership status with KMTA.");        
        
        VerticalPanel panel = new VerticalPanel();      
        setForm(panel);        
        
        
        //Members
        g_member = new RadioButton("membership", "KMTA Member");        
        g_member.addStyleName("section-label");            
        panel.add(g_member);   
                
//        Label memberDescription = new Label("Members qualify for discounted registration fees if registered before September 7th.");        
//        memberDescription.addStyleName("section-description");
//        panel.add(memberDescription);
        
        //Students
        g_student = new RadioButton("membership", "KMTA Student Chapter Member");        
        g_student.addStyleName("section-label");
        panel.add(g_student);
                
//        Label studentDescription = new Label("Students qualify for discounted registration fees.");        
//        studentDescription.addStyleName("section-description");
//        panel.add(studentDescription);
        
        g_studentSubPanel = new VerticalPanel();
        g_studentSubPanel.addStyleName("section-sub");
        g_studentSubPanel.setVisible(false);        
        panel.add(g_studentSubPanel);
        
        Label schoolLabel = new Label("School Name:");
        schoolLabel.addStyleName("section-label");
        g_studentSubPanel.add(schoolLabel);
        g_school = new TextBox();
        g_school.setVisibleLength(50);
        g_studentSubPanel.add(g_school);        
        
        
        //Buddies
        g_buddy = new RadioButton("membership", "Invited Buddy");
        g_buddy.addStyleName("section-label");
        panel.add(g_buddy);                            
            
//        Label buddyDescription = new Label("Non-members invited by a KMTA member attending the conference qualify for member rates.");        
//        buddyDescription.addStyleName("section-description");
//        panel.add(buddyDescription);
        
        g_buddySubPanel = new VerticalPanel();
        g_buddySubPanel.addStyleName("section-sub");
        g_buddySubPanel.setVisible(false);        
        panel.add(g_buddySubPanel);
        
        Label inviterLabel = new Label("Inviting KMTA Member:");
        inviterLabel.addStyleName("section-label");
        g_buddySubPanel.add(inviterLabel);
        g_inviter = new TextBox();
        g_inviter.setVisibleLength(50);
        g_buddySubPanel.add(g_inviter);  
        
        
        //NonMembers
        g_nonmember = new RadioButton("membership", "Non-Member");        
        g_nonmember.addStyleName("section-label");
        panel.add(g_nonmember);
        
        
        //Click listener
        ClickListener listener = new ClickListener() {
            public void onClick(Widget sender) {
                storeRegistrationData();
            }
        };
        
        g_member.addClickListener(listener);
        g_student.addClickListener(listener);
        g_buddy.addClickListener(listener);
        g_nonmember.addClickListener(listener);                               
        
    }
    
    protected void updateSubPanelVisibility(){
        if (g_student.isChecked()){
            g_studentSubPanel.setVisible(true);
        } else{
            g_studentSubPanel.setVisible(false);
        }
        
        if (g_buddy.isChecked()){
            g_buddySubPanel.setVisible(true);
        } else{
            g_buddySubPanel.setVisible(false);
        }
    }
    
    public void loadRegistrationData() {
        switch(c_data.getType()){
            case RegistrationData.TYPE_MEMBER:
                g_member.setChecked(true);
                break;
            case RegistrationData.TYPE_STUDENT:
                g_student.setChecked(true);
                break;
            case RegistrationData.TYPE_BUDDY:
                g_buddy.setChecked(true);
                break;
            case RegistrationData.TYPE_NONMEMBER:
                g_nonmember.setChecked(true);
                break;
            default:
                g_member.setChecked(false);
                g_student.setChecked(false);
                g_buddy.setChecked(false);
                g_nonmember.setChecked(false);
        }
        
        if(c_data.getType() == RegistrationData.TYPE_STUDENT){
            g_school.setText(c_data.getSchool());
        }
        else{
            g_school.setText("");
            
        }
        
        if(c_data.getType() == RegistrationData.TYPE_BUDDY){
            g_inviter.setText(c_data.getInviter());
        }
        else{
            g_inviter.setText("");
        }
        
        updateSubPanelVisibility();
    }
    
    public void storeRegistrationData() {
        if (g_member.isChecked()){
            c_data.setType(RegistrationData.TYPE_MEMBER);
        } else if(g_student.isChecked()){
            c_data.setType(RegistrationData.TYPE_STUDENT);
            
        } else if (g_buddy.isChecked()){
            c_data.setType(RegistrationData.TYPE_BUDDY);
            
        } else if (g_nonmember.isChecked()){
            c_data.setType(RegistrationData.TYPE_NONMEMBER);
        } else{
            c_data.setType(0);
        }
        
        if(c_data.getType() == RegistrationData.TYPE_STUDENT){
            c_data.setSchool(g_school.getText());
        }
        else{
            c_data.setSchool("");
            
        }
        
        if(c_data.getType() == RegistrationData.TYPE_BUDDY){
            c_data.setInviter(g_inviter.getText());
        }
        else{
            c_data.setInviter("");
        }
    }

    public void validate(List errors) {
        switch(c_data.getType()){
            case RegistrationData.TYPE_MEMBER:                
                break;
            case RegistrationData.TYPE_STUDENT:  
                if (c_data.getSchool() == null || c_data.getSchool().trim().length() == 0){
                    errors.add("School name is required.");
                }
                break;
            case RegistrationData.TYPE_BUDDY:  
                if (c_data.getInviter() == null || c_data.getInviter().trim().length() == 0){
                    errors.add("Name of the inviting KMTA member is required.");
                }
                break;
            case RegistrationData.TYPE_NONMEMBER:                
                break;
            default:
                errors.add("Select membership status.");
        }
    }
    
    
    
}
