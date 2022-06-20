import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogExtractor {

    private final String logName;

    LogExtractor(String fileName) {
        this.logName = fileName;
    }

    public HashMap<Integer, List<String>> extractAllLogLines() throws IOException {

        String line = null;
        HashMap<Integer, List<String>> sentenceWordsCountToLines = new HashMap();

        InputStream is = getClass().getResourceAsStream(this.logName);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while (null != (line = br.readLine())) {
            populateSentenceListByWordsNumber(sentenceWordsCountToLines, PIHelper.getLineWordNumber(line), line);
        }
        return sentenceWordsCountToLines;
    }

    private void populateSentenceListByWordsNumber(HashMap<Integer, List<String>> sentenceWordsCountToLines,
                                                   int wordsNum, String line) {
        if (sentenceWordsCountToLines.get(wordsNum) == null) {
            sentenceWordsCountToLines.put(wordsNum, new ArrayList());
        }
        sentenceWordsCountToLines.get(wordsNum).add(line);


    }
}
