import java.util.Stack;
public class test {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("c");
        stack.push("a");
        stack.push("t");
        stack.push("h");
        stack.push("y");

        StringBuilder str = new StringBuilder();

        while(!stack.isEmpty()){
            str.insert(0, stack.pop());
        }
        // prints StringBuilder after insertion
        System.out.print("After insertion = ");
        System.out.println(str);
    }
}