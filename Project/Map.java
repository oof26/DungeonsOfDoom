import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads and contains in memory the map of the game.
 *
 */
public class Map {

	/* Representation of the map */
	private char[][] map;
	private int mapHeight;
	private int mapWidth;
	private int mapNumber=0;
	private List<String> inputMap = new ArrayList<String>();
	/* Map name */
	private String mapName;
	
	/* Gold required for the human player to win */
	private int goldRequired;
	
	/**
	 * Default constructor, creates the default map "Very small Labyrinth of doom".
	 */
	public Map() {
		mapName = "Very small Labyrinth of Doom";
		goldRequired = 2;
		map = new char[][]{
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','G','.','.','.','.','.','.','.','.','.','E','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','E','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','G','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
		};
		mapHeight=map.length;
		mapWidth=map[0].length;
	}
	/**
	 * Constructor that accepts a map to read in from.
	 *
	 * @param : The filename of the map file.
	 * @throws FileNotFoundException 
	 */
	public Map(String fileName){
		mapHeight=0;
		mapWidth=0;
		mapNumber+=1;
		readMap("Maps/" + fileName);
		map = new char[mapHeight][mapWidth];
		for(int x = 0; x<mapHeight;x++) {
			map[x]=inputMap.get(x).toCharArray(); //converts the arrayList to a 2Dim char array
		}
	}

    /**
     * @return : Gold required to exit the current map.
     */
    protected int getGoldRequired() {
        return goldRequired;
    }

    /**
     * @return : The map as stored in memory.
     */
    protected char[][] getMap() {
        return map;
    }


    /**
     * @return : The name of the current map.
     */
    protected String getMapName() {
        return mapName;
    }


    /**
     * Reads the map from file.
     *
     * @param : Name of the map's file.
     * @throws FileNotFoundException 
     */
    protected void readMap(String fileName){
    	File myFile = new File (fileName) ;
		try {
			FileReader fileIn = new FileReader ( myFile );
			BufferedReader input = new BufferedReader ( fileIn ) ;
	    	try {	
	    		String map = "";
	    		while((map = input.readLine()) !=null){
	    			processLine(map);
	    		}
	    		input.close();
	    		
	    	}
	    	catch ( IOException e ) {
	    		e . printStackTrace () ;
	    	}
		} catch (FileNotFoundException e1) {
			System.out.println("File not found");
		}
    }
    /*
     * processes each line returned by the File Reader
     */
	private void processLine(String input) {
		if(input.contains("name")) {
			String regex = "\\s*\\bname\\b\\s*";
			input = input.replaceAll(regex, "");
			mapName=input;
		}else if(input.contains("win")) {
			String regex = "\\s*\\bwin\\b\\s*";
			input = input.replaceAll(regex, "");
			goldRequired=Integer.parseInt(input);
		}else{
			mapWidth=input.length(); //lines are added to the ArrayList which is then converted to a two dim array as an array list is dynamic
			inputMap.add(input);
			mapHeight+=1;
		}
	}
	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}
	public int getMapNumber() {
		return mapNumber;
	}
	public void setMapNumber(int num) {
		this.mapNumber = num;
	}
	public boolean isValid() {
		int goldInMap=0;
		//if mapName =  null, map is invalid
		if(mapName==null) {
			return false;
		}
		//if goldRequired is negative, map is invalid
		if(goldRequired<0) {
			return false;
		}
		
		//if goldRequired is > goldInMap, map is invalid
		for(int y=1;y<mapHeight-1;y++) {
			for(int x=1;x<mapWidth-1;x++) {
				if(map[y][x]=='G') {
					goldInMap++;
				}
			}
		}
		if(goldRequired>goldInMap) {
				return false;
		}
		
		//if map[][] is not surrounded by walls, map is invalid
		for(int topBottom = 0; topBottom<mapWidth; topBottom++) {
			if(map[0][topBottom]!='#' || map[mapHeight-1][topBottom]!='#'){
				return false;
			}
		}
		for(int sides = 0; sides<mapHeight; sides++) {
			if(map[sides][0]!='#' || map[sides][mapWidth-1]!='#'){
				return false;
			}
		}
		
		//if map[][] contains invalid characters, map is invalid
		for(int height=1;height<mapHeight-1;height++) {
			for(int width=1;width<mapWidth-1;width++) {
				if(map[height][width]=='G' || map[height][width]=='E' || map[height][width]=='.' || map[height][width]=='#' ) {
					//Do nothing
				}else {	
					return false;
				}
			}
		}
		
		//if map satisfies all these conditions, map is Valid
		return true;
	}


}
