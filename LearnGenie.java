import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LearnGenie {
	
	public static void greetUser() {
		System.out.println("I am the learning genie! \n"
				+ "I can figure out whatever you are thinking of by asking questions. \n");
		
	}
	
	/**Purpose: plays the game LearnGenie in infinite loop
	 * @param dt a decision tree 
	 * @param in a scanner to read user input
	 * @param filename a name of the file to save the tree to
	 * @throws IOException
	 */
	public static void runGenie(DecisionTree dt, Scanner in, String filename) throws IOException {
		System.out.println("I know " + dt.countObjects() + " thing(s)! \n");
		
		boolean loop = true;
		while (loop) {
			dt.guess(in);
			System.out.println("Do you want to continue? ");
			String response = in.nextLine().toLowerCase();
			if (!(response.equals("yes"))) {
				loop = false;
			}		
		}
		
		FileWriter out = new FileWriter(filename);
		dt.write(out);
		out.close();
	}
	/*
	 * there are three existing test files: testing, Books and HistoricalFigures
	 */
	public static void main (String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		greetUser();
		//DecisionTree dt = new DecisionTree();
		//DecisionTree dt = new DecisionTree("Barack Obama");
		DecisionTree dt = new DecisionTree(new File("HistoricalFigures"));
		runGenie(dt, in, "HistoricalFigures");
		in.close();
	}

}