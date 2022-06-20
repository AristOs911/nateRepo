import java.io.IOException;
import java.util.*;

public class PIAssignment {

    /*
        main class to execute the assignment, strat from here.
     */
    public static void main(String[] args) throws IOException {

        LogExtractor lExtractor = new LogExtractor("PILog.txt");
        SimilarityFinder sFinder = new SimilarityFinder();
        ResultPrinter resPrinter = new ResultPrinter();

        HashMap<Integer, List<String>> wordsNumToLinesMap = lExtractor.extractAllLogLines();
        List<List<LogLine>> result = sFinder.findSimilarity(wordsNumToLinesMap);
        resPrinter.printResult(result);

    }
}
