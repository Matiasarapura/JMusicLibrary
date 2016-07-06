package algoII.tp.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import algoII.tp.dbUtil.DbConnectionSingleton;
import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Library;
import algoII.tp.def.Title;
import algoII.tp.imple.LabelImpleTrucha;
import algoII.tp.imple.LibraryImpleTrucha;
import algoII.tp.imple.TitleImpleTrucha;

public class Main
{
	public static void main(String[] args) throws SQLException, IOException
	{
		Title disco=new TitleImpleTrucha("C:/ALBUMS/Oscar Peterson/We Got The Request/info.jml");
		Hashtable<Filter,List<Label>> atributos=disco.getAtts();
		
		Set<Filter> filtros=atributos.keySet();
		
		 Iterator<Filter> itr = filtros.iterator();
	      
		    //Displaying Key and value pairs
		    while (itr.hasNext()) { 
		    	Filter filtro=itr.next();
		    	System.out.println("filtro:"+filtro.getName());
		    	for(Label l:atributos.get(filtro))
		    		System.out.println(l.getName());
		    }
		
	//	Library lib = new LibraryImpleTrucha();
		//lib.loadDatabase("C:\\ALBUMS");
		
	}
}
