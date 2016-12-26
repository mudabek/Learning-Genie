import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuestionNode implements DecisionNode {
	public String val;
	public DecisionNode left;
	public DecisionNode right;
	
	/**
	 * Creates a new QuestionNode
	 * @param str the question to store
	 * @param left the left node of this node
	 * @param right the right node of this node
	 */
	public QuestionNode(String str, DecisionNode left, DecisionNode right) {
		this.val = str;
		this.left = left;
		this.right = right;
	}
	
	public QuestionNode(String str) { this(str, null, null); }
	
	public boolean isGuess() {
		return false;
	}
	
	/**
	 * @return ret a string representation of the node
	 */
	public String toString() {
		return "{" + val + 
				"[if yes: " + left.toString() + "] [if no: " + right.toString() + "]}";
	}
	
	/**
	 * @param cur the current node to start at
	 * @return the number of GuessNodes in the tree
	 */
	private int countObjectsH(DecisionNode cur) {
		if (cur.isGuess()) {
			return 1;
		} else {
			QuestionNode parent = (QuestionNode) cur;
			return countObjectsH(parent.left) + countObjectsH(parent.right);
		}
	}
	
	public int countObjects() { return countObjectsH(this); }
	
	/**
	 * @param cur the node to start at
	 * @param in the scanner to read response from
	 * @return parent a copy of cur; modified if needed
	 */
	private DecisionNode askNext(DecisionNode cur, Scanner in) {
		if (cur.isGuess()) {
			return cur.guess(in);
		} else {
			QuestionNode parent = (QuestionNode) cur;
			System.out.println(parent.val);
			String response = in.nextLine();
			if (response.toLowerCase().equals("yes")) {
				parent.left = askNext(parent.left, in);
			} else {
				parent.right = askNext(parent.right, in);
			}
			return parent;
		}
	}
	
	public DecisionNode guess(Scanner in) {
		return askNext(this, in);
	}
	
	/**
	 * writes the node to a file
	 * @param out the file to which we write
	 */
	public void write(FileWriter out) throws IOException {
		out.write("#" + val + "\n");
		left.write(out);
		right.write(out);
	}
	
}