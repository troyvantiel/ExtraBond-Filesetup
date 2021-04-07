import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class EXfile
{
  public static void main(String[] args)
  {
	try
	{
		//file for dihedral extrabonds needs to be set up in the manner shown below
		//dihedral <atom1> <atom2> <atom3> <atom4> <k> <ref>
		
		int atom1 = 0;
		int atom2 = 0;
		int atom3 = 0;
		int atom4 = 0;
		int k = 0;
		int ref = 0;
		String file = args[0];
		String dihed = "dihedral";
		String splitBY = ",";
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		
	}
	catch
	{
	
	}	
  }



}
