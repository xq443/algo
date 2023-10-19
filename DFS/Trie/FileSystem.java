package Trie;

import java.util.*;

public class FileSystem {
    /**
     * Design a data structure that simulates an in-memory file system.
     */
    FileNode root;

    public FileSystem() {
        root = new FileNode("/");
    }

    public List<String> ls(String path) {
        FileNode node = traverse(path);
        List<String> res = new ArrayList<>();
        if (node.isFile) res.add(node.path);
        else {
            res.addAll(node.next.keySet());
        }
        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        traverse(path);
    }

    public void addContentToFile(String filePath, String content) {
        FileNode node = traverse(filePath);
        node.isFile = true;
        node.content.append(content);
    }

    public String readContentFromFile(String filePath) {
        FileNode node = traverse(filePath);
        return node.content.toString();
    }

    public FileNode traverse(String filePath) { //filepath = "/a/b/c/d"
        FileNode node = root;
        String[] path = filePath.split("/");
        for (int i = 1; i < path.length; i++) {
            if (!node.next.containsKey(path[i])) {
                FileNode curr = new FileNode(path[i]);
                node.next.put(path[i], curr);
            }
            node.next.get(path[i]);

        }
        return node;
    }
    public static void main(String[] args) {
        // Create a new FileSystem
        FileSystem fileSystem = new FileSystem();
        // Test case input
        String[] commands = {
                "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"
        };

        String[][] arguments = {
                {"/"},
                {"/a/b/c"},
                {"/a/b/c/d", "hello"},
                {"/"},
                {"/a/b/c/d"}
        };

        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            String[] argument = arguments[i];

            switch (command) {
                case "ls":
                    List<String> result = fileSystem.ls(argument[0]);
                    System.out.println("ls(" + argument[0] + ") -> " + result);
                    break;
                case "mkdir":
                    fileSystem.mkdir(argument[0]);
                    System.out.println("mkdir(" + argument[0] + ")");
                    break;
                case "addContentToFile":
                    fileSystem.addContentToFile(argument[0], argument[1]);
                    System.out.println("addContentToFile(" + argument[0] + ", " + argument[1] + ")");
                    break;
                case "readContentFromFile":
                    String content = fileSystem.readContentFromFile(argument[0]);
                    System.out.println("readContentFromFile(" + argument[0] + ") -> " + content);
                    break;

            }
        }
    }
}
/**
 *      * Explanation
 *      * FileSystem fileSystem = new FileSystem();
 *      * fileSystem.ls("/");                         // return []
 *      * fileSystem.mkdir("/a/b/c");
 *      * fileSystem.addContentToFile("/a/b/c/d", "hello");
 *      * fileSystem.ls("/");                         // return ["a"]
 *      * fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 *      */

//T/S: n = depth(path), m = size(content)
//constructor: O(1)/O(1)
//ls: O(n)/O(n)
//mkdir: O(n)/O(n)
//addContentToFile: O(n + m)/O(n + m)
//readContentFromFile: O(n)/O(n)
