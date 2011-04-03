/*
 * PleaseWaitPanel.java
 *
 * Created on June 23, 2007, 1:21 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ksmta.registration.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *
 * @author Joseph A. Levin
 */
public class PleaseWaitPanel extends VerticalPanel{
    
    /** Creates a new instance of PleaseWaitPanel */
    public PleaseWaitPanel() {
        addStyleName("pleasewait-panel");
        add(new HTML("<center><img src=\"images/loading.gif\"/></center>"));
        //add(new Image("images/loading.gif"));
        add(new Label("Please wait..."));
    }
    
}
