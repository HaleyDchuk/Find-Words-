/*
 * This interface makes sure that these particular methods will be in the Dictionary Class. 
 * 
 * @author Joanna Klukowska 
 */
public interface DictionaryInterface {
	
	/** 
	 * This method determines if a given word is in this Dictionary. 
	 * 
	 * @param word the word to be checked. 
	 * 
	 * @return true if the word is in this Dictionary, false otherwise. 
	 */
	
	boolean findWords(String word);
	
	/** This method determines if a given prefix is a prefix of a word that 
	 * exists in this Dictionary. 
	 * 
	 * @param prefix the prefix to be checked
	 * 
	 * @return true if the prefix is in this Dictionary, false otherwise 
	 */
	
	boolean findPrefix(String prefix);

}
