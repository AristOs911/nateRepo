public class LogLine {

    private int id;
    private String[] tokens;
    private String line;
    private int diffWordIndex;


    public LogLine(int id, String line, String[] tokens) {
        this.line = line;
        this.tokens = createTokensArray(tokens);
        this.id = id;
    }

    private String[] createTokensArray(String[] tokens) {
        String[] _tokens = new String[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            _tokens[i] = tokens[i];
        }
        return _tokens;
    }

    public String[] getTokens() {
        return tokens;
    }

    public String getLine() {
        return line;
    }

    public void setDiffWordIndex(int matchIndex) {
        this.diffWordIndex = matchIndex;
    }
}
