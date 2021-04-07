import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class EXfile
{


  public static void readpdb(String filename)
  {
  	try
  	{
  		boolean loop = true;
  		int count = 0;
  		int p = 0;
  		String line = "";
  		String split = " ";
  		ArrayList<String> atomdata = new ArrayList<String>();
  		ArrayList<List<String>> linedata = new ArrayList<List<String>>();
    		String[] pdbline = null;
  		BufferedReader pdbRead = new BufferedReader(new FileReader(filename));
  		line = pdbRead.readLine();
  		while(loop)
  		{
  			atomdata.clear();
  			line = pdbRead.readLine();
  			p = 0;
  			System.out.println(line);
  			pdbline = line.split(split);
  			for(int i = 0; i < pdbline.length; i++)
  			{
				atomdata.add(pdbline[i]);
				//System.out.println(i + "    " + data.get(i-p));
				if(atomdata.get(i-p).isEmpty())
				{
					atomdata.remove(i-p);
					p++; 

				}
  			}
  			linedata.add(atomdata);
  			count++;
  			if(count > 2)
  			{
  				loop = false;
  			}
  		}
  		for(int j = 0; j < 3; j++)
  		{
  			for(int t = 0; t < 12; t++)
  			{
  				System.out.println(linedata.get(j).get(t));
  				//System
  			}
  		}
  	}
  	catch(IOException e)
  	{
  		e.printStackTrace();
  	}
  	
  	
  }

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
		boolean stop = false;
		String file = args[0];
		String dihed = "dihedral";
		String splitBY = ",";
		String line = "";
		String[] data = null;
		
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		readpdb(file);
		while(stop)
		{
			line = br.readLine();
			data = line.split(splitBY);
			
			atom1 = Integer.parseInt(data[0]);
			atom2 = Integer.parseInt(data[1]);
			atom3 = Integer.parseInt(data[2]);
			atom4 = Integer.parseInt(data[3]);
			k = Integer.parseInt(data[4]);
			ref = Integer.parseInt(data[5]);
			
		}
		
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}	
  }



}
