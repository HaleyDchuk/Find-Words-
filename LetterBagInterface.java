/*
 * This interface makes sure that this particular method will be in the LetterBag Class. 
 * 
 * @author Joanna Klukowska 
 */
import java.util.ArrayList;

public interface LetterBagInterface {
	
	/** 
	 * This method determines the list of words that can be created from a given 
	 * LetterBag object that are present in the provided Dictionary object dict. 
	 * 
	 * @param dict the Dictionary object to be used 
	 * 
	 * @return a list of valid words in alphabetical order
	 * 
	 */
	
	ArrayList<String> getAllWords (Dictionary dict);

}
