/** 
 * This class constructs an object of type Dictionary called dict. 
 * dict is an ArrayList of Strings made up of words 
 * read in by an input file.  
 * 
 * @author Haley Danylchuk 
 * @version 10/10/2015
 */
import java.util.ArrayList;

public class Dictionary implements DictionaryInterface {
	
	//creates a new ArrayList of Strings that will eventually represent the Dictionary object 
	ArrayList<String> dict = new ArrayList<String>();

	//Constructor that sets the ArrayList created in main from reading in the input file to the 
	//ArrayList in the Dictionary class 
	public Dictionary (ArrayList<String> entries){ 
		
		dict = entries;
		
	}
	
	//allows the contents in dict to be accessed by the LetterBag class in order to print the dict in LetterBag 
	//not needed for assignment
	public String get(int n){ 
		return dict.get(n); 
	}
	
	//allows the size of the dict to be accessed by the LetterBag class in order to print the dict in LetterBag
	//not needed for assignment 
	public int size(){ 
		return dict.size();
	}
	
	//printing dict from Dictionary class 
	public void print() { 
		
		for (int x = 0; x< dict.size(); x++) { 
			System.out.println(dict.get(x));
		}
		
	}

	/**
	 * This method is implemented from the DictionaryInterface interface.
	 * 
	 * @param word word is the anagram created in the getAllWords method that 
	 * has returned true for the findPrefixes method. 
	 * 
	 * @return returns a private findWords method, that of which returns a 
	 * boolean variable. The returned findWords method has added parameters 
	 * to it that have been initialized in this public findWords method. 
	 */
	public boolean findWords(String word) { 
		
		int begin = 0; 
		//index of the last element of dict
		int end = dict.size()-1;
		return findWords(word, begin, end);
	} 
	
	
	/**
	 * This private method uses a recursive binary search to look for the created 
	 * anagram in the Dictionary object, dict since dict is a sorted ArrayList.
	 *  
	 * @param word word is the anagram created in the getAllWords method that has 
	 * returned true for the findPrefixes method. 
	 * 
	 * @param begin begin is the value of the first index of dict, which will change 
	 * as the method calls itself. If the value of begin ever becomes larger than the
	 * value of end, then the word that is being searched for does not exist in dict. 
	 * 
	 * @param end end is another index value and is equal to the length of dict-1 
	 * because it represents the index of the last element in the list initially. 
	 * End too will change as the method calls itself. 
	 * 
	 * @return true if the anagram was found in dict and false if the anagram was not 
	 * found in dict. 
	 */
	private boolean findWords(String word, int begin, int end){ 
		
		//if the starting index is ever larger than the ending index, then the item being searched 
		//for was not found in the ArrayList 
		if (begin > end) { 
			
			return false; 
			
		}
		
		//calculates the middle index in dict 
		int mid = (begin+(end-begin)/2);
		
		//if the word that is being searched for is equal to the word at the middle index, return 
		//true because that word exists in the ArrayList 
		if (dict.get(mid).equals(word)) {
			
			return true; 
			
		//if a positive number is returned, it means that the anagram comes before the word it is
		//being compared to in dict alphabetically 
		} else if (dict.get(mid).compareTo(word) > 0) { 
			return findWords(word,begin, mid-1); 

		//if a negative number is returned, it means that the word in dict comes before the anagram alphabetically 
		} else if (dict.get(mid).compareTo(word) < 0) { 
			return findWords(word, mid+1, end); 
		}
		//if the anagram is never found, return false 
		else { 
				return false; 
		}
		
	}
		
	/**
	 * This method is implemented from the DictionaryInterface interface.
	 * 
	 * @param prefix prefix initially is the first letter of the anagram that is 
	 * being created that needs to be checked to see if it exists in dict.
	 * 
	 * @return returns a private findPrefixes method, that of which returns a 
	 * boolean variable. The returned findPrefixess method has added parameters 
	 * to it that have been initialized in this public findPrefixess method. 
	 */
	
     public boolean findPrefix(String prefix){ 
		
    	int begin = 0;
		int end = dict.size()-1;
		return findPrefix(prefix, begin, end);
	}


	/**
	 * This private method uses a recursive binary search to look for the prefix 
	 * of the anagram being created in the Dictionary object, dict. 
	 * It uses binary search because dict is a sorted ArrayList.
	 *  
	 * @param prefix prefix initially is the first letter of the anagram being created.
	 * As that prefix is found in dict, a new letter is added to prefix and searched 
	 * for in dict for the length of String of letters inputed by the user. 
	 * 
	 * @param begin begin is the value of the first index of dict, which will change 
	 * as the method calls itself. If the value of begin ever becomes larger than the 
	 * value of end, then the word that is being searched for does not exist in dict. 
	 * 
	 * @param end end is another index value and is equal to the length of 
	 * dict-1 because it represents the index of the last element in the list initially. 
	 * End too will change as the method calls itself. 
	 * 
	 * @return true if the prefix was found to also be the prefix of another word in 
	 * dict and false if the prefix was not also the prefix of another word 
	 * found in dict. 
	 */
	private boolean findPrefix(String prefix, int begin, int end){ 
	
		//this means that the substring that is being searched for in dict does not exist 
		if (end < begin) { 
			return false; 
		}
		
		int mid = (begin+ (end-begin)/2);
		
		//the left side of the && symbols make sure that only words that are at least the length of the given 
		//prefix, if not longer are checked
		//otherwise a String index out of bounds error will be thrown because it will try and check for words that 
		//may not have a letter at the index that the statement is asking for it to check 
		//the right side of the && symbol makes the if statement return true if the substring of the word in dict at 
		//index mid is identical to the prefix being checked 
		if (dict.get(mid).length() >= prefix.length() && dict.get(mid).substring(0, prefix.length()).equals(prefix)) { 
			return true; 
			
		} 
		
		//if a positive number is returned, then it means that the prefix comes before the substring of the word in dic
		// that it is being compared to alphabetically 
		else if (dict.get(mid).length() >= prefix.length() && dict.get(mid).substring(0, prefix.length()).compareTo(prefix) > 0) { 
				return findPrefix(prefix, begin, mid-1);
			}
			
		//if a negative number is returned, then it means that the word in the dictionary comes before the anagram it is being 
		//compared to alphabetically 
			else if (dict.get(mid).length() >= prefix.length() && dict.get(mid).substring(0, prefix.length()).compareTo(prefix) < 0){ 
				return findPrefix(prefix, mid+1, end);
			}
			
			else { 
				return false; 
			}
	}
}

	



