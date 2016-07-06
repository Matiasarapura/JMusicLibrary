package algoII.tp.imple;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import algoII.tp.dbUtil.DbConnectionSingleton;
import algoII.tp.def.Label;
import algoII.tp.def.Title;

public class LabelImpleTrucha implements Label
{
	private String name;
	public LabelImpleTrucha(String name)
	{
		this.name = name;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public List<Title> getTitles()
	{
		DbConnectionSingleton db = DbConnectionSingleton.getInstance();
		List<Title> lista=new ArrayList<Title>();
		Connection con=db.getConnection();
		try {
			java.sql.Statement statement=con.createStatement();
			String query = " select dbmusic.title.title from dbmusic.title join"+ 
					" dbmusic.title_has_label on idtitle=id_title"+
					" where dbmusic.title_has_label.id_label in (select idlabel from dbmusic.label where name like \'%"+ name +"%\')";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String nombre = rs.getString("title");
				lista.add(new TitleImpleTrucha(nombre));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<String> getSublabels()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
