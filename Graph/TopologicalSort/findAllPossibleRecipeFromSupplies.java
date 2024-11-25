import java.util.*;

public static List<String> findAllPossibleRecipeFromSupplies(String[] recipes,
    List<List<String>> ingredients, String[] supplies) {

    //build up the graph

  Map<String, List<String>> next = new HashMap<>();
  Map<String, Integer> inDegree = new HashMap<>();

  for (int i = 0; i < recipes.length; i++) {
    for (int j = 0; j < ingredients.get(i).size(); j++) {
      String ingredient = ingredients.get(i).get(j);
      next.computeIfAbsent(ingredient, k -> new ArrayList<>()).add(recipes[i]);
      inDegree.put(recipes[i], inDegree.getOrDefault(recipes[i], 0) + 1);
    }
  }

  //queue
  Queue<String> q = new LinkedList<>(Arrays.asList(supplies));

  //bfs
  List<String> ans = new ArrayList<>();
  while (!q.isEmpty()) {
    String curr = q.poll();
    if (Arrays.asList(recipes).contains(curr)) {
      ans.add(curr);
    }
    if (next.containsKey(curr)) {
      for (String s : next.get(curr)) {
        inDegree.put(s, inDegree.get(s) - 1);
        if (inDegree.get(s) == 0) {
          q.add(s);
        }
      }
    }
  }
  return ans;
}

public static void main(String[] args) {
  String[] recipes = {"bread", "sandwich", "burger"};
  List<List<String>> ingredient = Arrays.asList(
      Arrays.asList("yeast", "flour"),
      Arrays.asList("bread", "meat"),
      Arrays.asList("sandwich", "meat", "bread")
  );
  String[] supplies = {"yeast", "flour", "meat"};

  List<String> res = findAllPossibleRecipeFromSupplies(recipes, ingredient, supplies);

  //print the res
  for (String re : res) {
    System.out.println(re);
  }
}
