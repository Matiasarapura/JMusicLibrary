package algoII.tp.imple;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import algoII.tp.dbUtil.DbConnectionSingleton;
import algoII.tp.def.Filter;
import algoII.tp.def.Label;

public class FilterImpleTrucha implements Filter
{
	private String name;
	
	public FilterImpleTrucha(String n)
	{
		name = n;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public List<Label> getLabels()
	{
		DbConnectionSingleton db = DbConnectionSingleton.getInstance();
		List<Label> lista=new ArrayList<Label>();
		Connection con=db.getConnection();
		try {
			java.sql.Statement statement=con.createStatement();
			String query = "   select distinct name FROM dbmusic.label where id_filter =(select idfilter from dbmusic.filter where filter like \'%"+ name +"%\')";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String nombre = rs.getString("name");
				lista.add(new LabelImpleTrucha(nombre));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
