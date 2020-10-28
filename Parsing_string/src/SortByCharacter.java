import java.util.regex.Matcher;
import java.util.regex.Pattern;

//отсортировать лексемы в предложении по убыванию
//количества вхождений заданного символа, а в случае равенства – по алфавиту

public class SortByCharacter {
    public SortByCharacter(String str, String symbol) {
        sortWords(str, symbol);
    }

    private static void sortWords(String str, String symbol) {
        String[] sentences = str.split("[.?!]\\s*");
        String temp;
        for(int i = 0; i < sentences.length; i++) {
            temp = getSortSentences(sentences[i], symbol);
            sentences[i] = temp;
        }
        System.out.println(concatToParagraph(sentences));
    }

    private static String getSortSentences(String sentence, String symbol) {
        String[] words = sentence.split("[\\s\\p{Punct}]+");
        int countSymbols1;
        int countSymbols2;
        String temp;
        for(int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                countSymbols1 = getCountOfSymbols(words[i], symbol);
                countSymbols2 = getCountOfSymbols(words[j], symbol);
                if(countSymbols1 < countSymbols2) {
                    temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                } else if (countSymbols1 == countSymbols2) {
                    if (words[i].compareToIgnoreCase(words[j]) > 0) {
                        temp = words[i];
                        words[i] = words[j];
                        words[j] = temp;
                    }
                }
            }
        }
        return concatToSentence(words);
    }

    private static int getCountOfSymbols(String word, String symbol) {
        Pattern pattern = Pattern.compile(symbol.toLowerCase());
        Matcher matcher = pattern.matcher(word.toLowerCase());
        int count = 0;
        while (matcher.find())
            count++;
        return count;
    }

    private static String concatToSentence(String[] words) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            if(i != words.length - 1) builder.append(words[i]).append(" ");
            else builder.append(words[i]).append(". ");
        }
        return builder.toString();
    }

    private static String concatToParagraph(String[] sentence) {
        StringBuilder builder = new StringBuilder();
        for(String s : sentence) {
            builder.append(s);
        }
        return builder.toString();
    }
}
