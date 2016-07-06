package algoII.tp.imple;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import algoII.tp.dbUtil.DbConnectionSingleton;
import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Library;
import algoII.tp.def.Title;
import algoII.tp.folderExplorer.FolderExplorer;

public class LibraryImpleTrucha implements Library
{
	
	final static Charset ENCODING = StandardCharsets.ISO_8859_1;
	@Override
	public List<Title> getTitles()
	{
		DbConnectionSingleton db = DbConnectionSingleton.getInstance();
		List<Title> lista=new ArrayList<Title>();
		Connection con=db.getConnection();
		try {
			java.sql.Statement statement=con.createStatement();
			String query = "   select title FROM dbmusic.title";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String nombre = rs.getString("name");
				lista.add(new TitleImpleTrucha(nombre));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Filter> getFilters()
	{
		DbConnectionSingleton db = DbConnectionSingleton.getInstance();
		List<Filter> lista=new ArrayList<Filter>();
		Connection con=db.getConnection();
		try {
			java.sql.Statement statement=con.createStatement();
			String query = "   select distinct filter FROM dbmusic.filter";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String nombre = rs.getString("name");
				lista.add(new FilterImpleTrucha(nombre));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Filter getFilter(String filtername)
	{
		List<Filter> arr = new ArrayList<>();
		  arr = getFilters();
		  for (Filter f: arr){
		   if(f.getName() == filtername)
		   {
		    return f;
		   }
		  }
		  return null;
		 
	}

	@Override
	public List<Label> getLabels(Filter f)
	{
		return f.getLabels();
	}

	@Override
	public Label getLabel(Filter f, String labelname)
	{
		List<Label> arr = new ArrayList<>();
		arr = f.getLabels();
		 for (Label l: arr){
			   if(l.getName() == labelname)
			   
			    return l;
			   
			  }
		
		return null;
		
	}

	@Override
	public List<Title> getTitles(Filter f, Label lb)
	{
		List<Title> titulos=new ArrayList<Title>();
		List<Label> labels=f.getLabels();
		
		for(Label l:labels)
			titulos.addAll(l.getTitles());
		
		return titulos;
	}
	
	public void loadDatabase(String path){
		FolderExplorer ex = new FolderExplorer();
		try {
			ex.cargarDiscos(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
