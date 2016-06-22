package algoII.tp.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import algoII.tp.dbUtil.DbConnectionSingleton;
import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Library;
import algoII.tp.imple.LibraryImpleTrucha;

public class Main
{
	public static void main(String[] args) throws SQLException, IOException
	{
		
	
		Library lib = new LibraryImpleTrucha();
		lib.loadDatabase("C:\\ALBUMS");
		
	}
}
