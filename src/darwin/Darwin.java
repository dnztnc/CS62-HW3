package darwin;

import java.io.*;

/**
 * This class controls the simulation. The design is entirely up to you. You
 * should include a main method that takes the array of species file names
 * passed in and populates a world with species of each type. You class should
 * be able to support anywhere between 1 to 4 species.
 * 
 * Be sure to call the WorldMap.pause() method every time through the main
 * simulation loop or else the simulation will be too fast. For example:
 * 
 * 
 * public void simulate() { 
 * 	for (int rounds = 0; rounds < numRounds; rounds++) {
 * 		giveEachCreatureOneTurn(); 
 * 		WorldMap.pause(500); 
 * 	} 
 * }
 * 
 */
class Darwin {

	public Darwin(String[] speciesFilenames) {
	}

	/**
	 * The array passed into main will include the arguments that appeared on the
	 * command line. For example, running "java Darwin Hop.txt Rover.txt" will call
	 * the main method with s being an array of two strings: "Hop.txt" and
	 * "Rover.txt".
	 * 
	 * The autograder will always call the full path to the creature files, for
	 * example "java Darwin /home/user/Desktop/Assignment02/Creatures/Hop.txt" So
	 * please keep all your creates in the Creatures in the supplied
	 * Darwin/Creatures folder.
	 *
	 * To run your code you can either: supply command line arguments through
	 * VS code (add a launch.json file in the folder .vscode according to instructions and add/edit the following attribute
     * `"args": ["whateverArgumentsYouWantSeparatedByComma"]`) or by creating a temporary array
	 * with the filenames and passing it to the Darwin constructor. If you choose
	 * the latter options, make sure to change the code back to: Darwin d = new
	 * Darwin(s); before submitting. If you want to use relative filenames for the
	 * creatures they should be of the form "./Creatures/Hop.txt".
	 */
	public static void main(String s[]) {
		Darwin d = new Darwin(s);
		d.simulate();
		
	}

	public void simulate() {

		// don't forget to call pause somewhere in the simulator's loop...
		// make sure to pause using WorldMap so that TAs can modify pause time
		// when grading
			try {
			
			World world = new World(9, 9);
			WorldMap.createWorldMap(9, 9);

			BufferedReader in = new BufferedReader(new FileReader("./Creatures/" + "Food" + ".txt"));
			Species s = new Species(in);
			Position pos = new Position(2, 0);
			Creature c = new Creature(s, world, pos, Position.EAST);

			in = new BufferedReader(new FileReader("./Creatures/" + "Hop" + ".txt"));
			Species s2 = new Species(in);
			Position pos2 = new Position(3, 3);
			Creature c2 = new Creature(s2, world, pos2, Position.EAST);

			for (int i = 0; i < 5; i++) {
            c.takeOneTurn();
			c2.takeOneTurn();
            WorldMap.pause(300);
        }

	}catch (Exception e) {
			e.printStackTrace();
		}
	}}
