
public class CaesarCipher {
	
	private static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	//private String shiftedAlphabet = "";
	//private String shiftedAlphabet2 = "";
	private int key;
	private int key2;
	
	//constructor with one key
	public CaesarCipher(int key) 
	{
		this.key = key;
		//shiftedAlphabet = ALPHABET.substring(key) + ALPHABET.substring(0, key);
	}
	
	//constructor with two key
	public CaesarCipher(int key1, int key2)
	{
		this.key = key1;
		this.key2 = key2;
		////shiftedAlphabet2 = ALPHABET.substring(key2) + ALPHABET.substring(0, key2);
	}

	//method encrypt that encrypt a string with a key
	public String encrypt (String input) {

		StringBuilder encrypted = new StringBuilder(input);
		
		for (int i = 0; i < encrypted.length(); i++){
			
			char currentChar = encrypted.charAt(i);
			encrypted.setCharAt(i, encryptHelper(currentChar, key));
		}	
		return encrypted.toString();
	}
	
	//encrypt input string with double key
	public String encryptTwoKeys (String input) {
		
		StringBuilder encrypted = new StringBuilder(input);
		
		for (int i = 0; i < encrypted.length(); i++){
			char currentChar = encrypted.charAt(i);
			if (i%2 == 0)
				encrypted.setCharAt(i, encryptHelper(currentChar, this.key));
			else 
				encrypted.setCharAt(i, encryptHelper(currentChar, this.key2));		
		}
		return encrypted.toString();
	}
	
	//method encryptHelper that takes a character and encrypted with a key
	private char encryptHelper (char currentChar, int key) {
		
		String shiftedAlphabet = ALPHABET.substring(key) + ALPHABET.substring(0, key);
		char encryptedChar = currentChar;
		
		if(Character.isLowerCase(currentChar)){
			currentChar = Character.toUpperCase(currentChar);
			int index = ALPHABET.indexOf(currentChar);
			if (index != -1){
				encryptedChar = shiftedAlphabet.charAt(index);
				encryptedChar = Character.toLowerCase(encryptedChar);
			}
		} else {
			int index = ALPHABET.indexOf(currentChar);
			if (index != -1)
				encryptedChar = shiftedAlphabet.charAt(index);
		}
		return encryptedChar;
	}  	
	
	//method decrypt that decrypt a input message
	public String decrypt (String input) 
	{
		CaesarCipher cc = new CaesarCipher(26-key);
		return cc.encrypt(input);
	}
}
