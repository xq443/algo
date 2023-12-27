import java.util.PriorityQueue;

public class mergekSortedLists_v2 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
                (a,b) -> (a.val - b.val)
        );
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        for(ListNode list : lists){
            if(list != null) minHeap.offer(list);
        }
        //At first enqueue the ListNode 1->4->5, 1->3->4, 2->6
        //Pop the smallest node and offer its next node if it is not null.4->5, 3->4, 6

        while(!minHeap.isEmpty()){
            ListNode curr = minHeap.poll();
            prev.next = curr;
            prev = prev.next;
            if(curr.next != null) minHeap.offer(curr.next);
        }
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
        ListNode result = new mergekSortedLists_v2().mergeKLists(lists);
        // Printing the merged list
        printList(result);
    }
}
