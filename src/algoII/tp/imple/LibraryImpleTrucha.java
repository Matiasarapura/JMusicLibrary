package algoII.tp.imple;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import algoII.tp.folderExplorer.FolderExplorer;

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
