<%--
  Created by IntelliJ IDEA.
  User: syk
  Date: 2018/8/19
  Time: 下午10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h1>上传附件</h1>
    <form method="post" action="/home/doUpload" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" />
    </form>
</body>
</html>
