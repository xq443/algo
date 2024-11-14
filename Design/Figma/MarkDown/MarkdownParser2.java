package Figma.MarkDown;

public class MarkdownParser2 {

  public String parseItalic(String markdown) {
    StringBuilder result = new StringBuilder();
    int length = markdown.length();
    boolean inItalic = false;
    char delimiter = '\0';  // Tracks the current delimiter (* or _)
    int italicStart = -1;  // Tracks the start index of italic text

    // Validation step: Check for unmatched delimiters
    int openCount = 0;
    int closeCount = 0;

    for (int i = 0; i < length; i++) {
      char currentChar = markdown.charAt(i);

      // Check for italic delimiters (* or _)
      if (currentChar == '*' || currentChar == '_') {
        if (!inItalic) {
          // Start of italic text
//          if (i + 1 < length && markdown.charAt(i + 1) == currentChar) {
//            // Skip if it's a pair of delimiters (bold case)
//            result.append(currentChar);
//            i++;  // Skip the next marker as it's part of bold
//          }
//          else {
          inItalic = true;
          delimiter = currentChar;
//            result.append("<i>");
          openCount++;
        } else if (currentChar == delimiter) {
          // End of italic text
          inItalic = false;
//          result.append("</i>");
          closeCount++;
        }
      }
      else {
          // If it's an unmatched delimiter, just append the character
          result.append(currentChar);
        }
      }

    // Validation step: Check if open and close counts match
    if (openCount != closeCount) {
      return "Error: Unmatched italic delimiters.";
    }

    // Return the processed string
    return result.toString();
  }

  public static void main(String[] args) {
    MarkdownParser2 parser = new MarkdownParser2();

    // Edge case 1: Empty string
    System.out.println("Edge case 1: " + parser.parseItalic(""));

    // Edge case 2: No italic syntax
    System.out.println("Edge case 2: " + parser.parseItalic("This is a normal sentence."));

    // Edge case 3: Consecutive italic markers (single and double)
    System.out.println("Edge case 3: " + parser.parseItalic("This is *italic* text and _also italic_."));

    // Edge case 4: Multiple consecutive markers (bold case)
    System.out.println("Edge case 4: " + parser.parseItalic("This is **bold** and _italic_."));

    // Edge case 5: Unmatched delimiters
    System.out.println("Edge case 5: " + parser.parseItalic("This is *italic text."));

    // Edge case 6: Italic text with non-adjacent markers
    System.out.println("Edge case 6: " + parser.parseItalic("This *is* some _italic_ text."));

    // Edge case 7: Multiple sections of italic text
    System.out.println("Edge case 7: " + parser.parseItalic("This *is* italic, and this _is_ also italic."));

    // Edge case 8: No space between delimiters
    System.out.println("Edge case 8: " + parser.parseItalic("This *is*italic* text."));

    // Edge case 9: Text that starts and ends with italic markers
    System.out.println("Edge case 9: " + parser.parseItalic("*This is italic* text."));

    // Edge case 10: String with only delimiters
    System.out.println("Edge case 10: " + parser.parseItalic("* * _ _"));

    // Edge case 11: Mixed italic and bold with overlapping delimiters
    System.out.println("Edge case 11: " + parser.parseItalic("This is *italic **bold** italic*."));

    // Edge case 12: Text with both _ and * for italic
    System.out.println("Edge case 12: " + parser.parseItalic("This is *italic* and _italic_."));

    // Edge case 13: Large input with many markers
    System.out.println("Edge case 13: " + parser.parseItalic("*This* is _a_ large *test* with _multiple_ italic markers."));
  }
}
