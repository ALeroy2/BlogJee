<%@ page import="Dao.HSQLdb.BlogDao" %>
<%@ page import="Bean.Blog" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Alexis
  Date: 17/05/2019
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="mystyle.css">
    <meta charset="UTF-8">
    <title>My JSP Page</title>
</head>
<body>
<div class="container">
    <button onclick="location.href='SignOut'" class="signOutButton">Sign
        out</button>
    <%
        out.println("Coucou");
        out.println(session.getAttribute("mail"));
    %>
    <br />
    <%
        BlogDao monBlogDao = new BlogDao();
        List<Blog> mesBlog = monBlogDao.getAllBlogs();
        for(Blog monBlog : mesBlog){
            out.println("<br><div class=\"blocWhite\"><h3>" + monBlog.getTitre() + "</h3><p>"
                    + monBlog.getDescription() + "</p></div>");
        }
    %>

</div>
</body>
</html>