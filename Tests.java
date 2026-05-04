public class Tests {

    private int passed = 0;
    private int total = 0;

    private void runTest(String testName, Runnable test) {
        total++;
        try {
            test.run();
            passed++;
            System.out.println(testName + ": Passed");
        } catch (AssertionError e) {
            System.out.println(testName + ": Failed - " + e.getMessage());
        } catch (Exception e) {
            System.out.println(testName + ": Failed with error - " + e.getMessage());
        }
    }

    private void assertModification(String original, int length, String replacement, String expected) {
        TextModifier modifier = new TextModifier(original, length, replacement);
        String actual = modifier.modify();
        if (!actual.equals(expected)) {
            throw new AssertionError("Expected: \"" + expected + "\", Got: \"" + actual + "\"");
        }
    }

    public void runAllTests(String originalText, String replacementStr) {
        System.out.println("Running all tests\n");

        runTest("Test 1 (Standard case)", () ->
            assertModification(
                originalText,
                3, replacementStr,
                "Car golden sunset painted Car evening Car in bright shades of orange Car pink."
            )
        );

        runTest("Test 2 (Punctuation)", () ->
            assertModification("Cat, meet dog.", 3, "Bird", "Bird, meet Bird.")
        );

        runTest("Test 3 (Empty string)", () ->
            assertModification("", 3, "Car", "")
        );

        runTest("Test 4 (No words of target length)", () ->
            assertModification("Beautiful architecture", 3, "Car", "Beautiful architecture")
        );

        runTest("Test 5 (Only symbols and digits)", () ->
            assertModification("123 ... 456!", 3, "Car", "123 ... 456!")
        );

        runTest("Test 6 (Multiple spaces)", () ->
            assertModification("A  cat   is  here", 3, "dog", "A  dog   is  here")
        );

        runTest("Test 7 (Single word string)", () ->
            assertModification("Cat", 3, "Bird", "Bird")
        );

        runTest("Test 8 (Replacement with different length)", () ->
            assertModification("a cat and a dog", 3, "elephant", "a elephant elephant a elephant")
        );

        runTest("Test 9 (Exception on zero length)", () -> {
            try {
                new TextModifier("Any text", 0, "Replace");
                throw new AssertionError("Exception was not thrown");
            } catch (IllegalArgumentException e) {
            }
        });

        System.out.println("\nResult: Passed " + passed + " out of " + total + " tests.");
    }
}
