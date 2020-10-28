import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Cоздать приложение, разбирающее текст (текст хранится в строке) и позволяющее выполнять
//с текстом три различных операции: отсортировать абзацы по количеству предложений;
//в каждом предложении отсортировать слова по длине; отсортировать лексемы в предложении по убыванию
//количества вхождений заданного символа, а в случае равенства – по алфавиту.

public class Main {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        System.out.println("Please enter string:");
        String text = reader.readLine();
        if(text != null) chooseOption(text);
        else System.out.println("Please try again");
    }

    private static void chooseOption(String text) throws IOException {
        boolean stop = false;
        int option;
        do{
            System.out.println("Please choose one of the suggested options and enter its number.");
            System.out.println("1. Sort the paragraphs by the number of sentences.");
            System.out.println("2. Sort words by length in each sentence.");
            System.out.println("3. Sort tokens in a sentence in descending order of the number of " +
                    "occurrences of the specified character, and in case of equality – alphabetically.");
            option = Integer.parseInt(reader.readLine());
            if(option == 1 || option == 2 || option == 3) stop = true;
            else System.out.println("Please try again");
        } while (!stop);
        startSorting(option, text);
    }

    private static void startSorting(int option, String text) throws IOException {
        switch (option) {
            case 1 -> new SortParagraphs(text);
            case 2 -> new SortWords(text);
            case 3 -> {
                boolean stop = false;
                String symbol;
                do {
                    System.out.println("Please enter character:");
                    symbol = reader.readLine();
                    if (validateSymbol(symbol)) stop = true;
                    else System.out.println("Please enter character again");
                } while (!stop);
                new SortByCharacter(text, symbol);
            }
        }
    }

    private static boolean validateSymbol(String symbol) {
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-я]");
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }
}
