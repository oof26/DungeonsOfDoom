import java.util.Random;


/**
 * 
 */

/**
 * Computer controlled player used to chase down the HumanPlayer
 *
 */
public class Bot extends Player {
	private boolean playerInView;
	private int humanPosX;
	private int humanPosY;
	private boolean cmd;
	private char[] randomDirection = {'N','E','S','W'};
	private String lastAction;
	public Bot(Map map, Player HumanPlayer) {
		playerType='B';
		char[][] GameMap=map.getMap();
		boolean validPos=false;
		while(!validPos) {
			Random randY = new Random();
			playerPosY= randY.nextInt(GameMap.length-1)+1;
			Random randX= new Random();
			playerPosX=randX.nextInt(GameMap[0].length-1)+1;
			if(GameMap[playerPosY][playerPosX]!='G' && GameMap[playerPosY][playerPosX]!='#' && playerPosY!=HumanPlayer.getPlayerPosY() && playerPosX!=HumanPlayer.getPlayerPosX()) {
				validPos=true;
			}
		}
	}
	@Override
	/*
	 * Generates an appropriate action for the Bot by alternating between looking and moving
	 * Movement is made either towards the player if it is in view or in a random direction
	 * @see Player#getNextAction()
	 */
	protected String getNextAction() {
		Random rand = new Random();
		char dir= randomDirection[rand.nextInt(4)];
		if(lastAction=="MOVE") {
			lastAction="LOOK";
			return "LOOK";
		}else {
			if(playerInView) {
				if(playerPosX<humanPosX) {
					dir = 'E';
				}else if(playerPosX>humanPosX) {
					dir = 'W';
				}else if(playerPosY<humanPosY) {
					dir = 'S';
				}else if(playerPosY>humanPosY) {
					dir = 'N';
				}
				playerInView=false;				
			}
			lastAction="MOVE";
			return "MOVE " + dir;
		}
	}
	/*
	 * returns the position of the HumanPlayer if it is found 
	 * @see Player#look(char[][], Player)
	 */
	protected String look(char[][] map, Player player) { 
		for(int y=playerPosY-2;y<=playerPosY+2;y++) {
    		for(int x=playerPosX-2;x<=playerPosX+2;x++) {
    			try {
    				if(x==player.getPlayerPosX() && y==player.getPlayerPosY()) {
    					playerInView=true;
    					humanPosX=player.getPlayerPosX();
    					humanPosY=player.getPlayerPosY();
    					System.out.println("playerfound");
    				}
    			}catch(ArrayIndexOutOfBoundsException exception) {
    				//Do Nothing
    			}
    		}
    	}
		return null;
	}
	

}
