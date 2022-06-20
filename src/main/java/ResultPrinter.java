import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultPrinter {

    void printResult(List<List<LogLine>> result) {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < result.size(); i++) {
            List<String> wordsList = new ArrayList();
            for (int j = 0; j < result.get(i).size(); j++) {
                printPatternLines(result, i, wordsList, j);
            }
            printMatchGroup(str, wordsList);
        }
    }

    private void printPatternLines(List<List<LogLine>> result, int i, List<String> wordsList, int j) {
        int diffIndex = PIHelper.getExcludedWordIndexFromPattern(result.get(i).get(0).getTokens(), result.get(i).get(1).getTokens());
        LogLine line = result.get(i).get(j);

        wordsList.add(result.get(i).get(j).getTokens()[diffIndex]);
        System.out.println(line.getLine());
    }

    private void printMatchGroup(StringBuilder str, List<String> wordsList) {
        str.append("The changing word was: ").append(getWordDiffList(wordsList));
        System.out.println(str);
        str.delete(0, str.length());
    }

    private StringBuilder getWordDiffList(List<String> wordsList) {
        StringBuilder str = new StringBuilder();
        Iterator<String> it = wordsList.iterator();
        str.append(it.next());
        while (it.hasNext()) {
            str.append(", ").append(it.next());
        }
        return str;
    }
}
