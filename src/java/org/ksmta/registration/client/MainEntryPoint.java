/*
 * MainEntryPoint.java
 *
 * Created on June 9, 2007, 1:00 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ksmta.registration.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *
 * @author Joseph A. Levin
 */
public class MainEntryPoint implements EntryPoint {
    
    /** Creates a new instance of MainEntryPoint */
    public MainEntryPoint() {
    }
    
    /**
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    public void onModuleLoad() {        
        final VerticalPanel mainPanel = new VerticalPanel();
        RootPanel root = RootPanel.get("registration");
        if (root == null){
            root = RootPanel.get();
        }
        root.add(new RegistrationForm());               

        
        //TODO: Figure out how to hide a div element.
        try{
        hide("loading");
        }
        catch(Exception e){
            //Fail silently
        }
    }    
    
    private static native void hide(String id) /*-{
        var elem, vis;
        if( $doc.getElementById ) // this is the way the standards work
            elem = $doc.getElementById( id );
        else if( $doc.all ) // this is the way old msie versions work
            elem = $doc.all[id];
        else if( $doc.layers ) // this is the way nn4 works
            elem = $doc.layers[id];
        vis = elem.style;
        // if the style.display value is blank we try to figure it out here
        if(vis.display==''&&elem.offsetWidth!=undefined&&elem.offsetHeight!=undefined)
        vis.display = (elem.offsetWidth!=0&&elem.offsetHeight!=0)?'block':'none';
        vis.display = (vis.display==''||vis.display=='block')?'none':'block';                                
     }-*/;    
    
}
