package edu.northeastern.Joyce;

import java.util.*;

public class WEEK2 {
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

    // 328 Odd Even LinkedList
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode front = head;
        ListNode back = head.next;
        ListNode backHead = head.next;
        while(back != null && back.next != null){
            front.next = back.next;
            front = front.next;
            back.next = front.next;
            back = back.next;
        }
        front.next = backHead;
        return head;
    }

    //1474 Delete N nodes after M nodes of LinkedList
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode curr = head;
        ListNode prev = head;

        while(curr != null){
            int skipNode = m;
            int deleteNode = n;
            //skip m nodes
            while(curr != null && skipNode-- > 0){
                prev = curr;
                curr = curr.next;
            }
            //delete n nodes
            while(curr != null && deleteNode-- > 0){
                curr = curr.next;
            }
            prev.next = curr;
        }
        return head;
    }

    //237 Delete a node in a linkedlinst
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    //725 Split linkedlist in parts
    public ListNode[] splitListToParts(ListNode root, int k){
        ListNode[] ans = new ListNode[k];
        ListNode head = root;
        int N = 0;
        while (head != null){
            head = head.next;
            N++;
        }

        int length = N / k;
        int remain = N % k;

        head = root;
        ListNode prev = null;

        for(int i = 0; i < k; i++, remain--){
            ans[i] = head;
            for (int j = 0; j < length + ((remain > 0) ? 1 : 0); j++){
                prev = head;
                head = head.next;
            }
            if (prev != null){
                prev.next = null;
            }
        }
        return ans;
    }

    //82 Remove duplicates from sorted linkelist II
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null && curr.next.next != null){
            if(curr.next.val == curr.next.next.val){
                int val = curr.next.val;
                while(curr.next != null && curr.next.val == val){
                    curr.next = curr.next.next;
                }
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    //143 Reorder List
    public void reorderList(ListNode head) {
        //find half
        //reverse list
        //remodel a list
        if(head == null){
            return;
        }

        ListNode mid = findMid(head);
        ListNode s2 = mid.next;
        mid.next = null;
        s2 = reverse(s2);
        ListNode s1 = head;

        while(s1 != null && s2 != null){
            ListNode next = s1.next;
            s1.next = s2;
            s2 = s2.next;
            s1.next.next = next;
            s1 = next;
        }
    }
    public ListNode findMid(ListNode head){
        ListNode front = head;
        ListNode back = head;
        while(front != null && front.next != null){
            front = front.next.next;
            back = back.next;
        }
        return back;
    }
    public ListNode reverse(ListNode head){
        ListNode newHead = null;
        while (head != null){
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    //1019 Next greater node in linkedlist
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        //add all the element to the arraylist
        while(head != null){
            list.add(head.val);
            head = head.next;
        }
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            //put the index in the stack
            //if index i val > stack.top.val
            //ans[stack.pop] = index i .val
            while(!stack.isEmpty() && list.get(i) > list.get(stack.peek())) {
                ans[stack.pop()] = list.get(i);
            }
            stack.push(i);
        }
        return ans;
    }

    //24 Swap node in pairs
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;

        while(head != null && head.next != null){
            ListNode front = head;
            ListNode back = head.next;

            prev.next = back;
            front.next = back.next;
            back.next = front;

            prev = front;
            head = front.next;

        }
        return dummy.next;
    }

    //203 Remove Linkedlist element
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;
        while(curr.next != null){
            if(curr.next.val == val){
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    //20 Valid Parentheses
    class Solution {
        HashMap<Character, Character> pMap = new HashMap<Character, Character>();
        public boolean isValid(String s) {
            pMap.put(')' , '(');
            pMap.put('}' , '{');
            pMap.put(']' , '[');

            Stack<Character> stack = new Stack<Character>();

            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);

                if(pMap.containsKey(c)){
                    char top = stack.empty() ? '#' : stack.pop();
                    if(pMap.get(c) != top){
                        return false;
                    }
                } else {
                    stack.push(c);
                }
            }
            return stack.isEmpty();
        }
    }

    //71 Simplify Path
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        //split the path into array of string by slash
        String[] arr = path.split("/");

        for(int i = 0; i < arr.length; i++) {
            //remove the space of the word i in the front and back
            String curr = arr[i].trim();

            if(curr == null || curr.length() == 0 || curr.equals(".")) {
                continue;
            }
            //return to up level
            if(curr.equals("..")) {
                //if the stack is empty, nothing to pop
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(curr);
            }
        }
        String res = "";

        while(!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }

        return res.length() == 0 ? "/": res;

    }

    //155 Min stack
    class MinStack {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        /** initialize your data structure here. */
        public MinStack() {
        }


        public void push(int x) {
            stack1.push(x);
            if (stack2.isEmpty()){
                stack2.push(x);
            } else {
                int min = stack2.peek();
                stack2.push(Math.min(min, x));
            }

        }

        public void pop() {
            stack1.pop();
            stack2.pop();
        }

        public int top() {
            return stack1.peek();
        }

        public int getMin() {
            return stack2.peek();
        }
    }

    //394 Decode String
    public String decodeString(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<String> strStack = new ArrayDeque<>();

        StringBuilder tail = new StringBuilder();
        int n = s.length();
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                while(i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                numStack.push(num);
            } else if (c == '[') {
                strStack.push(tail.toString());
                tail = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder(strStack.pop());
                int repeatedTimes = numStack.pop();
                for (int j = 0; j < repeatedTimes; j++) {
                    temp.append(tail);
                }
                tail = temp;
            } else {
                tail.append(c);
            }
        }
        return tail.toString();
    }

    //402 Remove K digits
    public String removeKdigits(String num, int k) {
        LinkedList<Character> list = new LinkedList<Character>();

        for(char digit : num.toCharArray()) {
            while(list.size() > 0 && k > 0 && list.peekLast() > digit) {
                list.removeLast();
                k -= 1;
            }
            list.addLast(digit);
        }

        // remove the remaining digits from the tail.
        for(int i=0; i<k; ++i) {
            list.removeLast();
        }

        // build the final string, while removing the leading zeros.
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        for(char digit: list) {
            if(leadingZero && digit == '0') continue;
            leadingZero = false;
            ret.append(digit);
        }

        //return the final string
        if (ret.length() == 0) return "0";
        return ret.toString();
    }

    //456 132 Pattern
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3)
            return false;
        Stack < Integer > stack = new Stack < > ();
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            min[i] = Math.min(min[i - 1], nums[i]);
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] > min[j]) {
                while (!stack.isEmpty() && stack.peek() <= min[j])
                    stack.pop();
                if (!stack.isEmpty() && stack.peek() < nums[j])
                    return true;
                stack.push(nums[j]);
            }
        }
        return false;
    }

    //496 Next Greater Element I
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack < Integer > stack = new Stack < > ();
        HashMap < Integer, Integer > map = new HashMap < > ();
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.empty() && nums2[i] > stack.peek())
                map.put(stack.pop(), nums2[i]);
            stack.push(nums2[i]);
        }
        while (!stack.empty())
            map.put(stack.pop(), -1);
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    //503 Next Greater Element II
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }

    //735 Asteroid Collision
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack();
        for (int ast: asteroids) {
            collision: {
                while (!stack.isEmpty() && ast < 0 && 0 < stack.peek()) {
                    if (stack.peek() < -ast) {
                        stack.pop();
                        continue;
                    } else if (stack.peek() == -ast) {
                        stack.pop();
                    }
                    break collision;
                }
                stack.push(ast);
            }
        }

        int[] ans = new int[stack.size()];
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.pop();
        }
        return ans;
    }

    //739 Daily Temprature
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack();
        for (int i = T.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }

    //844 Backspace String Compare
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
            while (i >= 0) { // Find position of next possible char in build(S)
                if (S.charAt(i) == '#') {skipS++; i--;}
                else if (skipS > 0) {skipS--; i--;}
                else break;
            }
            while (j >= 0) { // Find position of next possible char in build(T)
                if (T.charAt(j) == '#') {skipT++; j--;}
                else if (skipT > 0) {skipT--; j--;}
                else break;
            }
            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0))
                return false;
            i--; j--;
        }
        return true;
    }

    //856 Score of Parentheses
    public int scoreOfParentheses(String S) {
        return F(S, 0, S.length());
    }

    public int F(String S, int i, int j) {
        //Score of balanced string S[i:j]
        int ans = 0, bal = 0;

        // Split string into primitives
        for (int k = i; k < j; ++k) {
            bal += S.charAt(k) == '(' ? 1 : -1;
            if (bal == 0) {
                if (k - i == 1) ans++;
                else ans += 2 * F(S, i+1, k);
                i = k+1;
            }
        }

        return ans;
    }

    //921 Minimum Add to make Parentheses Valid
    public int minAddToMakeValid(String S) {
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); ++i) {
            bal += S.charAt(i) == '(' ? 1 : -1;
            // It is guaranteed bal >= -1
            if (bal == -1) {
                ans++;
                bal++;
            }
        }

        return ans + bal;
    }


}
