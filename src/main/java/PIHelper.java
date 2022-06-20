public class PIHelper {

    public static final int DATE_CHAR_OFFSET = 20;
    public static final String PATTERN_DELIMITOR = "-";

    public static int getLineWordNumber(String line) {
        return line.substring(PIHelper.DATE_CHAR_OFFSET).split(" ").length;
    }

    public static String[] SplitLineIntoWordTolkens(String input) {
        return input.substring(PIHelper.DATE_CHAR_OFFSET).split(" ");
    }

    public static int getExcludedWordIndexFromPattern(String[] patternWords, String[] currentLineWords) {
        int index = -1;
        for (int i = 0; i < patternWords.length; i++) {
            if (!patternWords[i].equals(currentLineWords[i])) {
                index = i;
            }
        }
        return  index;
    }
}

