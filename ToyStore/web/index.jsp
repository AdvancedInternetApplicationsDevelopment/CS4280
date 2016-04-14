<%-- 
    Document   : index
    Created on : Mar 28, 2016, 4:12:31 PM
    Author     : suhag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Demo</title>
    </head>
    <body>
        <center>
            <form method="post" action="uploadFile" enctype="multipart/form-data">
                Select file to upload:
                <input type="file" name="uploadFile" />
                <br/>
                <input type="text" name="text" />
                <br/>
                <input type="number" name="number" />
                <br/>
                <input type="submit" value="Upload" />
            </form>
        </center>
    </body>
</html>