<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/6/6
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <script src="js/jquery-2.1.3.min.js"></script>
  <script src="js/bootstrap.min.js"></script>


  <style type="text/css">
    .form-signin {
      max-width: 400px;
      padding: 25px 29px 25px;
      margin: 300px auto 20px;
      background-color: rgba(256,256,256,0.2);

      border: 1px solid #a0a0a0;
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
      -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.95);
      -moz-box-shadow: 0 1px 2px rgba(0,0,0,.95);
      box-shadow: 0 1px 2px rgba(0,0,0,.95);
    }

    .form-signin input[type="text"],
    .form-signin input[type="password"] {
      font-size: 15px;
      width:90%;
      height:auto;
      margin-bottom: 15px;
      padding: 7px 9px;
    }

    .footer {
      position: absolute;
      bottom: 20px;
      width: 100%;
      color: #000000;
      text-align:center;
    }

    #info{
      color:red;
      visibility:hidden;
    }

    #sign{
      font-family:微软雅黑;
      font-size:50px;
      color:#D2691E;
      text-align:center;
      padding-top:90px;
      padding-bottom:50px;
    }

    .glyphicon {
      color:white;
    }

  </style>

  <script>
    function login(){ }
  </script>

  <title>登录</title>
</head>

<body>
<div style="position:absolute; width:100%; height:100%; z-index:-1; left:0; top:0;">
  <img src="images/bg.jpg" style="width:100%; height:100%; position:absolute; top:0; left:0;">
</div>

<form class="form-signin" action="studio.html" method="post">
  <span class="glyphicon glyphicon-user"></span>
  <input type="text" placeholder="账 号"><br>
  <span class="glyphicon glyphicon-lock"></span>
  <input type="password" placeholder="密 码">

  <table width="100%">
    <tr><td align="center">
      <span id="info">账号必须填写</span></td>
      <td align="right"><a href="info.html">忘记密码</a></td>
    </tr></table>

  <button class="btn btn-block btn-default active" type="submit"
          onsubmit="login()" style="margin-top:15px">登 录</button>
</form>

<footer class="footer">西安邮电大学 计算机学院</footer>

</body>
</html>
