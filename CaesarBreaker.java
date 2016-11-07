
import java.io.*;
import java.util.Scanner;

public class CaesarBreaker {
	
	// read content from input file line by line, return content in a string
	public static String Openfile(String filepath) throws IOException 
	{
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))){
			
			StringBuilder sb = new StringBuilder();
			String line;
			
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			String filecontent = sb.toString();
			return filecontent;
		}	
	}
	
	//static method halfOfString that return half of the string with starting index
	private static String halfOfString (String message, int startingIndex) {
		
		StringBuilder result = new StringBuilder("");
		int i = (startingIndex == 0) ? 0 : 1;
		
		for (; i < message.length(); i+=2) 
			result.append(message.charAt(i));
		
		return result.toString();
	}
	
	//static method countLetters takes a string input and count the letter occurrence
	private static int [] countLetters (String input) {
		
		int [] counts = new int[26];
		for (int i = 0; i < counts.length; i++)
			counts[i] = 0;
		String AlPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Scanner scanLine = new Scanner(input);
		while(scanLine.hasNext()) {
			String word = scanLine.next(); 
			for (int i = 0; i < word.length(); i++) {
				int index = AlPHABET.indexOf(Character.toUpperCase(word.charAt(i)));
				if(index != -1)
					counts[index]++;
			}
		}
		scanLine.close();
		return counts;
	}
	
	//static method indexOfMax finds the index of the max value in an unordered array of integers
	private static int indexOfMax (int [] values) {
		int maxIndex = 0;
		for (int i = 0; i < values.length; i++) 
			if (values[maxIndex] <= values[i])
				maxIndex = i;
		return maxIndex;
	}
	
	//method decrypt that decrypt a input message
	public static String decrypt (String input) 
	{
		CaesarCipher cc = new CaesarCipher(26 - getKey(input));
		return cc.encrypt(input);
	}
	
	//static method getKey returns an integer value of key calculated through letter occurrence
	private static int getKey (String input) 
	{
		int [] counts = new int[26];
		counts = countLetters (input);
		int maxIndex = indexOfMax(counts);
		
		//guess the key based on letter occurrence
		//letter 'e' is the most frequent letter and its index is 4
		if(maxIndex - 4 >= 0)
			return maxIndex - 4;
		else
			return (maxIndex - 4) + 26;
	}
	
	//method decryptTwoKeys that decrypt a message that is encrypted with two keys (two keys are unknown)
	public static String decryptTwoKeys (String encrypted)
	{
		String encryptedWithFirstKey = halfOfString(encrypted, 0);
		String encryptedWithSecondKey = halfOfString(encrypted, 1);
		
		int key1 = getKey(encryptedWithFirstKey);
		int key2 = getKey(encryptedWithSecondKey);
		
		System.out.println("key 1 is " + key1);
		System.out.println("key 2 is " + key2);
		
		CaesarCipher cc = new CaesarCipher(26 - key1, 26 - key2);
		
		return cc.encryptTwoKeys(encrypted);
	}
	
	//method decryptTwoKeys that decrypt a input string that is encrypted with two keys (Two keys are known)
	public static String decryptTwoKeys (String encrypted, int key1, int key2)
	{	
		CaesarCipher cc= new CaesarCipher(26 - key1, 26 - key2);	
		return cc.encryptTwoKeys(encrypted);
	}
	
	//main
	public static void main(String[] args) throws IOException {
		
		System.out.println("Test method encrypt and decrypt (single key):");
		CaesarCipher cc = new CaesarCipher(15);
		//String message = Openfile("src/wordsLotsOfEs.txt");
		String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
		String encrypted = cc.encrypt(message);
		System.out.println("Original message is \n" + message);
		System.out.println("Encrypted message(key=15) is \n"+encrypted);
		//System.out.println("Decrypted message is \n"+decrypt(message));
		
//		System.out.println("=================================================");
//		System.out.println("Test method decryptTwoKeys (double key):");
//		//String message2 = Openfile("src/mysteryTwoKeysQuiz.txt");
//		String message2 = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
//		System.out.println("Original message is \n" + message2);
//		System.out.println("Decrypted message is \n"+decryptTwoKeys(message2,14,24));
	}

}
