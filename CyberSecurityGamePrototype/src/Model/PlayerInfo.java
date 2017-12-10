/**
 * 
 */
package Model;

import java.util.*;
/**
 *
 * @author Dat Ly
 * @author Raymond Schooley
 * @author Trung Thai 
 * @author Wes Stahl
 * @version 1.0
 */

public class PlayerInfo {
	
	private String myPass;
	private String myEncryptPass;
	private boolean myNetEncryptFlag;
	private boolean myPassEncryptFlag;
	private int myLevel;
	private double myWallet;
	private String myPassEncyptionKey;
//	private List<Email> myEmail;
	
	/**
	 * Constructor for class PlayerInfo. Sets default constraints.
	 */
	public PlayerInfo() {
		
		myPass = defaultPass();	
		myNetEncryptFlag = false;
		myPassEncryptFlag = false;
		myLevel = 1;
		myWallet = 100.00;
		myPassEncyptionKey = "";
		myEncryptPass = "";
		//myEmail = new LinkedList<Email>();
	}
	
	/**
	 * Generates the default password at the start of the game.
	 * 
	 * @return returns a randomly generated password
	 */
	private String defaultPass() {
		
		int password_length = 10;
		StringBuilder aString = new StringBuilder();
		
		int min = 97; 
		int max = 127; 
		Random r = new Random();
		
		while (password_length > 0) {
			int result = r.nextInt(max - min) + min;
			aString.append((char) result);
			password_length--;
		}
		
		return aString.toString();
	}
	
	/**
	 * Used so the Player can recover their password.
	 * 
	 * @return returns their password to JPanel class for player details.
	 */
	public String getPass() {
		if (myPassEncryptFlag) {
			return myEncryptPass;
		}
		return myPass;
	}
	
	/**
	 * Changes network encryption scheme if the player decides to encrypt their network
	 */
	public void changeNetEncryption(final boolean theEncrypt) {
		myNetEncryptFlag = theEncrypt;
	}
	
	/**
	 * Determines if the user would like to encrypt their password. Encrypts password if yes.
	 */
	public void changePassEncryption(final boolean theEncrypt) {
		myPassEncryptFlag = theEncrypt;
		if (!myPassEncryptFlag) {
			myEncryptPass = "";
		} else {
			createEncryptionKey();
			encryptPassword();
		}
	}
	
	/**
	 * Allows the player to change their password if they wish.
	 */
	public boolean changePassword(String originalPass, String newPass, String newPassA) {
		if (!passCompare(myPass, originalPass)) {
			return false;
		} else if (!passCompare(newPass, newPassA)) {
			return false;
		} else if (!stringCheck(newPass)) {
			return false;
		}
		myPass = newPass;
		return true;
	}
	
	private boolean stringCheck(String newPass) {
		for (int i = 0; i < newPass.length(); i++) {
			char temp = newPass.charAt(i);
			if (temp < 48) {
				return false;
			} else if (temp > 57 && temp < 65) {
				return false;
			} else if ((temp > 90 && temp < 97) || temp > 122) {
				return false;
			}
		}
		return true;
	}

	private boolean passCompare(String aString, String bString) {
		for (int i = 0; i < aString.length(); i++) {
			if ((char)aString.charAt(i) != (char)bString.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the players current level.
	 * 
	 * @return returns an integer value that represents the player level
	 */
	public int getLevel() {
		return myLevel;
	}
	
	public void incrementLevel() {
		myLevel += 1;
	}
	
	/**
	 * Gets the players current wallet size.
	 * 
	 * @return returns a double that represents the players' money.
	 */
	public double getWallet() {
		return myWallet;
	}
	
	/**
	 * Adds/subtracts money from the player wallet.
	 * 
	 * @param theDouble double value that can be either negative or positive.
	 */
	public void incrementWallet(final double theDouble) {
		myWallet += theDouble;
	}
	
	/**
	 * Generates a random encryption key.
	 */
	public void createEncryptionKey() {
		int aVal = 1, temp;
		Random rand = new Random();
		while (aVal < myPass.length() - 1) {
			temp = rand.nextInt(122);
			myPassEncyptionKey += (char)temp;
			aVal++;
		}
	}
	
	/**
	 * Gets the current encryption key.
	 * 
	 * @return returns a string
	 */
	public String getEncyptionKey() {
		return myPassEncyptionKey;
	}
	
	/**
	 * Encrypts the player's password.
	 */
	private void encryptPassword() {
		for (int i = 0; i < myPass.length(); i++) {
			int val = (char)myPassEncyptionKey.charAt(i);
			int temp = (char)myPass.charAt(i);
			int newChar = temp + val % 122;
			myEncryptPass += (char)newChar;
		}
	}
	
	/*
	public Email getMail() {
		return myEmail;
	}
	 */
	
	/*
	public void addMail(Email email) {
		myEmail.add(email);
	}
	 */
	
	/*
	public void deleteEmail(Email email) {
		myEmail.remove(email);
	}
	 */

}
