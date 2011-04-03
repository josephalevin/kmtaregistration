/*
 * FormSection.java
 *
 * Created on June 15, 2007, 10:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ksmta.registration.client;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;

/**
 *
 * @author Joseph A. Levin
 */
public abstract class FormSection extends DockPanel{
    protected HTML c_titleHtml;
    protected HTML c_introHtml;
    protected Widget c_form;
    protected RegistrationData c_data;
    
    /** Creates a new instance of FormSection */
    public FormSection(RegistrationData data) {
        c_data = data;
        addStyleName("section");
        //setWidth("100%");
        
        c_titleHtml = new HTML();
        add(c_titleHtml, DockPanel.NORTH);
        c_titleHtml.addStyleName("section-title");        
        c_titleHtml.setWordWrap(false);
        
        c_introHtml = new HTML();
        add(c_introHtml, DockPanel.NORTH);
        c_introHtml.addStyleName("section-intro");        
        c_introHtml.setWordWrap(true);
        
//        HTML leftSpacer = new HTML();        
//        add(leftSpacer, DockPanel.WEST);
//        leftSpacer.addStyleName("section-leftspacer");                
        
        HTML bottomSpacer = new HTML();        
        add(bottomSpacer, DockPanel.SOUTH);
        bottomSpacer.addStyleName("section-bottomspacer");        
                
        
    }
    
    public void setTitleHtml(String html){
        c_titleHtml.setHTML(html);
    }
    
    public String getTitleHtml(){
        return c_titleHtml.getHTML();
    }
    
    public void setIntroHtml(String html){
        c_introHtml.setHTML(html);
    }
    
    public String getIntroHtml(){
        return c_introHtml.getHTML();
    }
    
    public Widget getForm(){
        return c_form;
    }
    
    public void setForm(Widget form){
        if (c_form != null){
            c_form.removeStyleName("section-form");
        }
        c_form = form;
        add(c_form, DockPanel.WEST);
        c_form.addStyleName("section-form");
    }
    
    public abstract void loadRegistrationData();
    
    public abstract void storeRegistrationData();
    
    public abstract void validate(List errors);
    
    
}
