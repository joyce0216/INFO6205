package edu.northeastern.Joyce;

public class codeLab2 {
    //LeetCode 83 - Remove duplicates from sorted Link List
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        while (temp != null && temp.next != null) {
            if (temp.next.val == temp.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }

    //LeetCode 19 - Remove Nth node from End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //we need dummy variable for the edge case: if the kth from the end is the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp = dummy;
        ListNode prev = dummy;

        for (int i = 0; i <= n; i++) {
            temp = temp.next;
        }

        while (temp != null) {
            temp = temp.next;
            prev = prev.next;
        }
        prev.next = prev.next.next;
        return dummy.next;
    }

    //LeetCode 86 - Partition List around a value X
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode temp = dummy1;
        ListNode dummy2 = new ListNode(0);
        ListNode curr = dummy2;

        while (head != null) {

            if (head.val < x) {
                temp.next = head;
                temp = temp.next;
            } else {
                curr.next = head;
                curr = curr.next;
            }
            head = head.next;
        }
        curr.next = null;
        temp.next = dummy2.next;
        return dummy1.next;
    }

    //LeetCode 708 - Insert into a Sorted Circular Linked List
    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    ;

    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node newNode = new Node();
            newNode.val = insertVal;
            newNode.next = newNode;
            return newNode;
        }

        Node max = head;
        //find the max num in the list
        while (max.next != head && max.val <= max.next.val) {
            //max.next != head (to avoid the list only have the head)
            max = max.next;
        }
        //in a sorted linked list, the min is next to the max
        Node min = max.next;
        Node cur = min;
        // case 1 : at the beginning / the last
        if (insertVal >= max.val || insertVal <= min.val) {
            Node node = new Node(insertVal, min);
            max.next = node;
        } else {
            // case2: find the appropriate place
            while (cur.next.val < insertVal) {
                cur = cur.next;
            }
            Node node = new Node(insertVal, cur.next);
            cur.next = node;
        }
        return head;
    }


    //LeetCode 1290 - Convert Binary Number in a Linked List to Integer
    //recursion
    class Solution {
        int output = 0;

        public int getDecimalValue(ListNode head) {
            helper(head);
            return output;
        }

        public int helper(ListNode head) {

            if (head == null) {
                return 0;
            }

            int temp = helper(head.next);
            output += Math.pow(2, temp) * head.val;
            return temp + 1;
        }


    }
}
