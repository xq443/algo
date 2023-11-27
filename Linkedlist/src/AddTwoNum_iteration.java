public class AddTwoNum_iteration {
    public ListNode addTwoList(ListNode l1, ListNode l2){
        ListNode head = l1;
        ListNode tail = l1;
        int carry = 0;
        while(l1 != null || l2 != null){
            int val1 = (l1 != null)? l1.val : 0;
            int val2 = (l2 != null)? l2.val : 0;

            int total = val1 + val2 + carry;
            l1 = (l1.next != null)? l1.next : null;
            l2 = (l2.next != null)? l2.next : null;

            carry = total / 10;
            tail.val = total % 10;

            if(l1 != null || l2 != null){
                tail.next = new ListNode();
                tail = tail.next;
            }
        }
        if(carry > 0){
            assert tail != null;
            tail.next = new ListNode(carry);
        }
        return  head;
    }

    public static void main(String[] args) {
        AddTwoNum_iteration addTwoNumIteration = new AddTwoNum_iteration();
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        ListNode ret = addTwoNumIteration.addTwoList(l1, l2);
        while(ret != null){
            System.out.print(ret.val + " -> ");
            ret = ret.next;
        }
        System.out.println("null");

    }
}
