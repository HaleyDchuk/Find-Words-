/** 
 * This class is the runnable program that contains the main method. It parses the 
 * command line argument, reads the input file, reads user input, creates the 
 * Dictionary and LetterBag objects, and displays the final anagrams.
 * 
 *  @author Haley Danylchuk 
 *  @version 10/10/2015
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 

public class FindWords {
	/** The main method parses the command line argument, reads the input file, creates 
	 * the Dictionary object from that file, reads the user input, creates the LetterBag 
	 * object from that, and displays the final ArrayList of sorted anagrams. 
	 * 
	 * @param args args are the command line argument for the file to be read
	 * 
	 * @throws FileNotFoundException if the file parsed cannot be found. Also throws 
	 * an exception if the file cannot be read from or if it does not exist. 
	 */
	public static void main(String[] args) throws FileNotFoundException{ 

		//if the file name is not apart of the argument, a message will be printed letting the user know that 
		//the file name is missing 
		//the program will also quit 
		if(args.length < 1) { 
			System.err.println("File name missing.");
			System.exit(0);
		}
		
		//creates a new file that will take the args[0] as a parameter 
		java.io.File fileName = new java.io.File(args[0]); 
		
		//if the file cannot be read, a message will be displayed letting the user know and the program will exit
		if (!fileName.canRead()) { 
			System.err.printf("Cannot read from file %s\n", fileName.getAbsolutePath());
			System.exit(0);
			
		}

		//if the file does not exist, a message will be displayed letting the user know and the program will exit 
		if (!fileName.exists()) { 
			System.err.println("No such file exists."); 
			System.exit(0);
		}
		
		//this ArrayList will hold all of the words from the input file that make up the dictionary 
		ArrayList<String> entries = new ArrayList<String>();
		//Scanner is used to read the input file 
		Scanner input = new Scanner(fileName); 
		
		//while there continues to be content on the next line of the file... 
		while (input.hasNext()) { 
			//a new String will be created of that word 
			String dictionaryWord = input.nextLine(); 
			//this word that was just read from the input file will be added to the ArrayList entries 
			entries.add(dictionaryWord);

		}
		
		//creates a new instance of the Dictionary Class that takes an ArrayList of Strings as its parameter 
		//this ArrayList of Strings are the Strings read in from the input file and added to entries 
		Dictionary j = new Dictionary(entries);

	
		//closes the Scanner 
		input.close(); 
		
		//declares the letters to be entered by the user to be of type String 
		String userInput = "";
		
		try { 
			//creates a new Scanner to take in input from a user 
			java.util.Scanner userScanner = new java.util.Scanner(System.in);
			//command that the user will see on the screen telling them what to do 
			System.out.println("Please enter a string of letters: ");

			//while the input from the user does not contain strictly letters print an error message telling them that 
			//only letters are accepted 
			while (!userScanner.hasNext("[A-Za-z]+")){ 
				System.out.println("Error. You entered an invalid character. Only letters are accepted.");
				//continues to prompt the user to enter input until it is valid 
				userScanner.next();
			}
			
			//once the input contains only letters, the input can be set to the String declared earlier, userInput
			userInput = userScanner.next(); 
			//makes all the input lower case 
			userInput.toLowerCase();
			//trims all extra white space from the input 
			userInput.trim();
			//closes the Scanner that got the user input 
			userScanner.close();
			
		//print an error if the Scanner cannot read the user input 
		} catch (Exception e) { 
			
			e.printStackTrace(System.err);
		}
		
		//creates a new instance of the LetterBag Class called let whose parameter is the letters entered by the user 
		LetterBag let = new LetterBag(userInput);
		//sets the Dictinary object j, instantiated above, to this instance let of the LetterBag Class 
		let.setDict(j);	
		
		
		//calls the sortFinalList that prints the anagrams that were found, as well as how many were found 
		//takes the getAllWords method of the LetterBag class as a parameter because getAllWords returns the list of 
		//anagrams that needs to be sorted and where duplicates need to be removed 
		let.sortFinalList(let.getAllWords(j));
		
	

	}
	
	

}
