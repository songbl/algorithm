package com.songbl;

import java.util.ArrayList;
import java.util.List;

/**
 * 原语括号
 *  删除掉原语括号
 *
 */

public class DeleteBracket {


    public static void main(String[] args) {
        String s = "(()())(())(()(()))";

        String s1 = removeOuterParentheses3(s);

        System.out.println(s1);

    }


    //暴力解法1
    public static String removeOuterParentheses1(String S) {
        int len = S.length();
        // 1.定义容器存储原语子串
        List<String> list = new ArrayList<String>();
        // 2.定义左括号、右括号计数器
        int left = 0, right = 0, lastOpr = 0;
        // 3.遍历字符串，读取到括号时对应计数器自增
        for (int i = 0; i < len; i++) {
            char c = S.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                right++;
            }
            // 4.检查是否到达某个原语结尾，截取原语子串添加到容器
            // 整个结果都放进去了，下面再截取  (()())(())(()(()))
            if (left == right) {
                //截取包含开始到end之前的
                list.add(S.substring(lastOpr, i + 1));
                //下一次截取开始的位置
                lastOpr = i + 1;
            }
        }
        // 5.遍历容器中的原语子串，删除最外层后合并成新串
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            // 最后一位的索引是长度-1 ；截取不包括最后一位，正好，去掉了)
            sb.append(s.substring(1, s.length() - 1));
        }
        return sb.toString();
    }


    // 暴力解法2
    public static String removeOuterParentheses2(String S) {
        int len = S.length();
        // 1.定义容器存储删除外层括号后的原语子串
        StringBuilder sb = new StringBuilder();
        // 2.定义左括号、右括号计数器
        int left = 0, right = 0, lastOpr = 0;
        // 3.遍历字符串，读取到括号时对应计数器自增
        for (int i = 0; i < len; i++) {
            char c = S.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                right++;
            }
            // 4.检查是否到达某个原语结尾，截取不包含最外层的原语子串添加到容器
            // 与上面最大的不同是  ===直接截取了====
            if (left == right) {
                sb.append(S.substring(++lastOpr, i));
                //下一次的位置，起点
                lastOpr = i + 1;
            }
        }
        return sb.toString();

    }




    // 优化解法：利用() ,类似栈的结构去做
    // 注意索引的++ -- ,先用后减，可能数组越界了
    public static String removeOuterParentheses3(String S) {
        StringBuilder result = new StringBuilder();
        // 1.直接用数组取代栈
        int index = -1; // 栈顶索引
        int len = S.length();
        char[] stack = new char[len];
        // 2.将原字符串转为数组进行遍历
        char[] s = S.toCharArray();
        for (int i = 0; i < len; i++) {
            char ch = s[i];
            if (ch == '(') {
            // 3.去掉截取子串的操作，将原语字符直接拼接
                if (index > -1) { // 此前有数据，
                    result.append(ch); // 因为前面有数据了，则当前字符必然是原语的一部分
                }
                stack[++index] = ch; // 遇到左括号，左括号入栈
            } else { // 说明是右括号
                stack[index--] = '\u0000'; // 遇到右括号，左括号出栈
                // 3.去掉截取子串的操作，将原语字符直接拼接
                if (index > -1) { // 右括号匹配之后不为0，
                    result.append(ch); // 则当前字符必然是原语的一部分
                }
            }
        }
        return result.toString();
    }


    // 说白了，上面的index 核心就是为了知道，下面有没有数据，要不要直接加入到sb中，可以用个count计数
    // 避免了数组越界了
    public static String removeOuterParentheses4(String S) {
        StringBuffer sb = new StringBuffer();
         // 1.定义计数器count，统计左右括号出现的次数
        int count = 0;
        int len = S.length();
        char[] s = S.toCharArray(); // 字符数组
        // 2.遍历字符串，根据读取数据进行计数
        for (int i = 0; i < len; i++) {
            char c = s[i];
            if (c == '(') {
        // 3.根据计数情况判断左右括号是否属于原语
                if (count > 0) { // 此前有数据，
                    sb.append(c); // 则当前必然是原语的一部分
                }
                count++; // 读取到左括号自增
            } else {
                count--; // 读取到右括号自减；如果用栈的思维要弹出来一个
            // 3.根据计数情况判断左右括号是否属于原语
                if (count > 0) { // 右括号之后不为0， count自减后仍有数据，；弹完之后，还有，说明，不是底，继续 进行
                    sb.append(c); // 则当前必然是原语的一部分
                }
            }
        }
        // 4.将原语拼接到缓冲区并返回
        return sb.toString();
    }

}
