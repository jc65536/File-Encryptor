import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7430908817835291671L;
	JTextField filePathPrompt = new JTextField();
	JButton encryptAction = new JButton("Encrypt");
	JButton decryptAction = new JButton("Decrypt");
	GridLayout launcherGrid = new GridLayout(4, 1);
	JLabel instructions = new JLabel("<html><center>"
			+ "<span>File"
			+ " Encryptor by Jason Cheng</span><br/>Enter the file path of a text file in"
			+ " the text box below.<br/>The processed file will appear in the same"
			+ " directory as the original.</center></html>", SwingConstants.CENTER);
	static String inputFilePath;
	static String encryptionKey;
	static Container contentPane;

	public static String getFileContent(String filePath) {
		FileReader reader;
		String line = "";
		String contents = "";
		BufferedReader buffer;
		try {
			reader = new FileReader(filePath);
			buffer = new BufferedReader(reader);
			while ((line = buffer.readLine()) != null) {
				contents += line + "\n";
			}
			buffer.close();
			return contents;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public static void performTask(int action) {
		switch (action) {
		case 0:
			System.out.println(action);
			try {
				encryptionKey = JOptionPane.showInputDialog(contentPane, "Enter an encryption key:");
				Encryptor encryptor = new Encryptor(Main.getFileContent(inputFilePath), encryptionKey);
				FileWriter writer = new FileWriter(inputFilePath + "-Encrypted");
				BufferedWriter buffer = new BufferedWriter(writer);
				buffer.write(encryptor.getEncryptedString());
				buffer.close();
				JOptionPane.showMessageDialog(contentPane, "File encrypted.", "Done!", JOptionPane.PLAIN_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(contentPane, "Error writing file.", "Error-", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case 1:
			System.out.println(action);
			try {
				encryptionKey = JOptionPane.showInputDialog(contentPane, "Enter the encryption key:");
				Decryptor decryptor = new Decryptor(Main.getFileContent(inputFilePath), encryptionKey);
				FileWriter writer = new FileWriter(inputFilePath + "-Decrypted");
				BufferedWriter buffer = new BufferedWriter(writer);
				buffer.write(decryptor.getDecryptedString());
				buffer.close();
				JOptionPane.showMessageDialog(contentPane, "File decrypted.", "Done!", JOptionPane.PLAIN_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(contentPane, "Error writing file.", "Error-", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
	}

	public Main() {
		contentPane = getContentPane();
		contentPane.setLayout(launcherGrid);
		contentPane.add(instructions);
		contentPane.add(filePathPrompt);
		contentPane.add(encryptAction);
		contentPane.add(decryptAction);
		encryptAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File tempFile = new File(filePathPrompt.getText());
				if (tempFile.exists()) {
					inputFilePath = filePathPrompt.getText();
					System.out.println(Main.getFileContent(inputFilePath));
					performTask(0);
				} else {
					JOptionPane.showMessageDialog(contentPane, "File not found.", "Error-", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		decryptAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File tempFile = new File(filePathPrompt.getText());
				if (tempFile.exists()) {
					inputFilePath = filePathPrompt.getText();
					System.out.println(Main.getFileContent(inputFilePath));
					performTask(1);
				} else {
					JOptionPane.showMessageDialog(contentPane, "File not found.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		Main window = new Main();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(350, 250);
		window.setVisible(true);
	}
}
