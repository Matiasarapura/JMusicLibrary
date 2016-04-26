package algoII.tp.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Statement;

import algoII.tp.dbUtil.DbConnectionSingleton;
import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Library;
import algoII.tp.imple.LibraryImpleTrucha;

public class Main
{
	public static void main(String[] args) throws SQLException, IOException
	{
		
	
		Library lib = new LibraryImpleTrucha();
		DbConnectionSingleton db = DbConnectionSingleton.getInstance();
		Connection con=db.getConnection();
		lib.cargarDiscos("C:\\ALBUMS_deploy\\ALBUMS",con);
		con.close();
//		
//		DbConnectionSingleton db = DbConnectionSingleton.getInstance();
//		Connection con=db.getConnection();
//		
//		
//		String query="SELECT idtitle,title FROM DBMUSIC.TITLE";
//		java.sql.Statement statement=con.createStatement();
//		
//		ResultSet rs = statement.executeQuery(query);
//
//		while (rs.next()) {
//
//			String idtitle = rs.getString("IDTITLE");
//			String title = rs.getString("TITLE");
//
//			System.out.println("titleid : " + idtitle);
//			System.out.println("title: " + title);
//			
//		}
//	
//		con.close();
		
	}
}
