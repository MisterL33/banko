<%@include file="header.jsp"%>
<%
	String nom = (String) session.getAttribute("nom");
	String prenom = (String) session.getAttribute("prenom");
	Integer numeroCompte = (Integer) session.getAttribute("numeroCompte");
	String libelleCompte = (String) session.getAttribute("libelleCompte");
%>
<body>

<h2> <fmt:message key="menu"/></h2>
	<p style="text-align: center;">
		<fmt:message key="bonjour" />
		<%
			out.println(nom);
			out.println(prenom);
		%>
	</p>
	
 <p id="listAccount">	<fmt:message key="list"/> : </p>
<ul class="collection">
	<c:forEach items="${comptes}" var="comptes">
	<li class="collection-item" >	<a href="compte?id=${comptes.numero}&page=1"> <c:out
				value=" ${comptes.libelle} - :   ${comptes.solde} euro" /></a> </li>
		
	</c:forEach>
</ul>

<ul id="buttonList">

	<li><a  class="waves-effect waves-light btn" href="/banko/virement"><fmt:message key="virement"/></a> </li>
	
	
<li>	<a class="waves-effect waves-light btn" href="/banko/reset"><fmt:message key="resetPass"/></a> </li>
<li>	<a class="waves-effect waves-light btn" href="/banko/newAccount"><fmt:message key="newCompte"/></a> </li>
</ul>
</body>
</html>

<style>
h2
{
text-align: center;
}

#listAccount{
text-align: center;
}

#buttonList li{
text-align: center;
margin-top: 20px;
margin-bottom: 20px;
}

.collection{
width: 50%;
margin-bottom: 50px;
margin: auto;
}
</style>