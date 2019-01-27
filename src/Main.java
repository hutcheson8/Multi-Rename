import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
	private static final Random R = new Random();

	public static void main(String[] args) {
		ArrayList<MRFile> files = new ArrayList<MRFile>();
		Scanner s = null;
		try {
			s = new Scanner(new BufferedInputStream(new FileInputStream("mrename.tmp")));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Input file does not exist. Terminating.");
			e.printStackTrace();
			System.exit(-1);
		}
		do {
			String fileName = s.nextLine();
			if (!fileName.equals("mrename.tmp")) {
				files.add(new MRFile(fileName));
			}
		} while (s.hasNextLine());
		if (s != null)
			s.close();
		PrintStream p = null;
		try {
			p = new PrintStream(new BufferedOutputStream(new FileOutputStream("mrenamef.bat")));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Could not create output file. Terminating.");
			e.printStackTrace();
			System.exit(-2);
		}
		for (int x = 1; !files.isEmpty(); x++) {
			MRFile toPrint = files.get(R.nextInt(files.size()));
			files.remove(toPrint);
			p.println(toPrint.commandLine(x));
		}
		if (p != null)
			p.close();
	}
}