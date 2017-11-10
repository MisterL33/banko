<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <script
  src="https://code.jquery.com/jquery-3.2.1.js"
  integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  crossorigin="anonymous"></script>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
 <fmt:setBundle basename="Properties.bundle"/>

 
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

</head>
<header>

	<nav>
		<div class="nav-wrapper">
			<a href="#!" class="brand-logo center"><fmt:message key="title"/> </a>
			<ul class="left hide-on-med-and-down">
				<li><a href="/banko/home"><fmt:message key="accueil"/></a></li>
			
				<li class="active">
				<form>
  <select  id="language" name="language" onchange="submit()" class="browser-default">
    <option value="" disabled selected>Choose language</option>
   
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
               
           
  </select>
  </form></li>
			</ul>
				<form id="logout" action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="<fmt:message key="logout"/> " />
</form>
		</div>
	
	</nav>
	
</header>

<style>
#language{
    color: firebrick;
    background: bisque;
    height: 64px;}
    
    #logout{
    float: right;
    cursor: pointer;
   
    }
    
    #logout:hover {
    text-decoration: underline;
    }
	
}
    
</style>