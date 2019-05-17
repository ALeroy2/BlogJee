<%--@elvariable id="connexion" type="bean.Connexion"--%>
<%@ page pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexis
  Date: 30/04/2019
  Time: 09:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>My EPSI Blog</title>
  <link rel="stylesheet" type="text/css" href="style.css">


</head>
<body>
<div class="container">

  <div>
    <button id="signin-button">Connexion</button>
    <button id="signup-button">Inscription</button>
  </div>
  <%--@elvariable id="user" type="bean.Utilisateur"--%>
  <div>
    <form method="post" id="signup-form" action="Inscription">
      <input type="text" placeholder="USERNAME" name="Nom" />
      <input type="email" placeholder="EMAIL" name="email" size="20" maxlength="60"/>
      <input type="password" placeholder="PASSWORD" name="motdepasse" size="20" maxlength="60"/>
      <button type="submit" class="submit-button" >Sign up !</button>
    </form>
    <form method="post" id="signin-form" action ="Connection">
      <input type="email" placeholder="USERNAME" id="mail" name="email" value="${user.email}" size="20" maxlength="60"/>
      <input type="password" placeholder="PASSWORD" name="motdepasse"  size="20" maxlength="60"/>
      <button type="submit" class="submit-button" formaction="Inscription" >Sign In</button>
      <p class="${empty connexion.erreurs ? 'succes' : 'erreur'}">${connexion.resultat}</p>
    </form>
  </div>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="index.js"></script>
</body>

</html>
