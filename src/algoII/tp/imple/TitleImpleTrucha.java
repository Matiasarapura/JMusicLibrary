package algoII.tp.imple;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import algoII.tp.dbUtil.DbConnectionSingleton;
import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Title;

public class TitleImpleTrucha implements Title
{

	@Override
	public String getName()
	{
		return "Dos pajaros de un tiro";
	}

	@Override
	public String getPath()
	{
		// TODO Auto-generated method stub
		return "C:/mimusica/sabina/dos pajaros de un tiro";
	}

	@Override
	public Hashtable<Filter,List<Label>> getAtts()
	{
		Filter f = new FilterImpleTrucha("Genre");
		ArrayList<Label> arr = new ArrayList<>();
		arr.add(new LabelImpleTrucha("Jazz"));
		arr.add(new LabelImpleTrucha("Blues"));
		arr.add(new LabelImpleTrucha("Rock"));
		
		
		Hashtable<Filter,List<Label>> tabla = new Hashtable<>();
		tabla.put(f,arr);
		return tabla;
	}

	@Override
	public long postTitle(String title, Long date) {
		// TODO Auto-generated method stub
		DbConnectionSingleton db = DbConnectionSingleton.getInstance();
		Connection con=db.getConnection();
		String query;
		query = "SELECT FECHA FROM TITLE as t where t.title=\"" +title  + "\"" ;
		try {
			java.sql.Statement statement=con.createStatement();
			ResultSet d = statement.executeQuery(query);
			if (d.next()){
				Long value = d.getLong("fecha");
				if (value < date){
					query=" INSERT IGNORE INTO DBMUSIC.TITLE (TITLE,FECHA) VALUES  ( \" " + title + " \",\""+date+" \" ) " ;
					statement.executeQuery(query);
					ResultSet rs = statement.getGeneratedKeys();
					return rs.getLong(0);
				}else return 0;
			}
			query="INSERT IGNORE INTO DBMUSIC.TITLE (TITLE,FECHA) VALUES  ( \" " + title + " \",\""+date+" \" ) " ;
			statement.execute(query,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys();
			long autoIncKeyFromApi = (long)-1;
			if(rs.next()){
				  autoIncKeyFromApi = rs.getLong(1);
			}
			System.out.println(autoIncKeyFromApi);
			return autoIncKeyFromApi;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;

		}
		
	}

}
