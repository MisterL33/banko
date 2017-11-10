<%@include file="header.jsp" %>

   <body>


 <div class="row">
        <div class="col s12 m6" style="float: none; margin: auto;     margin-top: 50px;">
          <div class="card blue-grey darken-1" >
            <div class="card-content white-text">
              <span class="card-title">Formulaire de connexion</span>
              
        <form method="post" action="home">
            

                <label for="nom"><fmt:message key="login"/> <span class="requis">*</span></label>
                <input id="login" name="login" value="<c:out value="${client.login}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['login']}</span>
                <br />

                <label for="password"><fmt:message key="mdp"/> <span class="requis">*</span></label>
                <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['password']}</span>
                <br />

                <input style="display: block;width: 100%;" type="submit" value="<fmt:message key="connexion"/>" class="waves-effect waves-light btn" />
                
         
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                <p> ${checker}
            </p>
           
        </form>
            </div>
            
          </div>
        </div>
      </div>
    </body>
</html>

<style>

.row label{

color: white;

}

</style>

