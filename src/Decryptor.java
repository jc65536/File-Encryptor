import java.util.Scanner;
public class Decryptor {
	private String cipher;
	private String encryptionKey;
	private String decryptedString = "";
	public Decryptor(String inputString, String key) {
		cipher = inputString;
		encryptionKey = key;
		Scanner scanner = new Scanner(cipher);
		int counter = 0;
		while(scanner.hasNextInt()) {
			int currentCode = Integer.parseInt(scanner.next(), 16);
			char decryptedCode = (char) (currentCode ^ key.charAt(counter));
			decryptedString += decryptedCode;
			counter = (counter + 1) % encryptionKey.length();
		}
		scanner.close();
	}

	public String getDecryptedString() {
		return decryptedString;
	}
}