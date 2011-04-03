/*
 * RegistrationSection.java
 *
 * Created on June 16, 2007, 2:11 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ksmta.registration.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;

/**
 *
 * @author Joseph A. Levin
 */
public class RegistrationSection extends FormSection{
    protected RadioButton g_both;
    protected RadioButton g_friday;
    protected RadioButton g_saturday;  
    
    protected Label g_bothPrice;
    protected Label g_fridayPrice;
    protected Label g_saturdayPrice;
    
    /** Creates a new instance of RegistrationSection */
    public RegistrationSection(RegistrationData data) {
        super(data);
        setTitleHtml("Registration");        
        
        Grid grid = new Grid(3,2);
        setForm(grid);
        
        g_both = new RadioButton("registration", "Friday & Saturday");
        g_both.addStyleName("section-label");
        g_bothPrice = new Label();
        grid.setWidget(0,0,g_both);
        grid.setWidget(0,1,g_bothPrice);
        
        g_friday = new RadioButton("registration", "Friday Only");
        g_friday.addStyleName("section-label");
        g_fridayPrice = new Label();
        grid.setWidget(1,0,g_friday);
        grid.setWidget(1,1,g_fridayPrice);
        
        g_saturday = new RadioButton("registration", "Saturday Only");
        g_saturday.addStyleName("section-label");
        g_saturdayPrice = new Label();
        grid.setWidget(2,0,g_saturday);
        grid.setWidget(2,1,g_saturdayPrice);
        
        ClickListener listener = new ClickListener() {
            public void onClick(Widget sender) {
                storeRegistrationData();
            }
        };
        
        g_both.addClickListener(listener);
//        g_both.setChecked(true);
//        g_both.setEnabled(false);
//        g_friday.setVisible(false);
//        g_saturday.setVisible(false);
        g_friday.addClickListener(listener);
        g_saturday.addClickListener(listener);

        
    }
    
    protected void updateIntro(){
        DateTimeFormat format = DateTimeFormat.getFormat("MMMM dd, yyyy");
        switch(c_data.getType()){
            case RegistrationData.TYPE_MEMBER:
                setIntroHtml("Select the conference days you will attend.  Members enjoy discounted rates if registered by " + format.format(PriceMatrix.DEADLINE) + ".");
                break;
            case RegistrationData.TYPE_STUDENT:
                setIntroHtml("Please indicate below which days you will attend.  Student members enjoy discounted rates.");
                break;    
            case RegistrationData.TYPE_BUDDY:
                setIntroHtml("Select the conference days you will attend.  Invited buddies enjoy discounted rates if registered by " + format.format(PriceMatrix.DEADLINE) + ".");
                break;    
            case RegistrationData.TYPE_NONMEMBER:
                setIntroHtml("Select the conference days you will attend.");
                break;
            default:
                setIntroHtml("Select your membership type.");
                break;
                
        }
    }
    
    protected String formatPrice(int price){
        return "$" + price + ".00";
    }
    
    protected void updatePrices(){
        g_bothPrice.setText(formatPrice(PriceMatrix.getBothPrice(c_data)));
        g_fridayPrice.setText(formatPrice(PriceMatrix.getFridayPrice(c_data)));
        g_saturdayPrice.setText(formatPrice(PriceMatrix.getSaturdayPrice(c_data)));
    }
    

    public void loadRegistrationData() {
        switch(c_data.getDays()){
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
        updateIntro();
        updatePrices();
    }

    public void storeRegistrationData() {
        if (g_both.isChecked()){
            c_data.setDays(RegistrationData.DAY_BOTH);            
        }
        else if (g_friday.isChecked()){
            c_data.setDays(RegistrationData.DAY_FRIDAY);
        }
        else if (g_saturday.isChecked()){
            c_data.setDays(RegistrationData.DAY_SATURDAY);
        }
    }

    public void validate(List errors) {
    }
    
}
