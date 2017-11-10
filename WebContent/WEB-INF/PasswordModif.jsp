<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp" %>
    
    <%
	String validator = (String) request.getAttribute("validator");
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

  <div class="row">
    <form class="col s12" method="post">
      <div class="row">
        <div class="input-field col s6">
         
          <input id="icon_prefix" placeholder="Nouveau mot de passe" name="password" type="password" class="validate">
          
        </div>
        <div class="input-field col s6">
         
          <input name="password2" placeholder="Retapez" id="icon_telephone" type="password" class="validate">
         
        </div>
        
         <input style="display: block;width: 100%;" type="submit" value="valider" class="waves-effect waves-light btn" />
        <p>${validator}</p>
      </div>
    </form>
  </div>
        

</body>
</html>

<script type="text/javascript">

</script>
</script>