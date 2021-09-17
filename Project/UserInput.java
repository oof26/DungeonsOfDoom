import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Used to avoid repetition of code when input is required by the MapSelector and HumanPlayer classes
 */
public class UserInput {
	/**
     * Reads player's input from the console.
     * <p>
     * return : A string containing the input the player entered.
     */
    public static String getInputFromConsole() {
    	BufferedReader input = new BufferedReader (new
    	InputStreamReader ( System . in ) ) ;
    	String userInput="";
    	try {
    		userInput = input . readLine () ;
    	}
    	catch ( IOException e ) {
    		e.printStackTrace () ;
    	}
    	return userInput ;
    }
}
