import java.util.*;

public class SimilarityFinder {

    PrivateInvestigatorLogger piLogger = new PrivateInvestigatorLogger();

    public List<List<LogLine>> findSimilarity(HashMap<Integer, List<String>> wordsNumToLinesMap) {
        List<List<LogLine>> result = new ArrayList();

        for (Map.Entry<Integer, List<String>> inputEntry : wordsNumToLinesMap.entrySet()) {
            Map<String[], List<LogLine>> similarityLMap = piLogger.processSimilarities(inputEntry.getValue());

            for (Map.Entry<String[], List<LogLine>> outputEntry : similarityLMap.entrySet()) {
                result.add(outputEntry.getValue());
            }
        }
        return result;
    }
}
