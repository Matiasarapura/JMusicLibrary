package algoII.tp.def;

import java.util.Hashtable;
import java.util.List;

public interface Title
{
	public String getName();
	public String getPath();
	public Hashtable<Filter,List<Label>>  getAtts();
}
