public class TextModifierWithTest {

    public static String modifyText(String originalText, int searchLength, String replacementStr) {
        if (searchLength <= 0) {
            throw new IllegalArgumentException("Error: Search length must be greater than 0");
        }
        
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

    public static String testModification(String original, int length, String replacement, String expected) {
        try {
            String actual = modifyText(original, length, replacement);
            if (actual.equals(expected)) {
                return "Passed";
            }
            return "Failed (Expected: \"" + expected + "\", Got: \"" + actual + "\")";
        } catch (Exception e) {
            return "Failed with error: " + e.getMessage();
        }
    }

    public static String testException() {
        try {
            modifyText("Any text", 0, "Replace");
            return "Failed (Error was not thrown)";
        } catch (IllegalArgumentException e) {
            return "Passed";
        } catch (Exception e) {
            return "Failed (Different error thrown: " + e.getMessage() + ")";
        }
    }

    public static void runAllTests() {
        System.out.println("Runnig all tests");
        int passed = 0;
        int total = 0;

        total++;
        if (testModification("The golden sunset painted the evening sky in bright shades of orange and pink.", 3, "Car", 
            "Car golden sunset painted Car evening Car in bright shades of orange Car pink.").equals("Passed")) {
            passed++;
            System.out.println("Test 1 (Standard case): Passed");
        } else {
            System.out.println("Test 1 (Standard case): Failed");
        }

        total++;
        if (testModification("Cat, meet dog.", 3, "Bird", "Bird, meet Bird.").equals("Passed")) {
            passed++;
            System.out.println("Test 2 (Punctuation): Passed");
        } else {
            System.out.println("Test 2 (Punctuation): Failed");
        }

        total++;
        if (testModification("", 3, "Car", "").equals("Passed")) {
            passed++;
            System.out.println("Test 3 (Empty string): Passed");
        } else {
            System.out.println("Test 3 (Empty string): Failed");
        }

        total++;
        if (testModification("Beautiful architecture", 3, "Car", "Beautiful architecture").equals("Passed")) {
            passed++;
            System.out.println("Test 4 (No words of target length): Passed");
        } else {
            System.out.println("Test 4 (No words of target length): Failed");
        }

        total++;
        if (testModification("123 ... 456!", 3, "Car", "123 ... 456!").equals("Passed")) {
            passed++;
            System.out.println("Test 5 (Only symbols and digits): Passed");
        } else {
            System.out.println("Test 5 (Only symbols and digits): Failed");
        }

        total++;
        if (testModification("A  cat   is  here", 3, "dog", "A  dog   is  here").equals("Passed")) {
            passed++;
            System.out.println("Test 6 (Multiple spaces): Passed");
        } else {
            System.out.println("Test 6 (Multiple spaces): Failed");
        }

        total++;
        if (testModification("Cat", 3, "Bird", "Bird").equals("Passed")) {
            passed++;
            System.out.println("Test 7 (Single word string): Passed");
        } else {
            System.out.println("Test 7 (Single word string): Failed");
        }

        total++;
        if (testModification("a cat and a dog", 3, "elephant", "a elephant elephant a elephant").equals("Passed")) {
            passed++;
            System.out.println("Test 8 (Replacement with different length): Passed");
        } else {
            System.out.println("Test 8 (Replacement with different length): Failed");
        }

        total++;
        if (testException().equals("Passed")) {
            passed++;
            System.out.println("Test 9 (Exception handling): Passed");
        } else {
            System.out.println("Test 9 (Exception handling): Failed");
        }

        System.out.println("Result: Passed " + passed + " out of " + total + " tests.\n");
    }

    public static void main(String[] args) {
        runAllTests();

        System.out.println("Main program");
        
        int searchLength = 3;
        String replacementStr = "Car";
        String originalText = "The golden sunset painted the evening sky in bright shades of orange and pink.";

        try {
            String resultText = modifyText(originalText, searchLength, replacementStr);
            
            System.out.println("Source: " + originalText);
            System.out.println("Result: " + resultText);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.getMessage());
        }
    }
}