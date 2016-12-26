import java.io.*;
import java.util.Scanner;

public class DecisionTree {
	private DecisionNode root;
	
	public DecisionTree() { root = new GuessNode("Dog", null); }
	
	public DecisionTree(String val) { root = new GuessNode(val, null); }
	
	/**
	 * Creates a decision tree from an existing file
	 * @param file a File containing a tree where # indicates a QuestionNode
	 * @throws IOException if the File is not found or the Scanner goes out of bounds
	 */
	public DecisionTree(File file) throws IOException {
		Scanner in = new Scanner(new FileInputStream(file));
		String temp = in.nextLine();
		
		if (temp.charAt(0) == '#') {
			QuestionNode parent = new QuestionNode(temp.substring(1));
			parent.left = readNode(in, parent);
			parent.right = readNode(in, parent);
			root = parent;
		} else { 
			root = new GuessNode(temp, null);
		}
		
	}
	
	/**
	 * @param in a Scanner from which we read input
	 * @param prev the parent node of the node just read
	 * @return node, a QuestionNode or GuessNode read in from in
	 */
	public DecisionNode readNode(Scanner in, QuestionNode prev) {
		String temp = in.nextLine();
		
		if (temp.charAt(0) == '#') {
			QuestionNode parent = new QuestionNode(temp.substring(1));
			parent.left = readNode(in, parent);
			parent.right = readNode(in, parent);
			return parent;
		} else {
			return new GuessNode(temp, prev);
		}
	}
	
	/**
	 * Returns a string representation of a tree.
	 */
	public String toString() {
		return root.toString();
	}
	
	/**
	 * @return ret, a count of the number of objects this
	 * decision tree records, i.e., the number of guess nodes
	 * contained in the tree.
	 */
	public int countObjects() {
		if (root == null) {
			return 0;
		} else {
			return root.countObjects();
		}
	}
	
	/**
	 * @param in, a Scanner for user input
	 * @return ret, an updated node that is the result of any
	 * knowledge learned during the game
	 */
	public void guess(Scanner in) {
		System.out.println("Think of an object!");
		root = root.guess(in);
	}
	
	/**
	 * Writes a node in serialized format to the file represented
	 * by out
	 * @param out the output file
	 * @throws IOException
	 */
	public void write(FileWriter out) throws IOException {
		root.write(out);
	}
}