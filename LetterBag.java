/**
 * This class represents the String of letters entered by the user. Anagrams of 
 * the inputed letters will be created as their prefixes are found in the Dictionary 
 * object. This class also creates a new Dictionary object that is a duplicate of 
 * dict in the Dictionary class. It will return an alphabetically sorted list of 
 * all of the anagrams created that were found in dict. 
 * 
 * @author Haley Danylchuk 
 * @version 10/10/2015 
 * 
 */
import java.util.ArrayList;

public class LetterBag implements LetterBagInterface {
	
	//a field of LetterBag Class that represents the letters entered by the user 
	private String userInput;
	
	//a field of the LetterBag Class that is a Dictionary object that is duplicated from the 
	//Dictionary Class' object 
	private Dictionary dict; 
	
	//allows the final list from getAllWords method to be passed to the sortFinalList method in order for 
	//it to be sorted and for the duplicates to be removed 
	public ArrayList<String> finalList;
	
	//sets the letters typed in by the user 
	public void setUserInput(String userInput){ 
		this.userInput = userInput; 
	}
	
	//prints dict from the LetterBag Class 
	//not needed for the assignment 
	public void print() { 
		
		for (int x = 0; x< dict.size(); x++) { 
			System.out.println(dict.get(x));
		}
		
	}
	
	//gets the letters inputed by the user 
	public String getUserInput(){ 
		return userInput; 
	}
	
	//Constructor that creates an instance of the LetterBag Class 
	//takes the letters inputed by the user as a parameter 
	public LetterBag(String userInput){ 
		this.userInput = userInput;
		
	}

	//sets the Dictionary dict in LetterBag Class 
	public  void setDict(Dictionary dict1) { 
		dict = dict1;
	}
	
	//gets the contents of the Dictionary object for the LetterBag Class 
	public  Dictionary getDict(){ 
		return dict; 
	}
	
	/** 
	 * This method is implemented from the LetterBagInterface. 
	 * 
	 * @param dict dict is an object of the Dictionary class that is an ArrayList 
	 * of Strings consisting of words read in from the input file. 
	 * 
	 * @return returns another getAllWords method that will return an ArrayList of Strings 
	 * that takes more parameters that of which have been initiated in this private 
	 * getAllWords method. 
	 */
	public ArrayList<String> getAllWords(Dictionary dict){
		
		//keeps track of letters used in computing the anagrams
		//is the size of the String of letters inputed by the user 
		boolean [] lettersUsed = new boolean [userInput.length()]; 
		//will hold the new created anagrams 
		StringBuffer outputString = new StringBuffer(); 
		//changes the letters entered by the user from a String to an array of letters
		//where each letter is its own character and has its own index 
		char[] letters = userInput.toCharArray();
		int letterIndex = 0; 
		
		//returns the private getAllWords method with the added parameters 
		return getAllWords(dict, letters, lettersUsed, outputString, letterIndex, new ArrayList<String>());
		
	}
	
	
	/**
	 * This method returns an ArrayList of Strings of all of the anagrams found in dict. 
	 * This ArrayList will be sorted alphabetically and contain no duplicate words. 
	 * 
	 * @param dict dict is an object of the Dictionary class that is an ArrayList of words 
	 * from the input file. 
	 * 
	 * @param letters letters is the char array that is made up of the letters of the 
	 * word entered by the user. 
	 * 
	 * @param lettersUsed lettersUsed is a Boolean array that keeps track of what letters 
	 * from the char array have been used in creating the anagrams. 
	 * 
	 * @param outputString outputString is a StringBuffer that each prefix after it is 
	 * checked is added to. The words in the outputString are converted to a String to then 
	 * be added to an ArrayList of Strings. 
	 * 
	 * @param letterIndex letterIndex is increased whenever getAllWords calls itself so 
	 * that all of the anagrams can be created. 
	 * 
	 * @return returns an alphabetically sorted ArrayList of the anagrams found in dict. 
	 */

	private ArrayList<String> getAllWords(Dictionary dict, char[] letters, boolean[] lettersUsed, StringBuffer outputString, int letterIndex,ArrayList<String> finalList){ 
		

		//for the length of the letters entered by the user 
			for (int i = 0; i < letters.length; i++) { 
			//if the letter has already been used, continue onto a different letter 
			if(lettersUsed[i]==true){ 
					continue; 
				}
			//add the letter from the user input to the StringBuffer 
				outputString.append(letters[i]); 
				lettersUsed[i] = true; 
				//convert StringBuffer to String so it can match the parameters of the DictionaryInterface for the 
				//method findPrefix(String prefix)
				String prefix = outputString.toString();
				//if the prefix was found to also be a prefix of a word in dict... 
				if (dict.findPrefix(prefix)==true) { 
					//method calls itself and increments the index by 1 in order to add another letter to the prefix
					getAllWords(dict, letters, lettersUsed, outputString,letterIndex+1, finalList); 
				}
				lettersUsed[i] = false; 
				outputString.setLength(outputString.length()-1);
			}
			//convert the StringBuffer to a String 
			String word = outputString.toString();
			//if the length of this word, that of which represents the newly created anagram, is equal to the length 
			//of the String of letters initially inputed by the user, call the findWords method in Dictionary 
			//this piece is the base case of the recursion method 
			if (word.length() == letters.length){ 
				
				//if the findWords method returns true, add that newly created anagram to the ArrayList that was initiated 
				//in the beginning of LetterBag Class 
				if (dict.findWords(word)) { 
					
					finalList.add(word);
				}
			}
			//once all of the newly created anagrams have been found in dict and added to the ArrayList, return this 
			//ArrayList 
			return finalList;

		
	}

	

	
	/**
	 * This method removes all duplicate anagrams that were created and found in dict. 
	 * It then sorts the ArrayList with no duplicates. 
	 * 
	 * @param finalList finalList is the unsorted list of anagrams found in dict that was 
	 * created in the getAllWords method. 
	 * 
	 * @return returns a sorted list with no duplicates of the anagrams created and 
	 * found in dict. It also returns the number of words found in dict even if no words were found.  
	 * 
	 */ 
	public ArrayList<String> sortFinalList(ArrayList<String> finalList) { 
		
		//declares a new ArrayList of Strings that is the list of words found in dict without any 
		//duplicate words 
		ArrayList<String> noDuplicates = new ArrayList<String>();
		//for loop for the length of the list that may contain duplicates 
		for (int x=0; x< finalList.size(); x++) {
			
			//if the word is already in the noDuplicates list, ignore it 
			if (noDuplicates.contains(finalList.get(x))){ 
				
				continue; 
			}
			//if the newly created ArrayList does not contain the word at the specified index of 
			//finalList, add it to this new list of no duplicates 
			else{ 
				noDuplicates.add(finalList.get(x));
			}
			
		}
	
		//Uses insertion sort to sort the list of no duplicates alphabetically 
			String comparison; 
			int index; 
			//for loop starts at the first index because it is the second element in the array, index 1, that 
			//checks with the element before it and swaps them if the second element is less 
			//than the first element 
			for (int m = 1; m < noDuplicates.size(); m++) { 
				comparison = noDuplicates.get(m);
				//initially, index is equal to 1 
				index = m; 
				//since 1 is > than 0 and element at the first index compared to the next element 
				//returns a number greater than 1, meaning that the comparison element is larger alphabetically, 
				//swap these two elements 
				//this continues until the element in front of the element it is being compared to is
				//larger alphabetically 
				while (index > 0 && noDuplicates.get(index-1).compareTo(comparison) > 0){ 
					//this line of code represents of the swapping of the two elements 
					noDuplicates.set(index, noDuplicates.get(index-1));
					//decrease the index by 1 each time 
					index --; 
				}
				
				//this sets the noDuplcates ArrayList once it is sorted 
				noDuplicates.set(index, comparison);
			
		}
			//if noDuplicates is an empty list, let the user know that no anagrams were found in dict with a print statement
			if (noDuplicates.isEmpty()){ 
				System.out.print("No words found.");
			//if noDuplicates is not empty, print out the number of anagrams found
			//the number of anagrams is found by finding out the size of the ArrayList noDuplicates 
			//print each element of the noDuplicates ArrayList 
			}else { 
				System.out.println("Found " + noDuplicates.size() + " words");
				for (int i = 0; i < noDuplicates.size(); i++) { 
					System.out.println(noDuplicates.get(i));
			}

		
		}
		//since this method must return an ArrayList, this return statement is crucial
		return noDuplicates;
	}
}
