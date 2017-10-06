package banko.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import banko.model.Client;

public class ClientManager {
	
	public static Client loadClientByID(int clientID) {
	
	Client result = new Client();
	
	try {
		Connection con = DriverManager
				.getConnection("jdbc:mysql://localhost/banque","root","");
		
		PreparedStatement stmt = con.prepareStatement("SELECT clientID, nom, prenom, login, passwd FROM client WHERE clientID=?");
		stmt.setInt(1, clientID);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			result.setClientID(rs.getInt("clientID"));
			result.setNom(rs.getString("nom"));
			result.setLogin(rs.getString("login"));
			result.setPrenom(rs.getString("prenom"));
			result.setPasswd(rs.getString("passwd"));
			System.out.println("Trouvé un client:"+result.toString());
		}
		rs.close();
		con.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return result;
		
	

	}
}