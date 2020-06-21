
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>

<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="net.sf.jasperreports.view.JasperViewer"%>

<%@page import="javax.servlet.ServletResponse"%>
<%@include file="Conexion.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
         File reportfile= new File(application.getRealPath("producto.jasper"));
         
         Map<String, Object> parameter = new HashMap<String, Object>();
         
        byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), parameter, con);
         
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream ouputstream = response.getOutputStream();
        ouputstream.write(bytes,0,bytes.length);

        ouputstream.flush();
        ouputstream.close();
        %>
    </body>
</html>
