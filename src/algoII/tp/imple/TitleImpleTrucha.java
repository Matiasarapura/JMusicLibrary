package algoII.tp.imple;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Title;

public class TitleImpleTrucha implements Title
{

	@Override
	public String getName()
	{
		return "Dos pajaros de un tiro";
	}

	@Override
	public String getPath()
	{
		// TODO Auto-generated method stub
		return "C:/mimusica/sabina/dos pajaros de un tiro";
	}

	@Override
	public Hashtable<Filter,List<Label>> getAtts()
	{
		Filter f = new FilterImpleTrucha("Genre");
		ArrayList<Label> arr = new ArrayList<>();
		arr.add(new LabelImpleTrucha("Jazz"));
		arr.add(new LabelImpleTrucha("Blues"));
		arr.add(new LabelImpleTrucha("Rock"));
		
		
		Hashtable<Filter,List<Label>> tabla = new Hashtable<>();
		tabla.put(f,arr);
		return tabla;
	}

}
