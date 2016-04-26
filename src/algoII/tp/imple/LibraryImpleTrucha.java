package algoII.tp.imple;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import algoII.tp.dbUtil.DbConnectionSingleton;
import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Library;
import algoII.tp.def.Title;

public class LibraryImpleTrucha implements Library
{
	
	final static Charset ENCODING = StandardCharsets.ISO_8859_1;
	@Override
	public List<Title> getTitles()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Filter> getFilters()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filter getFilter(String filtername)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Label> getLabels(Filter f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Label getLabel(Filter f, String labelname)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Title> getTitles(Filter f, Label lb)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> readSmallTextFile(String aFileName) throws IOException {
	    Path path = Paths.get(aFileName);
	    return Files.readAllLines(path, ENCODING);
	 }
	

	
	public void cargarDiscoEnBase(String absolutePath,Connection con) throws IOException, SQLException{
		
	   
		java.sql.Statement statement=con.createStatement();
		String query;
		Path path = Paths.get(absolutePath);
	    List<String> lineas=Files.readAllLines(path, ENCODING);
	   
	    
	    Hashtable<String,List<String>> filtros_etiquetas = new Hashtable<String,List<String>>();
	    
	    // el = y el ; no deberian estar hardcodeados
	    for (String l:lineas){
	    	l.trim();
	    	
	    	if(l.split("=").length>1){ //si hay alguna etiqueta
		    	String filtro=l.split("=",2)[0];
		    	List<String> etiquetas = new ArrayList<String>();
		    	for (String etiqueta: l.split("=",2)[1].split(";")){
			         etiquetas.add(etiqueta);
			      }
		    	filtros_etiquetas.put(filtro, etiquetas);
	    	}
	    }
	    
	    Set<String> keys = filtros_etiquetas.keySet();
	    
	    //Obtaining iterator over set entries
	    Iterator<String> itr = keys.iterator();
	 
	    System.out.println(absolutePath);
	    //Displaying Key and value pairs
	    while (itr.hasNext()) { 
	       // Getting Key
	       String filtro = itr.next();
	       System.out.println(filtro + " : ");
	       for(String etiqueta:filtros_etiquetas.get(filtro))
	    	   System.out.println(etiqueta);
	    
	      
	    }
	    
	    query=" INSERT IGNORE INTO DBMUSIC.TITLE (TITLE) VALUES  ( \" " + absolutePath + " \") " ;
	    
	    statement.execute(query);
		
	    System.out.println("-----------------------------------");
		
		
	}
	
	public void cargarDiscos(String folder,Connection con) throws IOException, SQLException{
		
		File directory = new File(folder);
		
		File[] contents = directory.listFiles();
		List<String> texto = null;
		
		if(contents==null){
			if(directory.getAbsolutePath().endsWith("info.jml"))
					
					
					cargarDiscoEnBase(directory.getCanonicalPath(),con);
					
		
			}
			else{
			
				for ( File f : contents) 
				  
				  cargarDiscos(f.getAbsolutePath(),con);
				
			}
		}
	
		

	

}
