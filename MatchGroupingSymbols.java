import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; 
import java.util.Stack; 

public class MatchGroupingSymbols {
    
    public static void main(String[] args) {
        if (args.length !=1) {
            System.out.println("Usage: java MatchGroupingSymbols <source-code-file>");
            return;
        }

        String fileName = args[0];

        try {
            // check for correct grouping symbols
            if (checkMatchGroupingSymbols(fileName)) {
                System.out.println("Good job, the grouping symbols in the file are correct.");
            } else {
                System.out.println("Sorry, the grouping symbols in the file are incorrect.");
            }
        } catch (IOException e) {
            // error checking
            System.out.println("Error reading the file: " + e.getMessage());
            
        }
    }

    private static boolean checkMatchGroupingSymbols(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Stack<Character> stack = new Stack<>();

            int lineNumber = 1;
            String line;

            // iterate thru each file line
            while ((line = reader.readLine()) != null) {
                for (char symbol : line.toCharArray()) {
                    // check if character is opening symbol
                    if (isOpeningSymbol(symbol)) {
                        stack.push(symbol);
                    } else if (isClosingSymbol(symbol)) {
                        // check character for closing grouping symbol matching opening symbol
                        if (stack.isEmpty() || !isMatchingPair(stack.pop(), symbol)) {
                            System.out.println("Error found at line " + lineNumber);
                            return false;
                            
                        }
                    }
                }
                lineNumber++;
            }

            // check for unclosed groupipng symbols
            if (!stack.isEmpty()) {
                System.out.println("Error found: unclosed grouping symbols.");
                return false;
            }

            return true;
        }
    }

    // check if character is an opening symbol
    private static boolean isOpeningSymbol(char symbol) {
        return symbol == '(' || symbol == '{' || symbol == '[';    
    }

    // check if character is a closing symbol
    private static boolean isClosingSymbol(char symbol) {
        return symbol == ')' || symbol == '}' || symbol == ']';
    }
  
    // check opening & closing symbols match
    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '{' && closing == '}') ||
                (opening == '[' && closing == ']');
    }
}
