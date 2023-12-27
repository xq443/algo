public class mergekSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        // Use the 0-th list as a return list
        for (int i = 1; i < lists.length; ++i) {
            lists[0] = mergeList(lists[0], lists[i]);
        }

        return lists[0];
        //return mergeDivideandConquer(lists, 0, lists.length - 1);
    }
    private ListNode mergeDivideandConquer(ListNode[] lists, int left, int right){
        if(left > right) return null;
        if(left == right) return lists[left];
        int mid = left + (right - left) /2 ;
        ListNode low = mergeDivideandConquer(lists, left, mid);
        ListNode high = mergeDivideandConquer(lists, mid + 1, right);
        return mergeList(low, high);
    }
    private ListNode mergeList(ListNode n1, ListNode n2){
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while(n1 != null && n2 != null){
            if(n1.val < n2.val){
                prev.next = n1;
                n1 = n1.next;
            }else {
                prev.next = n2;
                n2 = n2.next;
            }
            prev = prev.next;
        }

        prev.next = (n1 != null)? n1 : n2;
        return dummy.next;
    }
    private static ListNode createListFromArray(int[] arr) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        for (int num : arr) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummy.next;
    }
    // Utility function to print the linked list
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print("->");
            }
            current = current.next;
        }
    }
    public static void main(String[] args) {
        // Creating the input lists
        ListNode[] lists = new ListNode[3];
        lists[0] = createListFromArray(new int[]{1, 4, 5});
        lists[1] = createListFromArray(new int[]{1, 3, 4});
        lists[2] = createListFromArray(new int[]{2, 6});

        // Merging the lists using the provided solution
        ListNode result = new mergekSortedLists().mergeKLists(lists);
        // Printing the merged list
        printList(result);
    }
}
//TC O(NlogN)
//SC O(logN)