import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/*
 * Map selector used to generate a list of all maps in the Maps directory and then display them to allow the player to choose
 */
public class MapSelector {
	private ArrayList<Map> maps;
	private Map map ;
	public MapSelector() {
		int num = 1;
		maps = new ArrayList<Map>();
		File folder = new File("Maps");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) { //Generates a list of all maps in the Maps directory
			String fileName=listOfFiles[i].getName();
			map = new Map(fileName);
			if(map.isValid()) {
				map.setMapNumber(num);
				maps.add(map);
				num +=1;
			}
		  }
		}
	/*
	 * Outputs all maps in the list
	 */
	public void displayMaps() { 
		for(int x = 0; x<maps.size(); x++) {

			System.out.println(maps.get(x).getMapNumber() + " " + ((Map) maps.get(x)).getMapName());
		}
		System.out.println("Select number of desired map");
	}
	/*
	 * Prompts the user to select a map by number and returns the map to Game Logic
	 */
	public Map pickMap() { 
		Map map = null;
		String input = UserInput.getInputFromConsole();
		for(int x = 0; x<maps.size(); x++) {
			int mapNumber = (int) maps.get(x).getMapNumber();
			if(Integer.parseInt(input)==mapNumber){
				return maps.get(x);
			}
		}
		return map;
	}
	
}
