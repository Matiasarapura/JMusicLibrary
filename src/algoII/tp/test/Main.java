package algoII.tp.test;

import java.util.List;

import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Library;
import algoII.tp.imple.LibraryImpleTrucha;

public class Main
{
	public static void main(String[] args)
	{
		Library lib = new LibraryImpleTrucha();
		lib.listFiles("C:\\ALBUMS_deploy\\ALBUMS");
//		List<Filter> filtros = lib.getFilters();
//		
//		for(Filter f:filtros)
//		{
//			List<Label> labels = f.getLabels();
//			
//		}
		
	}
}
