import java.io.FileNotFoundException;

/**
 * Contains the main logic part of the game, as it processes.
 *
 */
public class GameLogic {
	
	private boolean gameRunning;
	private Map map;
	char[][] GameMap;
	private static HumanPlayer player;
	private static Bot bot;
	/**
	 * Default constructor
	 */
	public GameLogic() {
		map = new Map();
		GameMap = map.getMap();
		gameRunning=true;
		player = new HumanPlayer(map);
		bot = new Bot(map, player);
	}

    /**
	 * Checks if the game is running
	 *
     * @return if the game is running.
     */
    protected boolean gameRunning() {
    	if(player.getPlayerPosX()==bot.getPlayerPosX() && player.getPlayerPosY()==bot.getPlayerPosY()) {
    		System.out.println("You've been caught by the bot");
    		gameRunning=false;
    	}
        return gameRunning;
    }

    /**
	 * Returns the gold required to win.
	 *
     * @return : Gold required to win.
     */
    protected String hello() {
        return "Gold required: " + map.getGoldRequired();
    }
	
	/**
	 * Returns the gold currently owned by the player.
	 *
     * @return : Gold currently owned.
     */
    protected String gold() {
        return "Gold owned: " + player.getPlayerGold(); 
    }

    /**
     * Checks if movement is legal and updates player's location on the map.
     *
     * @param direction : The direction of the movement.
     * @return : Protocol if success or not.
     */
    protected String move(char direction, Player player) {
    	char[][] gameMap=map.getMap();
    	if(direction=='N') {
        	if(gameMap[player.getPlayerPosY()-1][player.getPlayerPosX()]=='#') {
        		return "FAIL";
        	}else {
        		player.setPlayerPosY(player.getPlayerPosY()-1);
        		return "SUCCESS";
        	}
        }else if(direction=='S') {
        	if(gameMap[player.getPlayerPosY()+1][player.getPlayerPosY()]=='#') {
        		return "FAIL";
        	}else {
        		player.setPlayerPosY(player.getPlayerPosY()+1);
        		return "SUCCESS";
        	}
        }else if(direction=='E') {
        	if(gameMap[player.getPlayerPosY()][player.getPlayerPosX()+1]=='#') {
        		return "FAIL";
        	}else {
        		player.setPlayerPosX(player.getPlayerPosX()+1);
        		return "SUCCESS";
        	}
        }else if(direction=='W') {
        	if(gameMap[player.getPlayerPosY()][player.getPlayerPosX()-1]=='#') {
        		return "FAIL";
        	}else {
        		player.setPlayerPosX(player.getPlayerPosX()-1);
        		return "SUCCESS";
        	}
        }
    	return "FAIL";
    }


    /**
     * Processes the player's pickup command, updating the map and the player's gold amount.
     *
     * @return If the player successfully picked-up gold or not.
     */
    protected String pickup() {
    	
    	if(GameMap[player.getPlayerPosY()][player.getPlayerPosX()]=='G') {
    		player.setPlayerGold(player.getPlayerGold()+1);
    		GameMap[player.getPlayerPosY()][player.getPlayerPosX()]='.'; 
    		return "SUCCESS. Gold owned: " + player.getPlayerGold() ;
    		
    	}
    	
    	return "FAIL. Gold owned: " + player.getPlayerGold() ;
    }
    /**
     * Quits the game, shutting down the application.
     */
    protected void quitGame() {
    	if(GameMap[player.getPlayerPosY()][player.getPlayerPosX()]=='E' && player.getPlayerGold()==map.getGoldRequired()) {
    		gameRunning=false;
    		System.out.println("WIN.");
    	}else {
    		System.out.println("LOSE.");
    		gameRunning=false;
    		System.exit(0);
    	}
    }
	/*
	 * Processes the input from the bot and the human player
	 * the parameters are interchangeable between the two as they are under the same superclass Player
	 * methods are called from game logic or in the case of look, from the player class
	 */
    protected String processInstruction(Player player, Player other) {
		String[] input = player.getNextAction().split(" ");
		switch(input[0]) {
			case "HELLO": 
				return hello();
			case "GOLD": 
				return gold();
			case "PICKUP": 
				return pickup();
			case "LOOK": 
				return player.look(GameMap, other);
			case "QUIT":
				quitGame();
				return null;
			case "MOVE": 
				try {
					return move(input[1].charAt(0), player);
				}catch(ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
				break;
			default: return input[0];
		}
		return null;

	}
	/*
	 * sets the active map for the game when prompted to 
	 */
	private void setMap(Map map) {
		this.map = map;
	}
	private Map getMap() {
		return map;
	}
	/*
	 * sequential method running of the game
	 * First the map is selected then the game is run
	 */
	public static void main(String[] args) {
		GameLogic logic = new GameLogic();
		
		MapSelector maps = new MapSelector();
		maps.displayMaps();
		
		try {
			logic.setMap(maps.pickMap());
			System.out.println(logic.getMap().getMapName() + " selected");
		}catch(NullPointerException e){
			System.out.println("Map not found, Game starting with default map");
			logic.setMap(new Map());
		}
		
		while(logic.gameRunning()) {
			System.out.println(logic.processInstruction(player,bot));
			logic.processInstruction(bot, player);
			
		}
			
    }

	
}