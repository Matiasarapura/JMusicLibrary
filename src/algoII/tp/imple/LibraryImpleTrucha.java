package algoII.tp.imple;

import java.io.File;
import java.util.List;

import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Library;
import algoII.tp.def.Title;

public class LibraryImpleTrucha implements Library
{

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
	
	@SuppressWarnings("unused")
	public void listFiles(String folder){
		File directory = new File(folder);
		
		File[] contents = directory.listFiles();
		
		if(contents==null){
			if(directory.getAbsolutePath().endsWith("info.jml"))
				System.out.println(directory.getAbsolutePath());
		}
		else{
		
			for ( File f : contents) {
			  
			  listFiles(f.getAbsolutePath());
			}
		
		}
	}

}
