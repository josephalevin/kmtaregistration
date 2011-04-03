/*
 * ErrorPanel.java
 *
 * Created on June 16, 2007, 1:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ksmta.registration.client;

import com.google.gwt.user.client.ui.HTML;
import java.util.List;

/**
 *
 * @author Joseph A. Levin
 */
public class ErrorPanel extends HTML{
    
    /** Creates a new instance of ErrorPanel */
    public ErrorPanel() {        
        addStyleName("error-panel");
        setWordWrap(true);
    }
    
    public void setErrors(List errors){        
        String html = "<ul>";
        for (int i = 0; i < errors.size(); i++){
            String error = (String) errors.get(i);
            html = html + "<li>" + error + "</li>";
        }
        html = html + "</ul>";
        setHTML(html);
        
    }
    
}
