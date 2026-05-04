public class TextModifier {

    private final String originalText;
    private final int searchLength;
    private final String replacementStr;

    public TextModifier(String originalText, int searchLength, String replacementStr) {
        if (searchLength <= 0) {
            throw new IllegalArgumentException("Error: Search length must be greater than 0");
        }
        this.originalText = originalText;
        this.searchLength = searchLength;
        this.replacementStr = replacementStr;
    }

    public String modify() {
        StringBuilder text = new StringBuilder(originalText);
        int wordStart = -1;

        for (int i = 0; i <= text.length(); i++) {
            boolean isLetter = (i < text.length()) && Character.isLetter(text.charAt(i));

            if (isLetter) {
                if (wordStart == -1) {
                    wordStart = i;
                }
            } else {
                if (wordStart != -1) {
                    int currentWordLength = i - wordStart;

                    if (currentWordLength == searchLength) {
                        text.replace(wordStart, i, replacementStr);
                        i += (replacementStr.length() - currentWordLength);
                    }
                    wordStart = -1;
                }
            }
        }

        return text.toString();
    }

    public String getOriginalText() {
        return originalText;
    }

    public int getSearchLength() {
        return searchLength;
    }

    public String getReplacementStr() {
        return replacementStr;
    }
}
