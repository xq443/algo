package Figma.MarkDown;

public class MarkdownParser {

  public String parseItalic(String markdown) {
    StringBuilder result = new StringBuilder();
    int length = markdown.length();
    boolean inItalic = false;
    char delimiter = '\0';  // Tracks the current delimiter (* or _)
    int italicStart = -1;  // Tracks the start index of italic text

    for (int i = 0; i < length; i++) {
      char currentChar = markdown.charAt(i);

      // Check for italic delimiters (* or _)
      if (currentChar == '*' || currentChar == '_') {
        if (!inItalic) { // we are not inside an italic section
//          if (i + 1 < length && markdown.charAt(i + 1) == currentChar) { // **bold text**
//            // Skip if it's a pair of delimiters (bold case)
//            result.append(currentChar);
//            i++;
//          } else {
            inItalic = true;
            delimiter = currentChar;
//            result.append("<i>");
        } else if (currentChar == delimiter) { //If the current character matches the delimiter (either * or _),
          // it marks the end of the italic section,
          // so we set inItalic = false and append </i> to close the italic tag.
          inItalic = false;
//          result.append("</i>");
        }
      }else {
          // If it's an unmatched delimiter, just append the character
          result.append(currentChar);
        }
//      } else {
//        // Append regular characters
//        result.append(currentChar);
//      }
    }

    // Return the processed string
    return result.toString();
  }

  public static void main(String[] args) {
    MarkdownParser parser = new MarkdownParser();

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
