package algoII.tp.def;

import java.util.List;

public interface Library
{
	public List<Title> getTitles();
	public List<Filter> getFilters();
	public Filter getFilter(String filtername);
	public List<Label> getLabels(Filter f);
	public Label getLabel(Filter f, String labelname);
	public List<Title> getTitles(Filter f, Label lb);
	
}
