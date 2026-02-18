package darwin;

/**
 * This class represents one creature on the board. Each creature must remember
 * its species, position, direction, and the world in which it is living.
 * In addition, the Creature must remember the next instruction out of its
 * program to execute.
 * The creature is also responsible for making itself appear in the WorldMap. In
 * fact, you should only update the WorldMap from inside the Creature class.
 */
public class Creature {
	Species mySpecies;
	World myWorld;
	Position myPos;
	int myDir;
	public int count=1;
	String myColor;
	char myChar;
	/**
	 * Create a creature of the given species, with the indicated position and
	 * direction. Note that we also pass in the world-- remember this world, so
	 * that you can check what is in front of the creature and update the board
	 * when the creature moves.
	 */
	public Creature(Species species, World world, Position pos, int dir) {
		mySpecies=species;
		myWorld=world;
		myPos=pos;
		myDir=dir;
		myColor=species.getColor().toLowerCase();
		myChar=species.getSpeciesChar();
		WorldMap.displaySquare(myPos, myChar, myDir, myColor);
	}

	/**
	 * Return the species of the creature.
	 */
	public Species species() {
		return mySpecies; 
	}

	/**
	 * Return the current direction of the creature.
	 */
	public int direction() {
		return myDir; 
	}

	/**
	 * Sets the current direction of the creature to the given value 
	 */
	public void setDirection(int dir){
		myDir=dir;
	}

	/**
	 * Return the position of the creature.
	 */
	public Position position() {
		return myPos; // fix
	}
	/**
	 * Execute steps from the Creature's program
	 *   starting at step #1
	 *   continue until a hop, left, right, or infect instruction is executed.
	 */
	public void takeOneTurn() {
		Instruction currentInst = mySpecies.programStep(count);
		int code = currentInst.getOpcode();
		count++;
		int address = currentInst.getAddress();;
		switch (code){
			case 1: // hop
				Position nextStep = myPos.getAdjacent(myDir);
				if (myWorld.inRange(nextStep) && myWorld.get(nextStep)==null){
					myWorld.set(myPos, null);
					myWorld.set(nextStep, this);
					WorldMap.displaySquare(myPos, ' ', myDir, " ");
					WorldMap.displaySquare(nextStep, myChar, myDir, myColor);
					myPos=nextStep;
				}
				break;

			case 2: // left
				myDir=(3+myDir)%4;
				WorldMap.displaySquare(myPos, myChar, myDir, myColor);
				break;

			case 3: // right
				myDir=(myDir+1)%4;
				WorldMap.displaySquare(myPos, myChar, myDir, myColor);
				break;

			case 4: // infect
				Position adjacentPos = myPos.getAdjacent(myDir);
				if (!adjacentPos.equals(null)){
					Creature adjacentCreature = myWorld.get(myPos.getAdjacent(myDir));
					if (!adjacentCreature.species().equals(mySpecies)){
						adjacentCreature = new Creature(mySpecies, myWorld, adjacentPos, adjacentCreature.direction());

						if (address>0){
							adjacentCreature.count = address;
						
						}
						else{
							adjacentCreature.count = 1;
						}
					}
					WorldMap.displaySquare(myPos, myChar, myDir, myColor);
					WorldMap.displaySquare(adjacentCreature.position(), adjacentCreature.myChar, adjacentCreature.direction(), adjacentCreature.myColor);
				}
				WorldMap.displaySquare(myPos, myChar, myDir, myColor);
					
					
				break;

			case 5: // ifempty n
				if (address==(-1)){throw new IllegalArgumentException("Missing address!");}		

				break;

			case 6: // ifwall n
				if (address==(-1)){throw new IllegalArgumentException("Missing address!");}
				break;

			case 7: // ifsame n
				if (address==(-1)){throw new IllegalArgumentException("Missing address!");}
				break;

			case 8: // ifenemy n
				if (address==(-1)){throw new IllegalArgumentException("Missing address!");}
				break;

			case 9: // ifrandom n
				if (address==(-1)){throw new IllegalArgumentException("Missing address!");}

				break;

			case 10: // go
				if (address==(-1)){throw new IllegalArgumentException("Missing address!");}
				count=address;
				break;

			default:
				count--;
				break;

		}
	}

	/**
	 * Return the compass direction the is 90 degrees left of the one passed in.
	 */
	public static int leftFrom(int direction) {
		return (4 + direction - 1) % 4;
	}

	/**
	 * Return the compass direction the is 90 degrees right of the one passed
	 * in.
	 */
	public static int rightFrom(int direction) {
		return (direction + 1) % 4;
	}
}
