<%@include file="header.jsp" %>
<%
	String success = (String) request.getAttribute("virementBool");
%>
<body>

<div id="virementContent">
 <div class="row">
        <div class="col s12 m6" style="float: none; margin: auto;     margin-top: 50px;">
          <div class="card" >
            <div class="card-content">
              <span class="card-title"><fmt:message key="form"/> </span>
<form method="post" action="virement">
  <label><fmt:message key="compte"/>  <fmt:message key="emetteur"/> </label>
  <select name="idEmetteur" class="browser-default">
<c:forEach items="${emetteurs}" var="emetteurs">

  <option  value="${emetteurs.numero}">${emetteurs.libelle} - ${emetteurs.solde}</option>
  
  </c:forEach>
  </select>



  <label><fmt:message key="compte"/>  <fmt:message key="recepteur"/>  </label>
  <select name="idRecepteur" class="browser-default">
<c:forEach items="${recepteurs}" var="recepteurs">

  <option  value="${recepteurs.numero}">${recepteurs.libelle} - ${recepteurs.solde}</option>
  
  </c:forEach>
  </select>
  <label><fmt:message key="montant"/>  : </label>
  <input name="montantTransac"></input>
  <label><fmt:message key="motif"/>  : </label>
  <input name="libelleTransac"></input>
  
  <button name="envoiTransac"><fmt:message key="envoyer"/> </button>
  
  </form>
  <label>${success}</label>
</div>
</div>
</div>
</div>
</div>
</body>
</html>

<style>
#virementContent
{
    width: 50%;
    top: 120px;
    position: relative;
    margin: auto;
}

.card span{
color: white;
}

.card label{
color: white;
}

.card{
background-color: #ee6e73;
}

</style>