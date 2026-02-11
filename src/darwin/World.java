package darwin;

/**
 * This class includes the functions necessary to keep track of the creatures in
 * a two-dimensional world. 
 */

public class World {
	private Matrix<Creature> creatures;
	private int w,h;
	/**
	 * This method creates a new world consisting of width columns and height
	 * rows, each of which is numbered beginning at 0. A newly created world
	 * contains no objects.
	 */
	public World(int w, int h) {
		this.w=w;
		this.h=h;
		this.creatures=new Matrix<>(h,w);
	}

	/**
	 * Returns the height of the world.
	 */
	public int height() {

		return h;
	}

	/**
	 * Returns the width of the world.
	 */
	public int width() {
		return w;
	}

	/**
	 * Returns whether pos is in the world or not.
	 * 
	 * returns true *if* pos is an (x,y) location within the bounds of the board.
	 */
	public boolean inRange(Position pos) {
		int currentX=pos.getX();
		int currentY=pos.getY();
		return (currentX<w && currentX>=0 && currentY<h && currentY>=0);
	}

	/**
	 * Set a position on the board to contain e.
	 * 
	 * @throws IllegalArgumentException if pos is not in range
	 */
	public void set(Position pos, Creature e) {
		if (!inRange(pos)){throw new IllegalArgumentException("Position " + pos + " is out of range");}
		int currentX=pos.getX();
		int currentY=pos.getY();
		creatures.set(currentY,currentX,e);
	}

	/**
	 * Return the contents of a position on the board.
	 * 
	 * @throws IllegalArgumentException if pos is not in range
	 */
	public Creature get(Position pos) {
		if (!inRange(pos)){throw new IllegalArgumentException("Position " + pos + " is out of range");}
		int currentX=pos.getX();
		int currentY=pos.getY();
		return creatures.get(currentY,currentX);
	}

}
