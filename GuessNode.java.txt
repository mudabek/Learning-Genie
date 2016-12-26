import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GuessNode implements DecisionNode {
	private String val;
	private QuestionNode parent;
	
	/**
	 * Creates a GuessNode
	 * @param str the value of string object
	 * @param par the parent QuestionNode
	 */
	public GuessNode(String str, QuestionNode par) { 
		val = str;
		parent = par;
	}
	
	public boolean isGuess() {
		return true;
	}
	
	public String toString() {
		return val;
	}
	
	/**
	 * because it's a leaf always returns 1
	 */
	public int countObjects() {
		return 1;
	}
	
	/**
	 * Reads two new pieces of input and creates respective nodes (QuestionNode and GuessNode)
	 * and then updates the tree
	 * @param in the scanner from which to read input
	 * @return parent updated tree
	 */
	private DecisionNode updateTree (Scanner in) {
		System.out.println("Oh no, I was wrong!");
		System.out.println("What object were you thinking of? ");
		String object = in.nextLine();

		System.out.println("What is a yes/no question that distinguishes a " 
				+ val + " from a(n) " + object + "?");
		System.out.println("(Yes corresponds to " + val + 
				"; No corresponds to " + object + ") ");
		String question = in.nextLine();
		
		QuestionNode temp = new QuestionNode(question);
		temp.left = this;
		temp.right = new GuessNode(object, temp);
		parent = temp;

		System.out.println("Thanks! I'll learn from this experience!");
		return parent;
	}
	
	/**
	 * Guesses a value; if correct returns else updates the tree
	 * @param in the scanner from which to read input
	 * @return ret if guess is correct return this node else returns updated node
	 */
	public DecisionNode guess(Scanner in) {
		String response;
		System.out.println("Are you thinking of " + val + "?");
		response = in.nextLine();
		
		if (response.toLowerCase().equals("yes")) {
			System.out.println("Excellent, thanks!");
			return this;
		} else {
			return updateTree(in);
		}
		
	}
	
	/**
	 * writes a node to a file
	 * @param out the file to write to
	 */
	public void write(FileWriter out) throws IOException {
		out.write(val + "\n");
	}

}