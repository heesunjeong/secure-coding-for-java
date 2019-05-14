<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.*" %>
    <%@ page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
  <% 
  
  	//java class 실행
  	Process process = Runtime.getRuntime().exec("cmd /c start java Hacking");
    OutputStream os = process.getOutputStream();
    InputStream is = process.getInputStream();
    DataInputStream di = new DataInputStream(is);
    String dist = di.readLine();
    
    while(dist != null) {
    	out.println(dist);
    	dist = di.readLine();
    }
    
  %>
</body>
</html>