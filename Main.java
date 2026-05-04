public class Main {

    public static void main(String[] args) {
        Tests tests = new Tests();

        System.out.println("\nMain program");

        int searchLength = 3;
        String replacementStr = "Car";
        String originalText = "The golden sunset painted the evening sky in bright shades of orange and pink.";
        //String originalText = "";

        tests.runAllTests(originalText, replacementStr);

        try {
            TextModifier modifier = new TextModifier(originalText, searchLength, replacementStr);
            String resultText = modifier.modify();

            System.out.println("Source: " + modifier.getOriginalText());
            System.out.println("Result: " + resultText);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.getMessage());
        }
    }
}
