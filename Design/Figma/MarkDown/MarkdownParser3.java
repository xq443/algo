package Figma.MarkDown;

public class MarkdownParser3 {

  public String parseItalic(String markdown) {
    StringBuilder result = new StringBuilder();
    int length = markdown.length();
    boolean inItalic = false;
    char delimiter = '\0';  // Tracks the current delimiter (* or _)

    // Loop through the markdown string to parse italic text
    for (int i = 0; i < length; i++) {
      char currentChar = markdown.charAt(i);

      // Check for italic delimiters (* or _)
      if (currentChar == '*' || currentChar == '_') {
        if (!inItalic) {
          // Start of italic text
          inItalic = true;
          delimiter = currentChar;
//          result.append("<i>");
        } else if (currentChar == delimiter) {
          // End of italic text
          inItalic = false;
//          result.append("</i>");
        }
      } else {
        // If it's not an italic delimiter, just append the character
        result.append(currentChar);
      }
    }

    // If the markdown ends with an unclosed italic tag, close it
//    if (inItalic) {
//      result.append("</i>");
//    }

    return result.toString();
  }

  public static void main(String[] args) {
    MarkdownParser3 parser = new MarkdownParser3();

    // Test case 1: Input with unmatched * at the end
    System.out.println("Test case 1: " + parser.parseItalic("This is *italic text."));

    // Test case 2: Input with nested * and _ markers
    System.out.println("Test case 2: " + parser.parseItalic("This *is *nested* italic* text."));

    // Test case 3: Mixed * and _ markers with mismatch
    System.out.println("Test case 3: " + parser.parseItalic("This _is_ italic and _another *mismatch* case."));

    // Test case 4: Input with unmatched _ marker at the beginning
    System.out.println("Test case 4: " + parser.parseItalic("_Unmatched start of italic and _continue._"));

    // Test case 5: Mixed * and _ markers
    System.out.println("Test case 5: " + parser.parseItalic("Text with *unmatched_ delimiter* in the middle."));

    // Test case 6: One unmatched * marker
    System.out.println("Test case 6: " + parser.parseItalic("Only one italic *marker."));

    // Test case 7: Input with mixed _ and * markers in a sentence
    System.out.println("Test case 7: " + parser.parseItalic("This *is _italic with mixed symbols_ that should be handled."));

    // Test case 8: Input with both _ and * markers having mismatched combinations
    System.out.println("Test case 8: " + parser.parseItalic("A _wrong* combination _of symbols*."));

    // Test case 9: Only one _ marker at the end
    System.out.println("Test case 9: " + parser.parseItalic("Only *italic*_text with a mismatch at the end."));

    // Test case 10: Input with both _ and * markers that need to be handled as separate italic sections
    System.out.println("Test case 10: " + parser.parseItalic("This *is* _italic_ text."));

    // Test case 11: Long string with mismatched markers
    System.out.println("Test case 11: " + parser.parseItalic("This **is _a large test_ with *multiple _italic markers."));

    // Test case 12: Input with only _ and * markers and no text
    System.out.println("Test case 12: " + parser.parseItalic("* * _ _"));
  }
}
