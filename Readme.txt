#### Dungeons of Doom ###
Dungeons of Doom is an texted based adventure game which is run in the command line.
The goal of the game is to collect enough gold and then leave the dungeon via an exit square all while avoiding the Bot.

Installation Instructions:
	This game is run on cmd line through the following steps
	1. for the first use, you must compile the code on command line by navigating to the directory then using the javac*.java command
	2. when you would like to run the game after that, simply type java GameLogic to run the game 
	3. maps can be added to the game by placing them in Maps folder
Controls:
	When starting up the game, the user will be prompted to select a map from the list of maps which are located in the maps folder
	Simply enter then number of the map you desire and it will be selected and the game will begin. 
	When in game the controls are as follows, (n.b. the commands are not case sensitive)
		"HELLO" - returns the amount of gold needed to complete the level
		"GOLD" - returns the current amount of gold the player owns
		"PICKUP" - pickups the gold if the player is on a square marked "G"
		"MOVE" + "<N,S,E,W>" - Moves the player in the desired direction if possible
		"QUIT" - Quits the game, with the player winning or losing 
		"LOOK" - returns a 5x5 grid of the players immediate surroundings
	The win condition is that sufficient gold has been collected and the player is on an exit square marked "E" when the quit command is entered
Code: 
	The code for this game is divided into 	7 classes
	1. Game Logic
		Game Logic contains the main method for this project and also contains players commmands except for look which is dealt with by the relevant class
		It contains 8 methods
			1. gameRunning() 
			2. hello()
			3. gold()
			4. move()
			5. pickup()
			6. quitGame
			7. processInstruction
			8. setMap
	2. Map 
		Map contains a default template for a map and also the functionality to generate a map given a file path, it also checks if maps are valid when passing them to map selector
		It contains 3 methods for this
			1. readMap()
			2. processLine()
			3. isValid()
	3. Player
		Player is an abstract class which acts as a template for both the HumanPlayer and the Bot classes
		It outlines 2 main methods which are shared by its subclasses
			1. getNextAction()
			2. look()	
	4. HumanPlayer
		HumanPlayer is a subclass of Player. It processes inputs from the player and feeds them to the GameLogic class
		It implements the two methods defined in the Player class
			1. getNextAction()
			2. look()
	5. Bot
		Bot is the automated player whos goal is to search for the HumanPlayer via passing commands to the GameLogic class
		This is accomplished through alternating between looking and moving to find the player
		This is done by implementing the two methods defined in the player class
			1. getNextAction()
			2. look();
	6. MapSelector
		MapSelector is used to prompt the user to pick from a list of maps and feeds the selection to the GameLogic class
		It contains an array list of all possible maps aswell as two methods 
			1. displayMaps()
			2. pickMap()	
	7. UserInput
		The final class is used to seperate the handling of user input so that it can be used by multiple other classes, namely the MapSelector and HumanPlayer classes
		It only contains one method
			1. getInputFromConsole()