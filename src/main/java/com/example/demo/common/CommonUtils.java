package com.example.demo.common;


import com.example.demo.common.algorithm.MergeSortUtilsImpl;
import org.apache.shiro.authc.UnknownAccountException;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Package： com.example.demo.utils
 * Author:  hujin
 * Date: 2019/8/8 16:39
 * Description:
 * Version：
 */
public class CommonUtils {


    /**
     * 牛客网： 二维数组查找
     * 在一个二维数组中（每个一维数组的长度相同），
     * 每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param target
     * @param array
     * @return time: 159ms
     * size: 17316k
     */
    public static boolean Find(int target, int[][] array) {
        int leny = array[0].length;
        int lenx = array.length;
        if (lenx == 0 || leny == 0) return false;
        for (int i = lenx - 1; i >= 0; ) {
            if (array[i][leny - 1] < target) {
                return false;
            } else if (array[i][leny - 1] == target) {
                return true;
            } else {
                for (int j = leny - 2; j >= 0; ) {
                    if (array[i][j] > target) {
                        j--;
                        continue;
                    } else if (array[i][j] == target) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
            i--;
        }
        return false;
    }

    /**
     * title 替换空格
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     *
     * @param str
     * @return time: 17ms time:  18ms
     * size: 9564k  size: 9440k
     */
    public String replaceSpace(StringBuffer str) {
//        String s = str.toString();
//        return s.replace(" ", "%20");
        int i = str.indexOf(" ");
        while (i > -1) {
            str.replace(i, 1 + 1, "%20");
            i = str.indexOf(" ");
        }
        return str.toString();
    }

    /**
     * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList
     */
    private static ArrayList<Integer> result = new ArrayList<Integer>();
    /*public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(result.size() != 0) result = new ArrayList<Integer>();
        if (listNode != null){
            if(listNode.next != null){
                printListFromTailToHead(listNode.next);
            }
            result.add(listNode.val);
        }
        return result;
    }*/

//    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
//        if (pre.length == 0 || in.length == 0) {
//            return null;
//        }
//        return getNode(pre, 0, pre.length, in, 0, in.length);
//    }

//    private TreeNode getNode(int[] preOrder, int ps, int pe, int[] inOrder, int is, int ie) {
//        if (pe < ps) return null;
//
//        int value = preOrder[ps];
//        int index = is;
//        while (index <= ie && value != inOrder[index]) {
//            index++;
//        }
//        TreeNode node = new TreeNode(value);
//        node.val = value;
//        node.left = getNode(preOrder, ps + 1, ps + index - is, inOrder, is, index - 1);
//        node.right = getNode(preOrder, ps + index - is + 1, pe, inOrder, index + 1, ie);
//        return node;
//    }

    /**
     * 2个栈表示队列
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack1.empty()) return -1;
        while (stack1.size() > 1) {
            stack2.push(stack1.pop());
        }
        int result = stack1.pop();
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        return result;
    }

    public Integer pop2() {
        if (stack1.empty() && stack2.empty()) return null;
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        } else {
            return stack2.pop();
        }
    }


    /**
     * 延伸  2个队列表示栈
     */

    Queue<Integer> queue1 = new ArrayDeque();
    Queue<Integer> queue2 = new ArrayDeque();

    public void queuePush(int node) {
        queue1.offer(node);
    }

    public int queuePop() {
        if (queue1.isEmpty() && queue2.isEmpty()) return -1;
        int result = -1;
        if (queue1.isEmpty()) {
            while (queue2.size() > 1) {
                queue1.offer(queue2.poll());
            }
            return queue2.poll();
        } else {
            while (queue1.size() > 1) {
                queue2.offer(queue1.poll());
            }
            return queue1.poll();
        }
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     *
     * @param array
     * @return 单向遍历
     */
    public int minNumberInRotateArray(int[] array) {
        int len = array.length;
        if (len == 0) return 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) return array[i + 1];
        }
        return array[0];
    }

    // 二分法
    public int minNumberInRotateArray2(int[] array) {
        int left = 0;
        int right = array.length - 1;
        if (right == 0) return 0;
        int result = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] > array[right]) left = mid + 1;
            else if (array[mid] == array[right]) {
                right--;
                left++;
            } else right = mid;
        }
        return array[left];
    }

    /**
     * 大家都知道斐波那契数列，现在要求输入一个整数n，
     * 请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     * n<=39
     *
     * @param n
     * @return 递归
     */
    public int Fibonacci(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else if (n <= 39) {
            return Fibonacci(n - 2) + Fibonacci(n - 1);
        }
        return 0;
    }

    /**
     * 迭代
     *
     * @param n
     * @return
     */
    public int Fibonacci1(int n) {
        int result = 0;
        int num0 = 0;
        int num1 = 1;
        for (int i = 1; i < n; i++) {
            result = num0 + num1;
            num0 = num1;
            num1 = result;
        }
        return n == 1 ? num1 : result;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     *
     * @param target
     * @return
     */
    public int JumpFloor(int target) {
        int result = 1;
        int num0 = 1;
        int num1 = 2;
        for (int i = 2; i < target; i++) {
            result = num0 + num1;
            num0 = num1;
            num1 = result;
        }
        return target == 2 ? num1 : result;
    }

    /**
     *
     * 变态跳台阶
     *一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * @param target
     * @return
     *
     * 思路 :
     * ① f(1) = 1
     * ② f(2) = 2   两次1 或者一次2
     * ③ f(3) = f(2) + f(1) + f(0)  第一次1 剩下2阶  或者跳2 剩下一阶
     *
     * 推导; f(n) = f(n-1) + f(n-2) +...+ f(1) + f(0)  第一次分别有1到n种跳法
     *  则 f(n-1) = f(n-2) + .... f(0)
     *  得到 f(n) = f(n-1) + f(n-1)
     *  即 得到 f (n) = 2(n-1)次方 f(1-1)
     *
     */
    public int JumpFloorII(int target) {
        return 1 << (target - 1);
    }

    /**
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
     * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     * @param target
     * @return
     * 思路:
     * ①  f(1) = 1
     * ②  f(2) = 2
     * ③  f(3) = f(2) + f (1)  第一个横着放 或者 前两个竖着放
     * 推导：
     *  f(n) = f(n-1) + f(n -1)
     *  即斐波那契
     */
    public int RectCover(int target) {
        if (target == 0) return 0;
        else if (target ==1) return 1;
        return JumpFloor(target);
    }

    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     * @param n
     * @return
     */
    public static int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            ++count;
            n = (n - 1) & n;
        }
        return count;
    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。
     * 求base的exponent次方。
     * @param base
     * @param exponent
     * @return
     */
    public double Power(double base, int exponent) throws Exception {
        double result = 1,curr = base;
        if (exponent < 0) {
            if (base == 0) throw new Exception("can't be zero");
            exponent = -exponent;
        }
        if(exponent==0){
            return 1;
        }
        while(exponent!=0){
            if((exponent&1)==1){
                result*=curr;
            }
            curr*=curr;
            exponent=exponent >> 1;
        }
        return exponent > 0? result :(1/result);
    }


/*   // @Test
    public void test () throws ParseException {
        // Date date = new Date(Date.parse("2019-07-08 00:00:00"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHssmm");
        Calendar calendar = Calendar.getInstance();
        String time = "20190708000000";
        calendar.setTime(sdf.parse(time));
        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
    }*/


    /**
     * title: 调整数组顺序使奇数位于偶数前面
     * 输入一个整数数组，
     * 实现一个函数来调整该数组中数字的顺序，
     * 使得所有的奇数位于数组的前半部分，
     * 所有的偶数位于数组的后半部分，
     * 并保证奇数和奇数，
     * 偶数和偶数之间的相对位置不变。
     * @param array
     * 思路：index记为当前奇数位置,遍历数组,
     * 遇到奇数插入到index位置。index向后移一位。
     */
    public void reOrderArray(int [] array) {
        int index = 0;
        for (int i=0;i<array.length;i++){
            int len  = array.length -1;
            int temp = array[i];
            if (temp%2 == 1) {
                for (int j = i -1;j >=index;j --) {
                    array[j+1] = array[j];
                }
                array[index] = temp;
                index++;
            }
        }
    }


    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * title: 链表中倒数第k个结点
     * 输入一个链表，输出该链表中倒数第k个结点。
     * @param head
     * @param k
     * @return
     *
     * 思路1： 两次遍历链表
     *   第一次遍历1到n 拿到链表长度n
     *   第二次遍历到 n-k 拿到倒数第n个
     *
     * 思路2： 一次遍历链表
     *  遍历1到k时取另一个变量从head还是获取next节点
     *  遍历到n时，另一个变量到n-k位置
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        ListNode temp = head;
        int n = 0;
        while (temp !=null){
            temp = temp.next;
            n++;
        }
        if (k == 0 || k > n) return null;
        int i = 0;
        temp = head;
        while (i<n-k) {
            temp = temp.next;
            i ++;
        }
        return temp;
    }

    public ListNode FindKthToTail1(ListNode head,int k) {
        int i = 0;
        int j = 0;
        ListNode result = null;
        ListNode temp = head;
        if (k == 0) return null;
        while(temp!=null) {
            if (i == k -1) {
                result = head;
            }
            if (i > k -1) {
                result = result.next;
            }
            temp = temp.next;
            i++;
        }
        return result;
    }

    /**
     * title: 反转链表
     * 输入一个链表，反转链表后，
     * 输出新链表的表头。
     * @param head
     * @return
     *
     * 思路：遍历链表,每个节点的next分别指向前一个元素
     */
    public ListNode ReverseList(ListNode head) {
        ListNode  temp = head;
        ListNode result = null;
        ListNode listNode = null;
        while (temp !=null){
            ListNode pNext = temp.next;
            if(pNext == null)result = temp;
            temp.next = listNode;
            listNode = temp;
            temp = pNext;
        }
        return  result;
    }

    /**
     * title: 合并两个排序的链表
     * 输入两个单调递增的链表，
     * 输出两个链表合成后的链表，
     * 当然我们需要合成后的链表满足单调不减规则。
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode head = null;
        ListNode p = null;
        if (list1 == null) return  list2;
        if (list2 == null) return  list1;
        while (list1!=null && list2!=null) {
            if (list1.val < list2.val) {
                if (p == null) p = list1;
                else p.next = list1;
                list1 = list1.next;
            } else {
                if (p == null)p = list2;
                else p.next = list2;
                list2 = list2.next;
            }
            if (head != null) {
                p = p.next;
            }
            else head = p;
        }
        if (list1 == null) {
            if (p == null)p = list2;
            else p.next = list2;
        }
        if (list2 == null) {
            if (p == null) p = list1;
            else p.next = list1;
        }
        return head;
    }


    /**
     * 二叉树
     */
    private class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }


    /**
     * 输入两棵二叉树A，B，
     * 判断B是不是A的子结构。
     * （ps：我们约定空树不是任意一个树的子结构）
     * @param root1
     * @param root2
     * @return
     *
     * 思路： 首先找跟节点相等的点，
     * 然后遍历其下面所有左右节点是否相等。
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root2 == null) return false;
        boolean result = false;
        if (root1 != null) {
            if (root1.val == root2.val){
                result = checkTree(root1,root2);
            }
            if (result == false) result = HasSubtree(root1.left,root2);
            if (result == false) result = HasSubtree(root1.right,root2);
        }
        return result;
    }
    private boolean checkTree (TreeNode root1 , TreeNode root2){
        if (root1 != null && root2 !=null) {
            if (root1.val == root2.val){
                return checkTree(root1.left,root2.left) && checkTree(root1.right,root2.right);
            }else {
                return false;
            }
        }
        if(root1 == null && root2 != null)return false;
        return  true;
    }


    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     * @param root
     * 递归左右子节点交换
     *
     */
    public void Mirror(TreeNode root) {
        TreeNode temp = null;
        if (root!=null) {
            temp = root.left;
            root.left = root.right;
            root.right = temp;
            Mirror(root.left);
            Mirror(root.right);
        }
    }

    /**
     *
     * @param root
     * 非递归 使用stack 遍历每个节点入栈,
     */
    public void Mirror1 (TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode temp = null;
        while (stack.size() > 0){
            TreeNode node = stack.pop();
            temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
    }

    /**
     * 输入一个矩阵，
     * 按照从外向里以顺时针的顺序依次打印出每一个数字，
     * 例如，如果输入如下4 X 4矩阵：
     * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
     * 则依次打印出数字
     * 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if (matrix == null) return null;
        int min = 0;
        int max = matrix.length + matrix[0].length -2;
        ArrayList<Integer> result = new ArrayList<Integer>();
        int x = 0;
        int y = 0;
        boolean flag = false;
        while(x+y <= max && min <= max){
            result.add(matrix[x][y]);
            if (flag) {
                while (min <= x + y) {
                    if(y>min/2)y--;
                    else x--;
                    if (x+y == min) {
                        flag = false;
                        max -=2;
                        min +=2;
                    }
                }
            }else {
                if (y < max/2)y++;
                else  x++;
            }
            if (x+y == max) {
                flag = true;
            }
        }
        //todo
        return result;
    }

    /**
     * 定义栈的数据结构，
     * 请在该类型中实现一个能够得到栈中所含最小元素的min函数
     * （时间复杂度应为O（1））。
     * @param node
     */
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minstack = new Stack<Integer>();
    public void push1(int node) {
        if (minstack.isEmpty() || minstack.peek() > node){
            minstack.push(node);
        }
        stack.push(node);
    }

    public void pop1() {
        if (stack.peek() == minstack.peek())minstack.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minstack.peek();
    }


    /**
     * 输入两个整数序列，
     * 第一个序列表示栈的压入顺序，
     * 请判断第二个序列是否可能为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。
     * 例如序列1,2,3,4,5是某栈的压入顺序，
     * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
     * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。
     * （注意：这两个序列的长度是相等的)
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        if(pushA.length != popA.length) return false;
        int index = 0;
        for(int i =0;i<pushA.length;i++){
            if(pushA[i] != popA[index]){
                stack.push(pushA[i]);
            } else {
                index++;
            }
            while(!stack.isEmpty()){
                if(popA[index] == stack.peek()){
                    stack.pop();
                    index++;
                }else{
                    break;
                }
            }
        }
        if (stack.isEmpty())return true;
        else return false;
    }


    /**
     * 从上往下打印出二叉树的每个节点，
     * 同层节点从左至右打印。
     * @param root
     * @return
     *
     * 思路: 利用队列的特性,遍历树将树的子节点都加入到队列中.
     * ArrayList模拟队列
     */

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> rootNode = new ArrayList<Integer>();
        ArrayList<TreeNode> queue = new ArrayList<>();
        if(root==null)return rootNode;
        queue.add(root);
        while(queue.size()!= 0){
            TreeNode p = queue.remove(0);
            if (p.left!=null) queue.add(p.left);
            if(p.right!=null) queue.add(p.right);
            rootNode.add(p.val);
        }
        return rootNode;
    }

    /**
     * 输入一个整数数组，
     * 判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则输出Yes,否则输出No。
     * 假设输入的数组的任意两个数字都互不相同
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length == 0) return false;
        if (sequence.length == 1)return true;
        return IsOrder(sequence,0,sequence.length-1);
    }
    private boolean IsOrder(int[] sequence,int left,int right){
        int mid = sequence[right];
        if(left>=right) return true;
        int index = left;
        while(sequence[index]<mid &&index<right){
            index ++;
        }
        int  k = index;
        while (k <right ){
            if(sequence[k]<mid)return false;
            k++;
        }
        return IsOrder(sequence,left,index == left ? index : index-1)&&IsOrder(sequence,index,right-1);
    }

    /**
     * 输入一个字符串,
     * 按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,
     * 则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba
     * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        if (str ==null || str.length() == 0) return null;
        ArrayList<String> result = new ArrayList<String>();
        char[] charArray = str.toCharArray();
        Set<Character>  chars = new HashSet<>();
        for (int i = 0; i < charArray.length; i++) {
            chars.add(charArray[i]);
        }
        List<Character> characters= new ArrayList<>(chars);
        add(characters);
        return result;
    }
    private ArrayList<String> add(List<Character> str){
        for (int i = 0;i<str.size();i++){
            String stringBuilder = str.get(i).toString();
            str.remove(i);
            stringBuilder += add(str);
        }
        // todo
        return null;
    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，
     * 请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
     * 由于数字2在数组中出现了5次，
     * 超过数组长度的一半，
     * 因此输出2。
     * 如果不存在则输出0。
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        int len = array.length;
        int index = 0;
        for (int i=0;i<array.length;i++){
            for(int j = i ;j<len;j++){
                if(array[i] == array[j]) index++;
            }
            if(index > len/2) return  array[i];
            else  index = 0;
        }
        return index;
    }

    /**
     * 输入n个整数，
     * 找出其中最小的K个数。
     * 例如输入4,5,1,6,2,7,3,8这8个数字，
     * 则最小的4个数字是1,2,3,4,
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        if(k > input.length) return result;
        for (int i = 0;i< k;i++){
            int index = -1;
            for(int j = 0; j<input.length;j++){
                if((index == -1 || input[index]> input[j])&&(indexList.size()==0 || indexList.indexOf(j)==-1))index = j;
            }
            if (index != -1) {
                result.add(input[index]);
                indexList.add(index);
            }
        }
        return  result;
    }


    /**
     * 给一个数组，返回它的最大连续子序列的和
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int len =  array.length;
        if(len == 0) return 0;
        int max = array[0];
        int sum = 0;
        for (int i = 0;i<len;i++){
            sum = 0;
            for(int j = i;j<len;j++){
                sum += array[j];
                if(sum > max) max = sum;
            }
        }
        return max;
    }


    /**
     * 求出任意非负整数区间中1出现的次数
     * （从1 到 n 中1出现的次数）
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        if(n <= 0)
            return 0;
        int count = 0;
        for(long i = 1; i <= n; i *= 10){
            long diviver = i * 10;
            count += (n / diviver) * i + Math.min(Math.max(n % diviver - i + 1, 0), i);
        }
        return count;
    }


    /**
     * 输入一个正整数数组，
     * 把数组里所有数字拼接起来排成一个数，
     * 打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     * @param numbers
     * @return
     */
    public String PrintMinNumber(int [] numbers) {
        String result = "";
        if (numbers.length == 0) return null;
        if (numbers.length == 1) return  numbers[0] +"";
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList()); ;
        Collections.sort(list,(a,b)->(a+""+b).compareTo(b+""+a));
        for (int i=0;i<list.size();i++){
            result+=list.get(i);
        }
        return  result;
    }


    /**
     * 在一个字符串
     * (0<=字符串长度<=10000，全部由字母组成)
     * 中找到第一个只出现一次的字符,
     * 并返回它的位置,
     * 如果没有则返回 -1
     * （需要区分大小写）
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str) {
        int i = 0;
        while(i<str.length() -1){
            if(str.lastIndexOf(str.substring(i,i+1)) == i && str.indexOf(str.substring(i,i+1)) == i){return i;}
            i++;
        }
        return -1;
    }

    /**
     * 在数组中的两个数字，
     * 如果前面一个数字大于后面的数字，
     * 则这两个数字组成一个逆序对。
     * 输入一个数组,求出这个数组中的逆序对的总数P。
     * 并将P对1000000007取模的结果输出。
     * 即输出P%1000000007
     * @param array
     * @return  todo
     */
    public int InversePairs(int [] array) {
        int p = 0;
        int min = array.length > 0 ? array[0] : 0;
        int max = 0;
        for (int i =1;i<array.length;i++){
            if(min > array[i]) {
                p+= i;
                min = array[i];
            }
            else if (max < array[i]){max = array[i];}
            else {
                for (int j = 0; j <i;j++){
                    if(array[i] < array[j])p++;
                }
            }
        }
        return p%1000000007;
    }

    /**
     * 找出所有和为S的连续正数序列
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer> > result = new ArrayList<>();
        int plow = 1,phigh = 2;
        while(phigh > plow){
            int cur = (phigh + plow) * (phigh - plow + 1) / 2;
            if(cur == sum){
                ArrayList<Integer> list = new ArrayList<>();
                for(int i=plow;i<=phigh;i++){
                    list.add(i);
                }
                result.add(list);
                plow++;
            }else if(cur < sum){
                phigh++;
            }else{
                plow++;
            }
        }
        return result;
    }

    /**
     * 输入一个递增排序的数组和一个数字S，
     * 在数组中查找两个数，使得他们的和正好是S，
     * 如果有多对数字的和等于S，输出两个数的乘积最小的
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        int plow = 0,phigh = array.length - 1;
        while(plow<phigh){
            if(array[plow] + array[phigh] == sum) {
                result.add(array[plow]);
                result.add(array[phigh]);
                return result;
            }else if(array[plow] + array[phigh] > sum) {
                phigh--;
            }else {
                plow++;
            }
        }
        return result;
    }

    /**
     * 汇编语言中有一种移位指令叫做循环左移（ROL），
     * 现在有个简单的任务，
     * 就是用字符串模拟这个指令的运算结果。
     * 对于一个给定的字符序列S，
     * 请你把其循环左移K位后的序列输出。
     * 例如，字符序列S=”abcXYZdef”,
     * 要求输出循环左移3位后的结果，
     * 即“XYZdefabc”。是
     * 不是很简单？OK，搞定它！
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str,int n) {
        if(n>str.length())return "";
        return str.substring(n) + str.substring(0,n);
    }

    /**
     * 牛客最近来了一个新员工Fish，
     * 每天早晨总是会拿着一本英文杂志，
     * 写些句子在本子上。
     * 同事Cat对Fish写的内容颇感兴趣，
     * 有一天他向Fish借来翻看，
     * 但却读不懂它的意思。例
     * 如，“student. a am I”。
     * 后来才意识到，
     * 这家伙原来把句子单词的顺序翻转了，
     * 正确的句子应该是“I am a student.”。
     * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        if(str.length() == 0)return "";
        String[] result = str.split(" ");
        if(result.length == 0)return str;
        StringBuilder rs = new StringBuilder();
        for (int i =result.length -1 ;i>=0;i--){
            rs.append(result[i]).append(" ");
        }
        return rs.toString().substring(0,rs.toString().length()-1);
    }

    @Test
    public void testStudent(){
        System.out.println(ReverseSentence("It's a dog!"));
    }

    /**
     * LL今天心情特别好,
     * 因为他去买了一副扑克牌,
     * 发现里面居然有2个大王,
     * 2个小王(一副牌原本是54张^_^)
     * ...他随机从中抽出了5张牌,
     * 想测测自己的手气,
     * 看看能不能抽到顺子,
     * 如果抽到的话,
     * 他决定去买体育彩票,嘿嘿！！
     * “红心A,黑桃3,小王,大王,方片5”,
     * “Oh My God!”
     * 不是顺子.....
     * LL不高兴了,他想了想,
     * 决定大\小 王可以看成任何数字,
     * 并且A看作1,J为11,Q为12,K为13。
     * 上面的5张牌就可以变成
     * “1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
     * LL决定去买体育彩票啦。
     * 现在,要求你使用这幅牌模拟上面的过程,
     * 然后告诉我们LL的运气如何，
     * 如果牌能组成顺子就输出true，
     * 否则就输出false。
     * 为了方便起见,你可以认为大小王是0。
     * @param numbers
     * @return
     */
    public boolean isContinuous(int [] numbers) {
        if (numbers.length != 5) return false;
        int min = 14;
        int max = -1;
        int flag = 0;
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (number < 0 || number > 13) return false;
            if (number == 0) continue;
            if (((flag >> number) & 1) == 1) return false;
            flag |= (1 << number);
            if (number > max) max = number;
            if (number < min) min = number;
        }
        if (max - min >= 5) return false;
        return true;
    }


    /**
     * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
     * HF作为牛客的资深元老,自然也准备了一些小游戏。
     * 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
     * 然后,他随机指定一个数m,让编号为0的小朋友开始报数。
     * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,
     * 并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,
     * 可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
     * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
     * @param n
     * @param m
     * @return
     * 思路 递归
     * 反向思考
     */
    public int LastRemaining_Solution(int n, int m) {
        if(n == 0 ) return -1;
        if(n == 0) return 0;
        else {
            return LastRemaining_Solution(n-1,m)%n;
        }
    }

    /**
     * 求1+2+3+...+n，
     * 要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        int result = n;
        boolean a = result>0 && (result += Sum_Solution(n-1))>0;
        return  result;
    }
    /**
     * 获取输入字的质数集
     * 比如180  得到 2 2 3 3 5
     * @param n
     */
    private static int[] primeList = {};
    public  void getNum (int n) {
        if (n <= 1) {
            return;
        }
        int i = 2;
        while (i<=n) {
            int len =primeList.length;
            if(n%i == 0) {
                n = n/i;
                if(len == 0 || primeList[len-1] < i){
                    primeList[len] = i;
                }
            }else {
                if (len > 0 || primeList[len-1] > i){
                    for (int index = 0;index < len-1; index++) {
                        if(i == primeList[index]) {
                            i = primeList[index+1];
                        }
                    }
                }else {
                    i ++;
                }
            }
        }
    }


    @Test
    public void test(){
        int[] num= {1,4,7,23,6,8,4,5};
        List<Integer> list = Arrays.stream(num).boxed().collect(Collectors.toList());
        MergeSortUtilsImpl.mergeSort4(list);
        System.out.println(list);
    }


    public static void main1(String[] args){
        int a = 123;
        StringBuilder s = new StringBuilder();
        int i = numberOfLeadingZeros(a) - 1;
        while(i >= 0) {
            s.append(a>>i & 1);
            i--;
        }
        System.out.println(s);

    }

    public int findKthLargest(int[] nums, int k) {
        for (int i = 1 ;i< nums.length; i += i) {
            for (int j =0; i+j< nums.length; j+=2*i) {
                int left = j,mid = i + j,right = j+2*i;
                if (right > nums.length) right = nums.length;
                int[] lefts = new int[mid-left];
                int[] rights = new int[right-mid];
                System.arraycopy(nums, left, lefts, 0,mid-left);
                System.arraycopy(nums, mid, rights, 0,right-mid);

                int m =0,n=0;
                for (int index = left;index < right; index ++) {
                    if(m>= lefts.length){
                        nums[index] = rights[n++];
                        continue;
                    }
                    if (n>= rights.length) {
                        nums[index] = lefts[m++];
                        continue;
                    }
                    if (lefts[m] < rights[n]) {
                        nums[index] = lefts[m++];
                    } else {
                        nums[index] = rights[n++];
                    }
                }
            }
        }
        return nums[nums.length - k];
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    // 递归
    public int maxDepth(TreeNode root) {
        if (root != null) {
            return getDeep(root) +1;
        }
        return 0;
    }

    private int getDeep(TreeNode root){
        int left = 0,right=0;
        if (root.left != null) {
            left += maxDepth(root.left);
            left ++;
        }
        if (root.right != null) {
            right +=maxDepth(root.right);
            right ++;
        }
        return left < right? right :left;
    }

    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0) return;
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();
        for (int i = 0;i < matrix.length;i ++) {
            for(int j =0; j <matrix[i].length; j ++) {
                if (matrix[i][j] == 0) {
                    x.add(i);
                    y.add(j);
                }
            }
        }
        for (int i = 0;i < matrix.length;i ++) {
            for(int j =0; j <matrix[i].length; j ++) {
                if (x.contains(i) || y.contains(j)) matrix[i][j] = 0;
            }
        }
    }

    public int maxPoints(int[][] points) {
        if (points.length == 1) return 1;
        int count = 0;
        Map<String,Set<int[]>> map = new HashMap<>();
        for (int i=0;i< points.length;i++){
            for (int j = i + 1;j<points.length;j++) {
                double k=0,b=0;
                String kb = "";
                if ((points[i][0] - points[j][0]) == 0) {
                    kb = "x=" + points[i][0];
                } else {
                    k = (Double.valueOf(points[i][1] - points[j][1])) / (points[i][0] - points[j][0]);
                    b = points[j][1] - points[j][0] * k;
                    kb = "y=" + k + "x+" + b;
                }
                if (map.get(kb) != null) {
                    Set<int[]> set= map.get(kb);
                    set.add(points[i]);
                    set.add(points[j]);
                    map.put(kb, set);
                }else {
                    Set<int[]> set= new HashSet<>();
                    set.add(points[i]);
                    set.add(points[j]);
                    map.put(kb,set);
                }
                count = count < map.get(kb).size() ? map.get(kb).size() : count;
            }
        }
        return count;
    }

    @Test
    public void tes () {
       int[][]  a =  {{560,248},{0,16},{30,250},{950,187},{630,277},{950,187},{-212,-268},{-287,-222},{53,37},{-280,-100},{-1,-14},{-5,4},{-35,-387},{-95,11},{-70,-13},{-700,-274},{-95,11},{-2,-33},{3,62},{-4,-47},{106,98},{-7,-65},{-8,-71},{-8,-147},{5,5},{-5,-90},{-420,-158},{-420,-158},{-350,-129},{-475,-53},{-4,-47},{-380,-37},{0,-24},{35,299},{-8,-71},{-2,-6},{8,25},{6,13},{-106,-146},{53,37},{-7,-128},{-5,-1},{-318,-390},{-15,-191},{-665,-85},{318,342},{7,138},{-570,-69},{-9,-4},{0,-9},{1,-7},{-51,23},{4,1},{-7,5},{-280,-100},{700,306},{0,-23},{-7,-4},{-246,-184},{350,161},{-424,-512},{35,299},{0,-24},{-140,-42},{-760,-101},{-9,-9},{140,74},{-285,-21},{-350,-129},{-6,9},{-630,-245},{700,306},{1,-17},{0,16},{-70,-13},{1,24},{-328,-260},{-34,26},{7,-5},{-371,-451},{-570,-69},{0,27},{-7,-65},{-9,-166},{-475,-53},{-68,20},{210,103},{700,306},{7,-6},{-3,-52},{-106,-146},{560,248},{10,6},{6,119},{0,2},{-41,6},{7,19},{30,250}};
       System.out.println(maxPoints(a));
    }

    public static int numberOfLeadingZeros(int i) {
        // HD, Figure 5-6
//        if (i == 0)
//            return 32;
//        int n = 1;
//        if (i >>> 16 == 0) { n += 16; i <<= 16; }
//        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
//        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
//        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
//        if (i >>> 31 == 0) { n +=  1; i <<=  1; }
//        n -= i >>> 31;
//        return n;
        if(i == 0) return 0;
        int n = 1;
        if(i >>> 16 != 0) {n += 16; i >>= 16;}
        if(i >>> 8 != 0) {n += 8; i >>= 8;}
        if(i >>> 4 != 0) {n += 4; i >>= 4;}
        if(i >>> 2 != 0) {n += 2; i >>= 2;}
        return n + (i >>> 1);

    }

    /**
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        boolean isMatch  = false;
        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(p)) return isMatch;


        return isMatch;

    }


    //测试类
    @Test
    public void testa() {
        System.out.println(getLongStr(" 1 3 4"));
    }
    public String getLongStr (String str) {
        char[] chars = str.toCharArray();
        String result = "";
        String data = "";
        Map<Character, Integer> map = new HashMap<>();

        if (str.length() == 1) return str;
        for (int i = 1; i < str.length(); i++) {
            map.put(chars[i],i);
            int index = result.indexOf(chars[i]);
            if (result.equals("")) result += chars[0];
            if (map.containsKey(chars[i]))
                result += chars[i];
            else {
                data = data.length() < result.length() ? result: data;
                result = result.substring(map.get(chars[i]) + 1) + chars[i];
            }
        }
        return data;
    }


    private static BigDecimal H = new BigDecimal(0); // 实时总金额

    private static int n = 0;  // 剩余人数

    private static BigDecimal count =new  BigDecimal(0);

    private static  BigDecimal  m =new  BigDecimal(0);  // 平均金额

    private static BigDecimal sum = new  BigDecimal(0); // 统计偏移量

    // 获取红包金额
    public BigDecimal getMoney(BigDecimal p) {
        if (H.intValue() < 0 || n <= 0) {
            throw new UnknownAccountException("红包已经抢光了！");
        }
        if (m.intValue() == 0) m = new BigDecimal(H.doubleValue() / n).setScale(2, BigDecimal.ROUND_HALF_UP); // 平均金额
        BigDecimal random;
        BigDecimal max = sum.intValue() <=0 ?  p: p.subtract(sum);
        BigDecimal min = sum.intValue() <=0 ? new BigDecimal("0").subtract(p).subtract(sum) : new BigDecimal("0").subtract(p);
        if (n == 1) random = new BigDecimal("0").subtract(sum);
        else random = new BigDecimal(Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(max.subtract(min)).add(min);
        sum  = sum.add(random);
        BigDecimal data = m.multiply(random).add(m).setScale(2, BigDecimal.ROUND_HALF_UP);  // 计算红包金额
        H  = H.subtract(data); // 扣除总金额
        n--;  // 人数减一
        count = count.add(data);
        return data;
    }

    //测试类
    @Test
    public void testGetMoney () {

        BigDecimal a  = new BigDecimal(100);
        int b = 3;
        H = a;
        n = b;
        for (int i = 0;i<b;i++){
            System.out.println(this.getMoney(new BigDecimal("0.1")));
        }
        System.out.println(count);
    }



    //测试类
    @Test
    public void testD () {
     C c = new C();
    }

    static{
        int x=5;
    }
    static int x,y;
    public static void main(String args[]){
        x--;
        myMethod( );
        System.out.println(x+y+ ++x);
    }
    public static void myMethod( ){
        y=x++ + ++x;
    }


    /**
     * 有1分，2分，5分，10分四种硬币，每种硬币数量无限，给定n分钱(n <= 100000)，
     * 有多少中组合可以组成n分钱？
     * 1  1
     * 2  2
     * 3  2
     * 4  3
     * 5  5
     */
    static int countx = 0;
    public  void solute (int[] coin, int index,  int n){
        if (n == 0) {
            countx++;
        }
        if (n < 0) return;
        for (int i =index ;i<4;i++){
            solute(coin, i, n - coin[i]);
        }
    }

    //测试类
    @Test
    public void test1D () {
        int[]  coin= {1,2,5,10};
        int tag = 0;
        int n = 13;
        solute(coin,0,n);
        System.out.println(countx);
    }


    @Test
    public void testTime () {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 99);
        System.out.println(sdf.format(calendar.getTime()));
    }



}


class C extends  B {
    private D d = new D("d1");
    private  D d2 = new D("d2");
    static {
        System.out.println("static : c");
    }
    public C (){
        System.out.println("c");
    }
}
class B{
    static {
        System.out.println("static : b");
    }
    public B () {
         System.out.println("b");
    }
}
class D{
    static {
        System.out.println("static : d");
    }
    String d;
    public D (String str) {
        d = str;
        System.out.println(d);
    }
}