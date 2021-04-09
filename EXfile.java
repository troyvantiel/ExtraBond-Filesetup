import java.io.*;
import java.util.*;


public class EXfile {
	//make a string for the filename
	public static String filename = "./Extrabonds.txt";
	public static void outputToFile(ArrayList<String[]> linedata)
	{
		try
		{

			//need to get the length of the final data array divided by four as that is how many atoms are in the residues and thus how many dihedral angles we need to constrain
			int divlength = linedata.size()/4;
			//specify the spring constant k for the angle
			int k = 0;
			//specify the angle in degrees that will be the constraint
			int ref = 60;

			//create a file writer so text can be written to the file
			FileWriter fileWrite = new FileWriter(filename);
			//for loop to take the data of four lines at a time and create a constraint
			for(int y = 0; y < divlength; y = y+4)
			{
				//write the data to the file in the correct formatting. Example: dihedral <atom1> <atom2> <atom3> <atom4> <k> <ref>
				fileWrite.write("dihedral " + "<" + linedata.get(y)[1] + "> " + "<" + linedata.get(y+1)[1] + "> " + "<" + linedata.get(y+2)[1] + "> "
						+ "<" + linedata.get(y+3)[1] + "> " + "<" + k + "> " + "<" + ref + ">" + "\n");
			}
			fileWrite.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void	Readpdb(String filename)
	{
		try
		{
			boolean loop = true;
			boolean atomcheck = false;
			int p = 0;
			String line = "";
			String split = " ";
			List<String> bbatoms = Arrays.asList(new String[]{"C", "O", "N", "CA"});
			ArrayList<String> atomdata = new ArrayList<>();
			ArrayList<String[]> linedata = new ArrayList<>();
			String[] pdbline = null;

			BufferedReader pdbRead = new BufferedReader(new FileReader(filename));
			//read the first line from the file as it contains crystal data that is not useful
			line = pdbRead.readLine();
			while(loop)
			{
				//clears the data to make sure nothing is copied from the last iteration
				atomdata.clear();
				//stores the next line of the file in a variable
				line = pdbRead.readLine();
				//resets the variable p to 0. p is the counter of how many data points have been removed from the line to keep the indexing consistent
				p = 0;
				//reset the atom check to false so that it can be set back to true if a backbone atom is found
				atomcheck = false;
				//split the line from the file by the delimiter (space in this case)
				pdbline = line.split(split);
				//for loop that will go through all the data points from each line that have been stored in the string array
				for(int i = 0; i < pdbline.length; i++)
				{
					//add all the data to an arraylist to allow manipulation
					atomdata.add(pdbline[i]);
					//if statement to check the line if it contains nothing
					if(atomdata.get(i-p).isEmpty())
					{
						//if the space is empty it can be removed from the array list of data which will compress and take away the spaces
						atomdata.remove(i-p);
						//increment p as there has been another data point removed (avoids the out of bounds errors!!)
						p++;
					}
				}
				//first check if we are still looking at the protein and not the water box. This can be determined from the last column in the pdb file
				//WT1 stands for the first water in the molecule which is no longer part of the protein
				if(atomdata.get(11).equals("WT1"))
				{
					//break out of the while loop so set the boolean "loop" to false will break the while
					loop = false;
				}
				//need to loop through each of the atom types to check if the line contains one of the backbone atoms
				for(int f = 0; f < 4; f++)
				{
					//if statement to check for a backbone atom. Atom type is stored in index 2
					if (atomdata.get(2).equals(bbatoms.get(f))) {
						//if there is a match we want the line added to the final array so set the atom check variable to true
						atomcheck = true;

						break;
					}

				}
				//check the "atomcheck" variable (true) to see if the line can be added to the final array
				if(atomcheck)
				{
					//add the line to the final array. putting it in as an array dereferences the data so the array can be cleared for the next line and no data will be lost
					linedata.add(atomdata.toArray(new String[12]));
				}
				//call the output method to set up the extrabonds file
				outputToFile(linedata);
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
		//create file and do relevant checks to see if the file exists
		File myFile = new File(filename);
		//check to see if a file was created and show feedback based on this case
		if(myFile.createNewFile())
		{
			System.out.println("File created: " + myFile.getName());
		}
		else
		{
			System.out.println("File already exists. Overwriting...");
		}
		Readpdb(args[0]);
		System.out.println("Done!");
	}
  	catch (IOException e)
	{
		e.printStackTrace();
	}



		


  }



}
