<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Pages</title>
        
        <link rel="stylesheet" type="text/css" href="registraion_style.css" />
        <meta name='gwt:module' content='org.ksmta.registration.Main=org.ksmta.registration.Main'>
        
    </head>
    <link> 
    <body>
        <div id="registration">
            <div id="loading" title="Please wait as the page loads." align="center">
                <img src="images/loading.gif"/>
                <br>Loading...
            </div>                
            <script language="javascript" src="org.ksmta.registration.Main/gwt.js"></script>  
        </div>
    </body>
</html>
