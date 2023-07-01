import java.util.*;
import java.io.*;

public class Battleship {
	//validate input before battle set up
	public static boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length != 1) {
	        return false;
	    }
	    char c = str.charAt(0);
	    if(c < '5' && c >= '0' ) {
	    	return true;
	    }
	    return false;
  	}
	public static boolean validateInputShip(String rowI, String colI, char[][] battleArray) {
		int rowInput = 0;
	    int colInput = 0;
	    char emptySlot = '-';
	    char occupiedSlot = '@';
	    
	    boolean result = false;
	    //check if input are valid integers; otherwise returns error
	    if(isInteger(rowI)) {
	    	rowInput = Integer.parseInt(rowI);
	    } else {
	    	System.out.println("Invalid coordinates. Choose different coordinates.");
	    	return false;
	    }
	    if (isInteger(colI)){
	    	colInput = Integer.parseInt(colI);
	    }  else {
	    	System.out.println("Invalid coordinates. Choose different coordinates.");
	    	return false;
	    }
		char slotInfo = battleArray[rowInput][colInput];
		if (Character.compare(slotInfo, emptySlot)==0){
			result= true;
		} 
		else if (Character.compare(slotInfo, occupiedSlot)==0){
			System.out.println("You already have a ship there. Choose different coordinates.");
		}
	    return result;  	
	}
	//validate input before firing, after firing
	public static boolean validateStatus(String rowI, String colI, char[][] battleArray) {
		int rowInput = 0;
	    int colInput = 0;
	    char emptySlot = '-';
	    char occupiedSlot = '@';
	    char damagedSlot = 'X';
	    char missedSlot = 'O';
	    boolean result = false;
	    if(isInteger(rowI)) {
	    	rowInput = Integer.parseInt(rowI);
	    } else {
	    	System.out.println("Invalid coordinates. Choose different coordinates.");
	    	return false;
	    }
	    if (isInteger(colI)){
	    	colInput = Integer.parseInt(colI);
	    }  else {
	    	System.out.println("Invalid coordinates. Choose different coordinates.");
	    	return false;
	    }
    	char slotInfo = battleArray[rowInput][colInput];
    	if (Character.compare(slotInfo, emptySlot)==0 || Character.compare(slotInfo, occupiedSlot)==0){
    		return true;
    	} 
		else if (Character.compare(slotInfo, damagedSlot)==0 || Character.compare(slotInfo, missedSlot)==0){
			System.out.println("You already fired on this spot. Choose different coordinates.");
		}
	    return result;  	
	}
	// Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}
	// Use this method to print boards history to the console.
	private static void printHistory(char[][] player) {
		final char occupiedSlot = '@';
		final char emptySlot = '-';
		
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					if (Character.compare(player[row][column],occupiedSlot)==0){
						System.out.print(emptySlot + " ");
					} else {
						System.out.print(player[row][column] + " ");
					}
				}
			}
			System.out.println("");
		}
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Battleship!");
		System.out.println("");
		String[] listPlayer={"PLAYER 1", "PLAYER 2"};
		int[] listShip={1, 2, 3, 4, 5};
		//create two 2d arrays for both players
		char[][] player1= new char[5][5];
		char[][] player2= new char[5][5];
		final char emptySlot = '-';
		final char occupiedSlot = '@';
	    char damagedSlot = 'X';
	    char missedSlot = 'O';
		for (int row=0; row<5; row++){
			for (int col=0; col<5; col++){
				player1[row][col]=emptySlot;
				player2[row][col]=emptySlot;
			}
		}
		
		//task 1:for each player, add 5 ships locations from scanner input. Check valid type (int), no duplicates then proceed.
		String rowI = "";
	    String colI = "";
		for (String playerNo : listPlayer) {
			rowI = "";
	    	colI = "";
			System.out.printf("%s, ENTER YOUR SHIPS’ COORDINATES.\n",playerNo);
			switch (playerNo){
				case "PLAYER 1":
					for (int shipNo : listShip) {
						do {
							System.out.printf("Enter ship %d location: \n",shipNo);
							rowI= input.next();
		        			colI = input.next();
						} while (!validateInputShip(rowI, colI ,player1));
						player1[Integer.parseInt(rowI)][Integer.parseInt(colI)]=occupiedSlot;
					};
					printBattleShip(player1);
					for (int i = 0; i < 100; i++) {
						System.out.println("");
					}
					break;
				case "PLAYER 2":
					for (int shipNo : listShip) {
						do {
							System.out.printf("Enter ship %d location: \n",shipNo);
							rowI= input.next();
		        			colI = input.next();
						} while (!validateInputShip(rowI, colI,player2));
						player2[Integer.parseInt(rowI)][Integer.parseInt(colI)]=occupiedSlot;
					};
					printBattleShip(player2);
					for (int i = 0; i < 100; i++) {
						System.out.println("");
					}
					break;
			}
		}
			
		//task 2: prompt player 1 start fire
		int rowInput = 0;
	    int colInput = 0;
	    int countP1=0;
	    int countP2=0;
	    boolean flag = false;
	    do {
	    	for (String playerNo : listPlayer) {
				rowI = "";
		    	colI = "";
				if (flag==true){
					break;
				} else {
					switch (playerNo){
					case "PLAYER 1":
						do {
							System.out.printf("Player 1, enter hit row/column:\n");
							rowI= input.next();
		        			colI = input.next();
						} while (!validateStatus(rowI, colI ,player2));
						rowInput = Integer.parseInt(rowI);
		        		colInput = Integer.parseInt(colI);
		        		switch (player2[rowInput][colInput]){
		        			case emptySlot:
		        				player2[rowInput][colInput]=missedSlot;
			        			System.out.println("PLAYER 1 MISSED!");
			        			printHistory(player2);
			        			break;
			        		case occupiedSlot:
			        			player2[rowInput][colInput]=damagedSlot;
			        			countP1=countP1 + 1;
			        			
			        			System.out.println("PLAYER 1 HIT PLAYER 2’s SHIP!");
			        			printHistory(player2);
			        			if (countP1 == 5){
			        				flag=true;
			        			}
			        			break;
		        		}
		        		break;
		        	case "PLAYER 2":
						do {
							System.out.printf("Player 2, enter hit row/column:\n");
							rowI= input.next();
		        			colI = input.next();
						} while (!validateStatus(rowI, colI ,player1));
						rowInput = Integer.parseInt(rowI);
		        		colInput = Integer.parseInt(colI);
		        		switch (player1[rowInput][colInput]){
		        			case emptySlot:
		        				player1[rowInput][colInput]=missedSlot;
			        			System.out.println("PLAYER 2 MISSED!");
			        			printHistory(player1);
			        			break;
			        		case occupiedSlot:
			        			player1[rowInput][colInput]=damagedSlot;
			        			System.out.println("PLAYER 2 HIT PLAYER 1’s SHIP!");
			        			countP2 = countP2 + 1;
			        			
			        			printHistory(player1);
			        			if (countP2 == 5){
			        				flag=true;
			        			}
			        			break;
		        		}
		        		break;	
				}

				}
				
			}
	    } while (flag == false);
	    if (countP1 == 5){
	    	System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
	    	System.out.println("");
	    } else {
	    	System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
	    	System.out.println("");
	    }
	    System.out.println("Final boards:");
	    System.out.println("");
	    printBattleShip(player1);
	    System.out.println("");
	    printBattleShip(player2);

    
    }
}

//in input has valid type, add ship position to battle array
//check if ship already in battle array: error. if not, reinput ship position.
//a board must be printed to the console using the provided method.
	//A ‘–’  empty space.
	//An ‘@’ ship that is not hit. 
	//An ‘X’ hit.
	//An ‘O’ miss.
	//Each player’s board must have 5 ships. 5/25 grid spaces will start with @
//100 new lines must follow the printed board
//prompt Player1 fire
	//check valid input
	//already hit that location. reinput
	//if miss: message. print board
	//if hit, congrat. print board
//player2 turn
//until one player wins (all @ replaced by X). print both boards (1 first). 
//Then terminate. 
    
	
	