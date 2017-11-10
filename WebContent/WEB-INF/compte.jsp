<%@include file="header.jsp" %>

<%       
String nom = (String) session.getAttribute("nom");
String prenom = (String) session.getAttribute("prenom");       
Integer numeroCompte = (Integer) request.getAttribute("numeroCompte");       
String libelleCompte = (String) request.getAttribute("libelleCompte");    
Integer nbPage = (Integer) request.getAttribute("nbPage");
           			 %>
           			 
<body>
<p><fmt:message key="bonjour"/> <%  out.println( nom ); out.println( prenom );  %></p>
	<p>Liste des transactions du compte numéro : <%  out.println(numeroCompte);%> et ayant pour libelle :<%  out.println(libelleCompte);  %> </p>
	
	
	<div id="results"><!-- content will be loaded here -->
	<ul class="collection">
  <c:forEach items="${transacPaginate}" var="transacPaginate">


 <li class="collection-item" >  <c:out value=" ${transacPaginate.libelle} || ${transacPaginate.date} ||   ${transacPaginate.montant} euros"/>
 
 </li> 
  </c:forEach>
     </ul>
   
<div name='test' id="paginate">
<% for(int i = 0; i < nbPage; i+=1) { %>

       <a href="compte?id=<% out.println(numeroCompte); %>&page=<%=i+1%>"><%=i+1 %></a>
    <% } %>

</div>

           
           
           
</body>
</html>


<style>

.collection
{
width: 50%;
margin: auto;
}

#paginate{
    text-align: center;
}
</style>


<script>

var name = $("#paginate").attr("name");
console.log(name);


url = url.replace("0", searchText);

$.ajax({
    type: "GET",
    url: url,
    
    data: {searchText: searchText},
    success: function (response)
    {
        // var content = "tbody tr";

        $('#content .item').remove();
        var i = 0;
        for (i = 0; i < response.length; i++)
        {
            var thisId = '<tr class=item><td>'

                    + response[i].idProperties + "</td><td>"
                    + response[i].nameProperties + "</td><td>"

                    + response[i].roomsProperties + "</td><td>"

                    + "</td><td>"
                    + "<a id=deleteAjax onClick=deleteAjax(" + response[i].idProperties + ") class='btn btn-danger delete' data-playgroup-id=" + response[i].id + "><span class='glyphicon glyphicon-trash' aria-hidden=true></span></a>" + "</td><td>"
                    + "<a class='btn btn-warning' href=/Lovelydays_Crm/web/app_dev.php/properties/edit/" + response[i].idProperties + "> <span class='glyphicon glyphicon-edit' aria-hidden=true></span></a>"
                    + "</td></tr>";
            $('#content').append(thisId);
            $('#content .item').addClass('item' + response[i].idProperties);
        }
    }
}
);

</script>