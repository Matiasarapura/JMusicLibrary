package algoII.tp.imple;

import java.util.List;

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
		
		
		return null;
	}

}
