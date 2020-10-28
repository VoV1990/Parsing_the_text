//в каждом предложении отсортировать слова по длине

public class SortWords {
    public SortWords(String str) {
        sortWords(str);
    }

    private static void sortWords(String str) {
        String[] sentences = str.split("[.?!]\\s*");
        String temp;
        for(int i = 0; i < sentences.length; i++) {
            temp = getSortSentences(sentences[i]);
            sentences[i] = temp;
        }
        System.out.println(concatToParagraph(sentences));
    }

    private static String getSortSentences(String sentence) {
        String[] words = sentence.split("[\\s\\p{Punct}]+");
        int wordLength1;
        int wordLength2;
        String temp;
        for(int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                wordLength1 = words[i].length();
                wordLength2 = words[j].length();
                if(wordLength1 > wordLength2) {
                    temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                }
            }
        }
        return concatToSentence(words);
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
