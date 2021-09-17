/**
 * 
 */

/**
 * Abstract class which lays out template for the HumanPlayer and Bot classes
 *
 */
public abstract class Player {
	
	protected int playerPosX;
	protected int playerPosY;
	protected char playerType;
	
	public char getPlayerType() {
		return playerType;
	}
	public int getPlayerPosX() {
		return playerPosX;
	}

	public void setPlayerPosX(int playerPosX) {
		this.playerPosX = playerPosX;
	}

	public int getPlayerPosY() {
		return playerPosY;
	}

	public void setPlayerPosY(int playerPosY) {
		this.playerPosY = playerPosY;
	}

	
	
	protected abstract String getNextAction() ;
	protected abstract String look(char[][] map, Player player);
	
	
	
}
