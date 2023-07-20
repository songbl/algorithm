package com.songbl;


/**
 *  两数相加
 * 题干：每个链表存储一组数字，合并起来，组成一个数，两个链表相加，第一个节点表示首位
 *
 *
 * 方法一：直接将链表存储的数字，转换成一个数字，再做相加
 *
 *
 *
 *
 * 方案二：直接对应位置相加
 *
 *
 *
 *
 *
 *
 *
 * 链表：一种物理存储上非连续、非顺序存储的数据结果。由两部分组成：指针+数据域
 * 大小没有限制、插入的复杂度是O(1),前提是知道前一个的索引
 *
 */

public class TwoSum {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(){}
        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);


        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(3);

        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(listNode.val);

        while (listNode.next!=null){
            System.out.println(listNode.val);
            listNode = listNode.next ;

        }

    }

    //暴力解法
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //取出第一个链表每一个节点上数字，并合并成数字
        long l1Value = 0; // 累加：把链表转成数字，注意次序为逆序
        int digit = 0; // 位数： 0代表个位， 1代表十位，以此递增
        while (l1 != null) { // 链表的下一个节点，若没有节点返回l1为null
            int pow = (int) Math.pow(10, digit); // 该数字对应的位数，从0开始  ；底数,几次方
            // 链表第一位是最大位
            l1Value += (long)l1.val * pow; // 累加：在当前数值基础上增加新的一个高位
            digit++; // 位数加1
            l1 = l1.next; // 链表指向下一个节点
        }
        //取出第二个链表每一个节点上数字，并合并成数字
        long l2Value = 0; // 如法炮制
        int digit2 = 0;
        while (l2 != null) { // 链表的下一个节点，若没有节点返回l1为null
            int pow = (int) Math.pow(10, digit2); // 该数字对应的位数，从0开始  ；底数,几次方
            // 链表第一位是最大位
            l2Value += (long)l2.val * pow; // 累加：在当前数值基础上增加新的一个高位
            digit2++; // 位数加1
            l2 = l2.next; // 链表指向下一个节点
        }

        //将数字合并，并开始散到每个节点上去
        ListNode head = new ListNode(); // 创建一个新链表，头部为空节点
        ListNode cur = head;
        long sum = l1Value + l2Value; // 数字相加，主意越界问题
        if (sum == 0) {
            head = new ListNode(0);
            return head;
        }
        while (sum > 0) { // 数字再转成链表
            int val = (int)(sum % 10); //通过取余的方式，拿到最后一位。即：当前最低位（在队列的最右边）
            cur.next = new ListNode(val); // 创建新节点，插入链表尾部（插入到了下一个节点，说明是往右插入）
            cur = cur.next; // 链表尾部指针移动
            sum = sum / 10; // 移除最低位（最低位已经处理完成）
        }
        return head.next;
    }



    //数学思维
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2; // 原链表的两个遍历指针
        ListNode resultHead = new ListNode(-1); // 结果链表的头结点head
        ListNode curr = resultHead; // 结果链表的遍历指针，代表当前操作的节点
        int carry = 0; // 进位
        // 1.遍历两个链表
        while (p != null || q != null) { // 以较长的链表为准
        // 获取当前节点值：链表较短，已无节点，取0
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            // 2.对应位置的节点数值相加；
            // 本次位置上相加，加上上一次的进位
            int sum = carry + x + y;
            // 如何得到进位：和对10求整，得到本次计算的进位。如果不大于10的话，变成0了
            carry = sum / 10;
            int num = sum % 10; // 存放到新链表节点中的数值
            // 3.将计算结果插入新链表尾部
            curr.next = new ListNode(num); // 创建新节点，追加到结果链表的尾部
            curr = curr.next; // 结果链表的当前节点向后移动
          // 循环的迭代部分：原链表的两个遍历指针
            p = p == null ? p : p.next;
            q = q == null ? q : q.next;
        }
        //最后一次了，还进位了，再新增一个节点
        if (carry > 0) { // 处理进位节点
            curr.next = new ListNode(carry);
        }
        return resultHead.next;
    }






}
