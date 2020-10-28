//отсортировать абзацы по количеству предложений

public class SortParagraphs {
    public SortParagraphs(String str) {
        sortParagraphs(str);
    }

    private static void sortParagraphs(String str) {
        String[] paragraphs = str.split("\u2029");
        int paragraphLength1;
        int paragraphLength2;
        String temp;
        for(int i = 0; i < paragraphs.length; i++) {
            for (int j = i + 1; j < paragraphs.length; j++) {
                paragraphLength1 = getQuantityOfSentences(paragraphs[i]);
                paragraphLength2 = getQuantityOfSentences(paragraphs[j]);
                if(paragraphLength1 > paragraphLength2) {
                    temp = paragraphs[i];
                    paragraphs[i] = paragraphs[j];
                    paragraphs[j] = temp;
                }
            }
        }
        System.out.println(concatParagraph(paragraphs));
    }

    private static int getQuantityOfSentences(String paragraph) {
        String pattern = "[.?!]";
        String[] sentences = paragraph.split(pattern);
        return sentences.length;
    }

    private static String concatParagraph(String[] paragraphs) {
        StringBuilder builder = new StringBuilder();
        for(String s : paragraphs) builder.append(s).append("\u2029");
        return builder.toString();
    }
}
