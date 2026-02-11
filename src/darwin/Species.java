package darwin;

import java.io.*;
import java.util.ArrayList;

/**
 * The individual creatures in the world are all representatives of some
 * species class and share certain common characteristics, such as the species
 * name and the program they execute. Rather than copy this information into
 * each creature, this data can be recorded once as part of the description for
 * a species and then each creature can simply include the appropriate species
 * reference as part of its internal data structure.
 * 
 * Note: The instruction addresses start at one, not zero.
 */
public class Species {
	private String name;
	private String color;
	private char speciesChar; // the first character of Species name
	private ArrayList<Instruction> program;

	/**
	 * Create a species for the given fileReader. 
	 */
	public Species(BufferedReader fileReader) {
			try {
				
				// insert code to read from Creatures file here (use readLine() )
				program = new ArrayList<>();
				name=fileReader.readLine();
				speciesChar=name.charAt(0);
				color=fileReader.readLine();
				String line;
				while ((line = fileReader.readLine()) != null) {
					if (line.isEmpty()) break;
					String[] parts = line.split("\\s+");
					int number, address;
    				switch (parts[0]) {
					case "hop":
						number=1;
						break;
					case "left":
						number=2;
						break;
					case "right":
						number=3;
						break;
					case "infect":
						number=4;
						break;
					case "ifempty":
						number=5;
						break;
					case "ifwall":
						number=6;
						break;
					case "ifsame":
						number=7;
						break;
					case "ifenemy":
						number=8;
						break;
					case "ifrandom":
						number=9;
						break;
					case "go":
						number=10;
						break;

					default:
						number=0;
}					
					if (parts.length>1){
						program.add(new Instruction(number, Integer.parseInt(parts[1])));
					}
					else {program.add(new Instruction(number));}
					
				}

				
			} catch (IOException e) {
				System.out.println(
					"Could not read file '"
						+ fileReader
						+ "'");
				System.exit(1);
			}
	
	}


	/**
	* Return the char for the species
	*/
	public char getSpeciesChar() {
		return speciesChar;		
	}

	/**
	 * Return the name of the species.
	 */
	public String getName() {
		return name;   
	}

	/**
	 * Return the color of the species.
	 */
	public String getColor() {
		return color;    
	}

	/**
	 * Return the number of instructions in the program.
	 */
	public int programSize() {
		return program.size(); 
	}

	/**
	 * Return an instruction from the program.
	 * @pre 1 <= i <= programSize().
	 * @post returns instruction i of the program.
	 */
	public Instruction programStep(int i) {
		return program.get(i-1); 
	}

	/**
	 * Return a String representation of the program.
	 * 
	 * do not change
	 */
	public String programToString() {
		String s = "";
		for (int i = 1; i <= programSize(); i++) {
			s = s + (i) + ": " + programStep(i) + "\n";
		}
		return s;
	}

}
