<%@include file="header.jsp" %>

   <body>
   
        <form method="post" action="home">
            <fieldset>
                <legend>Connexion</legend>
                <p>Vous pouvez vous connecter via ce formulaire.</p>

                <label for="nom"><fmt:message key="login"/> <span class="requis">*</span></label>
                <input id="login" name="login" value="<c:out value="${client.login}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['login']}</span>
                <br />

                <label for="password"><fmt:message key="mdp"/> <span class="requis">*</span></label>
                <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['password']}</span>
                <br />

                <input type="submit" value="<fmt:message key="connexion"/>" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                <p>  <% 
                	String checker = (String) request.getAttribute("checker");
            		out.println( checker );
           			 %>
            </p>
            </fieldset>
        </form>


    </body>
</html>

