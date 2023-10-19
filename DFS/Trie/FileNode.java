package Trie;

import java.util.HashMap;

public class FileNode {
    String path;
    boolean isFile;
    StringBuilder content;
    HashMap<String, FileNode> next;

    public FileNode(String path) {
        this.path = path;
        isFile = false;
        content = new StringBuilder();
        next = new HashMap<>();
    }
    public boolean isFile(FileNode node){
        if(node.content.length() == 0) return false;
        else  return true;
    }
}


