/*
 * PriceMatrix.java
 *
 * Created on June 18, 2007, 6:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ksmta.registration.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;

/**
 *
 * @author Joseph A. Levin
 */
public class PriceMatrix {
          
    static{
        DateTimeFormat format = DateTimeFormat.getFormat("yyyy-MM-dd");
        DEADLINE = format.parse("2011-05-16");

    }
    
    public static final Date DEADLINE;
    
    
    //DEBUG    
    private static final boolean QUALIFY_DEADLINE = new Date().before(DEADLINE);

    
    //Empty, Both, Friday, Saturday
    
    private static final int[] EARLY_FEE = new int[] {0, 75, 60, 50};
    private static final int[] NORMAL_FEE = new int[] {0, 90, 75, 65};
    private static final int[] STUDENT_FEE = new int[] {0, 20, 15, 10};
    /** Creates a new instance of PriceMatrix */
    public PriceMatrix() {
    }
    
    
    private static boolean qualifyEarly(RegistrationData data){        
        if (QUALIFY_DEADLINE){
            if (data.getType() == RegistrationData.TYPE_MEMBER || data.getType() == RegistrationData.TYPE_BUDDY){
                return true;
            }
        }
        return false;
    }
    
    public static int getPrice(RegistrationData data, int day){
        switch (day){
            case RegistrationData.DAY_BOTH:
                return getBothPrice(data);
            case RegistrationData.DAY_FRIDAY:
                return getFridayPrice(data);
            case RegistrationData.DAY_SATURDAY:
                return getSaturdayPrice(data);
            default:
                return 0;
        }
    }
    
    public static int getBothPrice(RegistrationData data){
        int[] fee;
        if (qualifyEarly(data)){
            fee = EARLY_FEE;
        } else{
            fee = NORMAL_FEE;
        }                
        
        switch(data.getType()){
            case RegistrationData.TYPE_MEMBER:
                return fee[RegistrationData.DAY_BOTH];
            case RegistrationData.TYPE_STUDENT:
                return STUDENT_FEE[RegistrationData.DAY_BOTH];
            case RegistrationData.TYPE_BUDDY:
                return fee[RegistrationData.DAY_BOTH];
            case RegistrationData.TYPE_NONMEMBER:
                return fee[RegistrationData.DAY_BOTH];
            default:
                return NORMAL_FEE[RegistrationData.DAY_BOTH];
        }
    }
    
    public static int getFridayPrice(RegistrationData data){
        int[] fee;
        if (qualifyEarly(data)){
            fee = EARLY_FEE;
        } else{
            fee = NORMAL_FEE;
        }                
        
        switch(data.getType()){
            case RegistrationData.TYPE_MEMBER:
                return fee[RegistrationData.DAY_FRIDAY];
            case RegistrationData.TYPE_STUDENT:
                return STUDENT_FEE[RegistrationData.DAY_FRIDAY];
            case RegistrationData.TYPE_BUDDY:
                return fee[RegistrationData.DAY_FRIDAY];
            case RegistrationData.TYPE_NONMEMBER:
                return fee[RegistrationData.DAY_FRIDAY];
            default:
                return NORMAL_FEE[RegistrationData.DAY_FRIDAY];
        }
    }
    
    public static int getSaturdayPrice(RegistrationData data){
        int[] fee;
        if (qualifyEarly(data)){
            fee = EARLY_FEE;
        } else{
            fee = NORMAL_FEE;
        }                
        
        switch(data.getType()){
            case RegistrationData.TYPE_MEMBER:
                return fee[RegistrationData.DAY_SATURDAY];
            case RegistrationData.TYPE_STUDENT:
                return STUDENT_FEE[RegistrationData.DAY_SATURDAY];
            case RegistrationData.TYPE_BUDDY:
                return fee[RegistrationData.DAY_SATURDAY];
            case RegistrationData.TYPE_NONMEMBER:
                return fee[RegistrationData.DAY_SATURDAY];
            default:
                return NORMAL_FEE[RegistrationData.DAY_SATURDAY];
        }
    }
    
}
