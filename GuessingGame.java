/*
      THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
      CODE WRITTEN BY OTHER STUDENTS. Melanie Dauber
*/

package hw1;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class GuessingGame {
	// fill in code here
		// define data members
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		int count = 0;
		int myguessNum;
		String guess1 = "" + myguessNum;
			
		public GuessingGame ( ) {
			// fill in code here
			// initialization
			//fills the ArrayList
			
			for (int i = 1000; i < 10000; i++)
				numbers.add(i);	
		}

		public int myGuessIs() {
			// fill in code here
			//randomly generates a guess from the ArrayList numbers
			
				int guess = 1 + (int) (Math.random() * (numbers.size()-1));
				myguessNum = numbers.get(guess);
				
				if (numbers.isEmpty()){
					return -1;
				}
				
				count++;
				return myguessNum;
		}

		
		
		
		public int totalNumGuesses() {
			// fill in code here
			// this should return the total number of guesses taken
			return count;
			
		}
	 
		public void updateMyGuess(int nmatches) {
			// fill in code here
			// update the guess based on the number of matching digits claimed by the user
			//based on nmatches the method deletes all numbers in the ArrayList numbers that do not have an equal nmatches
			
			if (nmatches == 0){ 
				for (int x = 0; x < numbers.size(); x++){
					int var = numbers.get(x); 
					int marker = 0; 
					int varNum = myguessNum;
					for (int i = 0; i < 4; i++){
						int specialNum = varNum % 10; //separates digits to compare nmatches
						int specialGuess = var % 10; 
						if (specialGuess == specialNum){
							marker += 1;
						}
						var = var / 10;
						varNum = varNum / 10;
					}
					if (marker != 0){ 
						numbers.remove(x); //removes number from ArrayList
					}
				}
				
			} else if (nmatches == 1){ 
				for (int x = 0; x < numbers.size(); x++){
					int var = numbers.get(x); 
					int marker = 0; 
					int varNum = myguessNum;
					for (int i = 0; i < 4; i++){
						int specialNum = varNum % 10;
						int specialGuess = var % 10; 
						if (specialGuess == specialNum){
							marker += 1;
						}
						var = var / 10;
						varNum = varNum / 10;
					}
					if (marker != 1){ 
						numbers.remove(x);
					}
				}
				
				} else if (nmatches == 2){ 
					for (int x = 0; x < numbers.size(); x++){
						int var = numbers.get(x);
						int varNum = myguessNum;
						int marker = 0;
						for (int i = 0; i < 4; i++){
							int specialNum = varNum % 10;
							int specialGuess = var % 10;
							if (specialGuess == specialNum){
								marker += 1;
							}
							var = var / 10;
							varNum = varNum / 10;
						}
						if (marker != 2) { 
							numbers.remove(x);
						}
					}
					
				}  else if (nmatches == 3){ 
					for (int x = 0; x < numbers.size(); x++){
						int var = numbers.get(x);
						int varNum = myguessNum;
						int marker = 0;
						for (int i = 0; i < 4; i++){
							int specialNum = varNum % 10;
							int specialGuess = var % 10;
							if (specialGuess == specialNum){
								marker += 1;
							}
							var = var / 10;
							varNum = varNum / 10;
						}
						if (marker != 3) { 
							numbers.remove(x);
						}
					}
				} 
			}
		
		// fill in code here (optional)
		// feel free to add more methods as needed
		
		// you shouldn't need to change the main function
		public static void main(String[] args) {

			GuessingGame gamer = new GuessingGame( );
	  
			JOptionPane.showMessageDialog(null, "Think of a number between 1000 and 9999.\n Click OK when you are ready...", "Let's play a game", JOptionPane.INFORMATION_MESSAGE);
			int numMatches = 0;
			int myguess = 0;
			
			do {
				myguess = gamer.myGuessIs();
				if (myguess == -1) {
					JOptionPane.showMessageDialog(null, "I don't think your number exists.\n I could be wrong though...", "Mistake", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				String userInput = JOptionPane.showInputDialog("I guess your number is " + myguess + ". How many digits did I guess correctly?");
				// quit if the user input nothing (such as pressed ESC)
				if (userInput == null)
					System.exit(0);
				// parse user input, pop up a warning message if the input is invalid
				try {
					numMatches = Integer.parseInt(userInput.trim());
				}
				catch(Exception exception) {
					JOptionPane.showMessageDialog(null, "Your input is invalid. Please enter a number between 0 and 4", "Warning", JOptionPane.WARNING_MESSAGE);
					numMatches = 0;
				}
				// the number of matches must be between 0 and 4
				if (numMatches < 0 || numMatches > 4) {
					JOptionPane.showMessageDialog(null, "Your input is invalid. Please enter a number between 0 and 4", "Warning", JOptionPane.WARNING_MESSAGE);
					numMatches = 0;
				}
				if (numMatches == 4)
					break;
				// update based on user input
				gamer.updateMyGuess(numMatches);
				
			} while (true);
			
			// the game ends when the user says all 4 digits are correct
			System.out.println("Aha, I got it, your number is " + myguess + ".");
			System.out.println("I did it in " + gamer.totalNumGuesses() + " turns.");
		}
	}
