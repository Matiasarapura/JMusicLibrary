package algoII.tp.folderExplorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import algoII.tp.constants.ServiceConstants;
import algoII.tp.dbUtil.DbConnectionSingleton;

public class FolderExplorer {

	
	public void cargarDiscoEnBase(String absolutePath) {
		try
		{
			Connection con = DbConnectionSingleton.getInstance().getConnection();
			java.sql.Statement statement=con.createStatement();
			String query;
			Path path = Paths.get(absolutePath);
		    List<String> lineas=Files.readAllLines(path, ServiceConstants.ENCODING);
		   
		    
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
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void cargarDiscos(String folder){
		
		File directory = new File(folder);
		
		File[] contents = directory.listFiles();

		
		if(contents==null){
			if(directory.getAbsolutePath().endsWith("info.jml"))
				try {
					cargarDiscoEnBase(directory.getCanonicalPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
		
			}
			else{
			
				for ( File f : contents) 
				  
				  cargarDiscos(f.getAbsolutePath());
				
			}
		}
	
}
