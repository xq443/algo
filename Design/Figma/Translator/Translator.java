package Figma.Translator;

import java.util.Map;

public class Translator {

  // Basic translation method for variable replacement
  public String translate(String sentence, Map<String, String> map) {
    for (Map.Entry<String, String> entry : map.entrySet()) {
      sentence = sentence.replace("{" + entry.getKey() + "}", entry.getValue());
    }
    return sentence;
  }

  // Pluralization method, handles singular/plural based on count
  public String pluralize(String sentence, Map<String, String> map) {
    for (Map.Entry<String, String> entry : map.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();

      if (key.equals("animal_count")) {
        // Handle the pluralization logic
        int count = Integer.parseInt(value);

        // Handle pluralization for the animal name
        String animalKey = "animal";
        String animalValue = map.get(animalKey);

        String pluralAnimalValue = animalValue + "s";  // Simple pluralization by adding "s"

        // Replace the animal_count variable with the value
        sentence = sentence.replace("{" + key + "}", value);

        // Replace the singular/plural animal variable depending on count
        sentence = sentence.replace("{" + animalKey + "}", count == 1 ? animalValue : pluralAnimalValue);
      } else {
        // Regular variable replacement
        sentence = sentence.replace("{" + key + "}", value);
      }
    }
    return sentence;
  }

  // Method to handle nested translations and resolve placeholders
  public String nestedTranslate(String sentence, Map<String, String> map) {
    boolean hasPlaceholders = true;
    while (hasPlaceholders) {
      hasPlaceholders = false;

      // Process all the variables in the map
      for (Map.Entry<String, String> entry : map.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();

        // Replace variables in the sentence
        if (sentence.contains("{" + key + "}")) {
          sentence = sentence.replace("{" + key + "}", value);
          hasPlaceholders = true; // Indicate that there are still placeholders to replace
        }
      }

      // After replacing regular variables, process pluralization if needed
      sentence = pluralize(sentence, map);
    }
    return sentence;
  }

  public static void main(String[] args) {
    Translator translator = new Translator();

    // Test 1: Basic translation
    String sentence1 = "I like {animal}.";
    System.out.println(translator.translate(sentence1, Map.of("animal", "dog")));  // Output: I like dog.

    // Test 2: Sentence with animal count and pluralization
    String sentence2 = "I have {animal_count} {animal}.";
    Map<String, String> variables = Map.of("animal", "dog", "animal_count", "2");
    System.out.println(translator.pluralize(sentence2, variables));  // Output: I have 2 dogs.

    // Test 3: Test with singular count
    Map<String, String> variablesSingular = Map.of("animal", "dog", "animal_count", "1");
    System.out.println(translator.pluralize(sentence2, variablesSingular));  // Output: I have 1 dog.

    // Test 4: Test with nested variables
    Map<String, String> variable = Map.of(
        "animal", "dog",
        "animal_count", "2",
        "owner", "John",
        "sentence", "I have {animal_count} {animal}."
    );

    // Sample sentence with nested variables
    String sentence = "{owner} says: {sentence}";
    // Translate the sentence
    System.out.println(translator.nestedTranslate(sentence, variable));  // Output: John says: I have 2 dogs.
  }
}

// The overall time complexity for nestedTranslate() will be O(p * m * n),
// where p is the number of placeholders (or iterations to resolve them), m is the number of variables in the map, and n is the length of the sentence.
// The map itself requires O(m) space, where m is the number of variables.