

import java.util.*;

public class PrivateInvestigatorLogger {

    Map<String[], List<LogLine>> patternToLogLine;
    List<LogLine> candidateLogList;
    int id;
    int wordNumInLine;
    List<String> lines;


    /**
     * Main high level functionality method ~ start
     *
     * @param lines
     * @return
     */
    public Map<String[], List<LogLine>> processSimilarities(List<String> lines) {

        initilizeFieldsForDiffrenetWordCountProcessing(lines);

        for (String line : lines) {
            id++;
            LogLine currentLogLine = convertLineToLogLine(id, line);

            int indexDiffer = findPatternInMapForCurrentLine(currentLogLine);

            if (indexDiffer > -1) {
                addPatternAndCandidateToMapAndList(currentLogLine);
            } else {
                searchForMatchInCandidateListAndAddToMapIfFound(currentLogLine);
            }
        }
        return patternToLogLine;
    }

    private void initilizeFieldsForDiffrenetWordCountProcessing(List<String> lines) {
        patternToLogLine = new LinkedHashMap();
        candidateLogList = new ArrayList();
        this.wordNumInLine = PIHelper.getLineWordNumber(lines.get(0));
        this.id = 0;
        this.lines = lines;
    }


    private void searchForMatchInCandidateListAndAddToMapIfFound(LogLine logLine) {
        if (candidateLogList.isEmpty()) {
            candidateLogList.add(logLine);
            return;
        }

        for (int i = candidateLogList.size(); i > 0; i--) {
            int candidateFromListIndex = i - 1;
            LogLine currCandidate = candidateLogList.get(candidateFromListIndex);

            int isMatchFoundIndex = isMatchFromCandidateListFound(currCandidate.getTokens(), logLine.getTokens());

            if (isMatchFoundIndex > -1) {
                createOrMergePatternFromCandidateInMap(logLine, candidateFromListIndex, isMatchFoundIndex);
            }
        }
        candidateLogList.add(logLine);
    }


    private void createOrMergePatternFromCandidateInMap(LogLine currentLogLine, int i, int matchIndex) {

        String[] pattern = createPattern(matchIndex, i);

        if (patternToLogLine.get(pattern) == null) {
            createNewPatternInMap(currentLogLine, i, matchIndex);
        } else {
            patternToLogLine.get(pattern).add(currentLogLine);
        }

    }

    private void createNewPatternInMap(LogLine currentLogLine, int i, int matchIndex) {
        List<LogLine> list = new LinkedList();

        LogLine canLogLine = candidateLogList.get(i);

        list.add(canLogLine);

        list.add(currentLogLine);

        patternToLogLine.put(createPattern(matchIndex, i), list);
    }

    private void addPatternAndCandidateToMapAndList(LogLine currentLogLine) {

        for (Map.Entry<String[], List<LogLine>> entry : patternToLogLine.entrySet()) {
            int isSimilar = findSimiliarSentenceIndex(entry.getKey(), currentLogLine.getTokens());
            if (isSimilar > -1) {
                patternToLogLine.get(entry.getKey()).add(currentLogLine);
                break;
            }
        }
        candidateLogList.add(currentLogLine);
        return;
    }

    private String[] createPattern(int indexDiff, int i) {
        String[] words = candidateLogList.get(i).getTokens();
        int wordsNum = words.length;
        String[] patternArray = new String[wordsNum];
        System.arraycopy(words, 0, patternArray, 0, wordsNum);
        patternArray[indexDiff] = PIHelper.PATTERN_DELIMITOR;
        return patternArray;
    }

    private int isMatchFromCandidateListFound(String[] candidateTokens, String[] currentTokens) {
        int indexDiff = -1;

        indexDiff = findSimiliarSentenceIndex(candidateTokens, currentTokens);
        return indexDiff;
    }


    private int findPatternInMapForCurrentLine(LogLine logLine) {
        if (patternToLogLine.isEmpty()) return -1;
        for (String[] words : patternToLogLine.keySet()) {

            int indexDiff = findSimiliarSentenceIndex(words, logLine.getTokens());
            if (indexDiff > -1) {
                return indexDiff;
            }
        }
        return -1;
    }

    private int findSimiliarSentenceIndex(String[] patternWords, String[] currentLineWords) {
        int index = -1;
        int counter = 0;

        for (int i = 0; i < patternWords.length; i++) {
            if (!patternWords[i].equals(currentLineWords[i])) {
                index = i;
                counter++;
            }
            if (counter > 1)
                break;
        }
        if (counter == 1)
            return index;
        else
            return -1;
    }

    private LogLine convertLineToLogLine(int id, String line) {
        String[] tokens = PIHelper.SplitLineIntoWordTolkens(line);
        return new LogLine(id, line, tokens);

    }

}
