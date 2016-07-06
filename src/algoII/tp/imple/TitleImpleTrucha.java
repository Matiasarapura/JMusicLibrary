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

	String name;
	public TitleImpleTrucha(String nombre) {
		this.name=nombre;
	}

	@Override
	public String getName()
	{
		return "Dos pajaros de un tiro";
	}

	@Override
	public String getPath()
	{
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Hashtable<Filter,List<Label>> getAtts()
	{
		DbConnectionSingleton db = DbConnectionSingleton.getInstance();
		Connection con=db.getConnection();
		
		Hashtable<Filter,List<Label>> tabla = new Hashtable<>();
		List<Filter> filtros=new ArrayList<Filter>();
		filtros.add(new FilterImpleTrucha("Genre"));
		filtros.add(new FilterImpleTrucha("Artist"));
		filtros.add(new FilterImpleTrucha("Year"));
		filtros.add(new FilterImpleTrucha("Tittle"));
		filtros.add(new FilterImpleTrucha("Subfolder"));
		try{
			java.sql.Statement statement=con.createStatement();
			
			for(Filter filtro: filtros){
				String nameFilter=filtro.getName();
				ArrayList<Label> arr = new ArrayList<>();
				String query =   "select dbmusic.label.name from dbmusic.label join "+
					    "dbmusic.filter on dbmusic.filter.idfilter=dbmusic.label.id_filter "+
					    "join dbmusic.title_has_label on "+
					    "dbmusic.title_has_label.id_label=dbmusic.label.idlabel "+
					    "join dbmusic.title on "+
					    "dbmusic.title_has_label.id_title=dbmusic.title.idtitle "+
					    "where dbmusic.title.title like \'%"+ name +"%\'"+
					    " and dbmusic.filter.filter like \'%"+ nameFilter +"%\' ";
				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					String nombre=rs.getString("name");
					arr.add(new LabelImpleTrucha(nombre));
				}
				tabla.put(filtro,arr);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
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
