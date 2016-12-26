import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public interface DecisionNode {

	public boolean isGuess();
	public String toString();
 	public int countObjects();
 	public DecisionNode guess(Scanner in);
 	public void write(FileWriter out) throws IOException;
 }