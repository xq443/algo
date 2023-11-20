public class AddTwoNum_recursion {
    public static ListNode addTwoLists(ListNode l1, ListNode l2){
        return addNums(l1, l2, 0);
    }
    public static ListNode addNums(ListNode l1, ListNode l2, int carry){
        if(l1 == null && l2 == null){
            return (carry > 0 )? new ListNode(carry): null;
        }
        int val1 = (l1 != null)? l1.val : 0;
        int val2 = (l2 != null)? l2.val : 0;
        int total = val1 + val2 + carry;

        ListNode ret = (l1 != null)? l1 : l2;
        ret.val = total % 10;
        int newCarry = total / 10;

        l1 = (l1.next != null)? l1.next : null;
        l2 = (l2.next != null)? l2.next : null;

        ret.next = addNums(l1, l2, newCarry);
        return ret;
    }
    public static void main(String[] args) {
        //AddTwoNum addTwoNum = new AddTwoNum();
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        ListNode result = addTwoLists(l1, l2);
        // Print the result (linked list representation)
        while (result != null) {
            System.out.print(result.val + " -> ");
            result = result.next;
        }
        System.out.println("null");

    }
}
