package algoII.tp.def;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public interface Title
{
	public String getName();
	public String getPath();
	public Hashtable<Filter,List<Label>>  getAtts();
	public long postTitle(String title, Long date);
}
