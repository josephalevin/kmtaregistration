/*
 * RegistrationData.java
 *
 * Created on June 11, 2007, 7:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ksmta.registration.client;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joseph A. Levin
 */
public class RegistrationData {
    
    public static final int TYPE_MEMBER = 1;
    public static final int TYPE_STUDENT = 2;
    public static final int TYPE_BUDDY = 3;
    public static final int TYPE_NONMEMBER = 4;
    
    public static final int DAY_BOTH = 1;
    public static final int DAY_FRIDAY = 2;
    public static final int DAY_SATURDAY = 3;
    
    protected List c_listeners;
    
    private int c_type;
    private String c_school;
    private String c_inviter;
    
    private String c_firstName;
    private String c_lastName;
    private String c_email;
    private String c_phone_a;
    private String c_phone_b;
    private String c_phone_c;
    private String c_address;
    private String c_city;
    private String c_state;
    private String c_zip;
    
    private int c_days;            
    
    private int c_buddyDays; 
    private boolean c_buddy;   
    private boolean c_enrollWebsite;
    private String c_buddyName;
    
    
    /**
     * Creates a new instance of RegistrationData
     */
    public RegistrationData() {
        c_type = TYPE_MEMBER;         
        c_school = "";
        c_inviter = "";
        
        c_firstName = "";
        c_lastName = "";
        c_email = "";
        c_phone_a = "";
        c_phone_b = "";
        c_phone_c = "";
        c_address = "";
        c_city = "";
        c_state = "KS";
        c_zip = "";
        
        c_days = DAY_BOTH;      
        
        c_buddy = false;
        c_enrollWebsite = false;
        c_buddyDays = 0;
        c_buddyName = "";
        
    }
    
    public int getType(){
        return c_type;
    }
    
    public void setType(int type){
        c_type = type;
        fireChangeNotify();
    }
    
    public String getSchool(){
        return c_school;
    }
    
    public void setSchool(String school){
        c_school = school;
    }
    
    public String getInviter(){
        return c_inviter;
    }
    
    public void setInviter(String inviter){
        c_inviter = inviter;
    }
    
    public String getFirstName(){
        return c_firstName;
    }
    
    public void setFirstName(String first){
        c_firstName = first;
    }
    
    public String getLastName(){
        return c_lastName;
    }
    
    public void setLastName(String last){
        c_lastName = last;
    }
    
    public String getEmail(){
        return c_email;
    }
    
    public void setEmail(String email){
        c_email = email;
    }
    
    public String getPhone_A() {
        return c_phone_a;
    }
    
    public void setPhone_A(String a) {
        c_phone_a = a;
    }
    
    public String getPhone_B() {
        return c_phone_b;
    }
    
    public void setPhone_B(String b) {
        c_phone_b = b;
    }
    
    public String getPhone_C() {
        return c_phone_c;
    }
    
    public void setPhone_C(String c) {
        c_phone_c = c;
    }
    
    
    
    public String getAddress() {
        return c_address;
    }
    
    public void setAddress(String address) {
        c_address = address;
    }
    
    public String getCity() {
        return c_city;
    }
    
    public void setCity(String city) {
        c_city = city;
    }
    
    public String getState() {
        return c_state;
    }
    
    public void setState(String state) {
        c_state = state;
    }
    
    public String getZip() {
        return c_zip;
    }
    
    public void setZip(String zip) {
        c_zip = zip;
    }
    
    public int getDays(){
        return c_days;
    }
    
    public void setDays(int days){
        c_days = days;
        fireChangeNotify();
    }
    
    public int getBuddyDays(){
        return c_buddyDays;
    }
    
    public void setBuddyDays(int days){
        c_buddyDays = days;
        fireChangeNotify();
    }
    
    public boolean getBuddy(){
        return c_buddy;
    }
    
    public void setBuddy(boolean buddy){
        c_buddy = buddy;
        fireChangeNotify();
    }
    
    public boolean getEnrollWebsite(){
        return c_enrollWebsite;
    }
    
    public void setEnrollWebsite(boolean enroll){
        c_enrollWebsite = enroll;
        fireChangeNotify();
    }
    
    public String getBuddyName(){
        return c_buddyName;
    }   
    
    public void setBuddyName(String name){
        c_buddyName = name;
    }
    
    protected void fireChangeNotify(){
        if (c_listeners != null){
            for (int i = 0; i < c_listeners.size(); i++){
                ((RegistrationDataListener) c_listeners.get(i)).changeNotify();
            }
        }
    }
    
    public void addChangeListener(RegistrationDataListener listener){
        if (c_listeners == null){
            c_listeners = new ArrayList();
        }
        c_listeners.add(listener);
    }
   
    
    public int getTotal(){
        int total = 0;
        //Registration price
        total += PriceMatrix.getPrice(this, getDays());
        
        //Buddy's price
        if (getType() == TYPE_MEMBER && getBuddy()){
            total += PriceMatrix.getPrice(this, getBuddyDays());
        }
        
        //lab usage fee, such a dirty hack!
        if (getEnrollWebsite()){
            total += 15;
        }
        
        //Online fee
        total += 3;
        return total;
    }
}
