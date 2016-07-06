package algoII.tp.folderExplorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import algoII.tp.constants.ServiceConstants;
import algoII.tp.dbUtil.DbConnectionSingleton;

public class FolderExplorer {

	

	public void cargarDiscoEnBase(String absolutePath, Connection con) throws IOException, SQLException{
		
		java.sql.Statement statement=con.createStatement();
		String query;
		Path path = Paths.get(absolutePath);
	    List<String> lineas=Files.readAllLines(path,ServiceConstants.ENCODING);
	   
	    
	    Hashtable<String,List<String>> filtros_etiquetas = new Hashtable<String,List<String>>();
	    
	    // el = y el ; no deberian estar hardcodeados
	    for (String l:lineas){
	    	l.trim();
	    	
	    	if(l.split("=").length > 1){ //si hay alguna etiqueta
		    	String filtro=l.split("=",2)[0];
		    	List<String> etiquetas = new ArrayList<String>();
		    	for (String etiqueta: l.split("=",2)[1].split(";")){
			         etiquetas.add(etiqueta);
			      }
		    	filtros_etiquetas.put(filtro, etiquetas);
	    	}
	    }
	    
	    
	    

	    BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
	    FileTime time = attr.creationTime();
	    
	    long date = time.toMillis();
	    //los inserts hay que moverlos a cada interf
	    query=" INSERT IGNORE INTO DBMUSIC.TITLE (TITLE,FECHA) VALUES  ( \" " + absolutePath + " \",\""+date+"\") " ;
	    
	    statement.execute(query,Statement.RETURN_GENERATED_KEYS);
	    
	    ResultSet generatedKeys = statement.getGeneratedKeys();
	    long titlePK=(long) -1;
	    
	    if(generatedKeys.next())
	    
	    	titlePK=generatedKeys.getLong(1);
	 
	    System.out.println(absolutePath +"  " + date );
	    
	    Set<String> keys = filtros_etiquetas.keySet();
	    
	    //Obtaining iterator over set entries
	    Iterator<String> itr = keys.iterator();
	      
	    //Displaying Key and value pairs
	    while (itr.hasNext()) { 
	       // Procesar Filtro
	       String filtro = itr.next();
	       System.out.println(filtro + " : ");
	       
	       query=" INSERT IGNORE INTO DBMUSIC.FILTER (FILTER) VALUES (\"" + filtro + " \") " ;
	       
	       statement.execute(query,Statement.RETURN_GENERATED_KEYS);
	       
	       generatedKeys = statement.getGeneratedKeys();
		   long filterPK = (long) -1;
		    
		    if(generatedKeys.next())
		    
		    	filterPK=generatedKeys.getLong(1);
		    
		    else {//ya existe y hay que hacer un query
		    	generatedKeys =statement.executeQuery("SELECT IDFILTER FROM DBMUSIC.FILTER WHERE FILTER=\""+filtro+"\"") ;
		    	String pk;
				if(generatedKeys.next()){
				    
			    	 pk=generatedKeys.getString("IDFILTER");
			    	 filterPK=Long.parseLong(pk);
			    	 
				}
		    } 
		    
		    System.out.println(filterPK);
		    
	       
	       // Procesar Etiquetas
	       for(String etiqueta:filtros_etiquetas.get(filtro)) {
	    	
	    	   etiqueta=devolverEtiqueta(etiqueta);
	    	   query= " INSERT INTO DBMUSIC.LABEL (NAME,ID_FILTER) VALUES (\""+ etiqueta+ "\",\"" + filterPK +" \") " ;
	    	   statement.execute(query,Statement.RETURN_GENERATED_KEYS);
		       
		       generatedKeys = statement.getGeneratedKeys();
			   
		      
		       
		       List<Long> labelPKS=new ArrayList<Long>();
		       
			   while(generatedKeys.next())
				   
				   labelPKS.add(generatedKeys.getLong(1));
				   	
			   for(Long labelPK:labelPKS){
			    	
			    	query = "INSERT INTO DBMUSIC.TITLE_HAS_LABEL (ID_LABEL,ID_TITLE) VALUES (\""+labelPK+ "\",\"" + titlePK +" \") " ;
			    	statement.execute(query);
			    	
			   }
	    	   
	    	   System.out.println(etiqueta);
	    	   
	    	   
	       }
	    }
	    
	    
	    System.out.println("-----------------------------------");
		
		
	}

	
	public String devolverEtiqueta(String etiqueta){
		return etiqueta.replace("\"", "\\\" ");
	}
	
	public void cargarDiscos(String folder) throws IOException, SQLException{
		DbConnectionSingleton db = DbConnectionSingleton.getInstance();
		Connection con=db.getConnection();
		java.sql.Statement statement=con.createStatement();
		File directory = new File(folder);
		File[] contents = directory.listFiles();
		Path path = directory.toPath();
		String direccion = directory.getCanonicalPath().replace('\\', '/');
		
		if(contents==null){
			if(directory.getAbsolutePath().endsWith("info.jml"))
				{	
				BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
				FileTime time = attr.creationTime();
				long date = time.toMillis();
					if(canPost(direccion,date))
						{	
							cargarDiscoEnBase(direccion, con);
						}	
				}
			}else{
			
				for ( File f : contents) 
				  
				  cargarDiscos(f.getAbsolutePath());
				
			}
		}
	
	public Boolean canPost(String path,Long date){
		DbConnectionSingleton db = DbConnectionSingleton.getInstance();

		Connection con=db.getConnection();
		try {
			java.sql.Statement statement=con.createStatement();
			String query = "SELECT FECHA FROM DBMUSIC.TITLE WHERE TITLE LIKE \'%"+path+"%\'";
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				
				if(rs.getLong("FECHA") == date){
					return false;
				}	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
		

	

}

