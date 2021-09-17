import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Runs the game with a human player and contains code needed to read inputs.
 *
 */
public class HumanPlayer extends Player{
	
	private int playerGold;

	
	
	public HumanPlayer(Map map){
		playerType='P';
		playerGold=0;
		char[][] GameMap=map.getMap();
		boolean validPos=false;
		while(!validPos) {
			Random randY = new Random();
			playerPosY= randY.nextInt(GameMap.length-1)+1;
			Random randX= new Random();
			playerPosX=randX.nextInt(GameMap[0].length-1)+1;
			if(GameMap[playerPosY][playerPosX]!='G' && GameMap[playerPosY][playerPosX]!='#') {
				validPos=true;
			}
		}
	}
    

    /**
     * Processes the command. It should return a reply in form of a String, as the protocol dictates.
     * Otherwise it should return the string "Invalid".
     *
     * @return : Processed output or Invalid if the @param command is wrong.
     */
    protected String getNextAction() {
    	String input = UserInput.getInputFromConsole().toUpperCase();
    	String[] nextAction = input.split(" ");
    	if(nextAction[0].equals("HELLO")){
    		return nextAction[0]; 
    	}else if (nextAction[0].equals("GOLD")) {
    		return nextAction[0];
    	}else if(nextAction[0].equals("MOVE")) {
    		try {
    			switch(nextAction[1]) {
	    			case "N": return input;
	    			case "S": return input;
	    			case "E": return input;
	    			case "W": return input;
	    			default: return "Invalid";
    			}
    		}catch(ArrayIndexOutOfBoundsException exception) {
    			return "Invalid";
    		}   		
    	}else if(nextAction[0].equals("PICKUP")) {
    		return nextAction[0];
    	}else if(nextAction[0].equals("LOOK")) {
    		return nextAction[0];
    	}else if(nextAction[0].equals("QUIT")) {
    		return nextAction[0];
    	}else {
    		return "Invalid";
    	}
    }
    
    /**
     * Converts the map from a 2D char array to a single string.
     *
     * @return : A String representation of the game map.
     */
    protected String look(char GameMap[][], Player bot) {
    	String output="";
    	for(int y=getPlayerPosY()-2;y<=getPlayerPosY()+2;y++) {
    		for(int x=getPlayerPosX()-2;x<=getPlayerPosX()+2;x++) {
    			try {
    				if(x==getPlayerPosX() && y==getPlayerPosY()) {
    					output+='P';
    				}else if (x==bot.getPlayerPosX() && y==bot.getPlayerPosY()) {
    					output+='B';
    				}else {
    					output+=GameMap[y][x];
    				}
    			}catch(ArrayIndexOutOfBoundsException exception) {
    				output+="#";
    			}
    		}
    		output+=System.lineSeparator();
    	}
        return output;
    }

	public int getPlayerGold() {
		return playerGold;
	}

	public void setPlayerGold(int playerGold) {
		this.playerGold = playerGold;
	}
	
	public int getPlayerPosX() {
		return playerPosX;
	}
	
	public void setPlayerPosX(int playerPosX) {
		this.playerPosX = playerPosX;
	}
}