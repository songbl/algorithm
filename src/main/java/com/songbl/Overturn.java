package com.songbl;

/**
 * 实现整数的数字翻转
 * <p>
 * 数据结构：
 * 数组：地址是连续的，可以通过索引拿到数据
 * <p>
 * <p>
 * 方案一：暴力解法
 * 1. 整数转字符串、字符串再转数组
 * 2. 反向遍历字符数组，并将元素存储到新数组中
 * 3. 将新的数组再转回来
 * <p>
 * <p>
 * 方案二：
 * 直接首和尾 交换
 *
 *
 * 方案三：
 *  数学思维
 */

public class Overturn {


    public static void main(String[] args) {

        int min = -2147483647;
        //暴力解法1
        int reverse1 = reverse1(Integer.MIN_VALUE);
        //暴力解法2
        int reverse2 = reverse2(Integer.MIN_VALUE);
        System.out.println("方案二翻转后的大小：" + reverse2);

        int reverse3 = reverse2(-256);
        System.out.println("方案三翻转后的大小：" + reverse3);

    }

    //暴力解法
    public static int reverse1(int x) {
        //1. 最大溢出问题，乘以-1 之后，就超过最大值的界限了
        if (x == Integer.MIN_VALUE) {
            // 整数类型最小值的绝对值 比 最大值的绝对值 大1
            return 0; // 反转必然溢出，返回0

        }
        int sign = x > 0 ? 1 : -1; // 符号
        x = x < 0 ? x * -1 : x; // 无论正负，都当成正数
        // 1.整数转字符串，再转字符数组
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        // 2.反向遍历字符数组，并将元素存储到新数组中
        int len = chars.length;
        //新数组
        char[] array = new char[len];
        for (int i = 0; i < len; i++) { // 遍历新数组
            array[i] = chars[len - 1 - i];
        }
        // 3.将新数组转成字符串，再转成整数输出（怕翻转之后，数值比int值大，所以用Long型接收）
        long value = Long.valueOf(String.valueOf(array));
//        boolean b = value > Integer.MAX_VALUE || value < Integer.MIN_VALUE;
        //不会出现负值
        boolean b = value > Integer.MAX_VALUE;
        int result = b ? 0 : (int) value; // 数值越界：溢出则返回0
        return result * sign; // 符号还原
    }


    // 首位   和 末尾 交换，索引变化，一增 一减少
    public static int reverse2(int x) {
        if (x == Integer.MIN_VALUE) {
            // 整数类型最小值的绝对值 比 最大值的绝对值 大1
            return 0; // 反转必然溢出，返回0
        }
        int sign = x > 0 ? 1 : -1; // 符号
        x = x < 0 ? x * -1 : x; // 无论正负，都当成正数;最小值的话；乘以-1，放不下了
        // 1.整数转字符串，再转字符数组
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        // 2.交换首位(start)和末位(end)数字
        // 3.循环操作：依次交换第二(start++)和倒数第二个(end--)
        int start = 0, end = chars.length - 1;
        while (start < end) { // 反转完成的标志： start >= end
            // 交换两端等距离的元素
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        // 4.将原数组转成字符串，再转成整数输出
        long value = Long.valueOf(String.valueOf(chars));
        boolean b = value > Integer.MAX_VALUE || value < Integer.MIN_VALUE;
        int result = b ? 0 : (int) value;
        return result * sign;
    }


    // 使用取余算法；rev⋅10+digi

    public static int reverse3(int x) {
        int res = 0;
        while (x != 0) {
            //处理越界。
            // 粗矿的判断就是    Integer.MIN_VALUE <digs * 10<Integer.MAX_VALUE
            if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            //末尾数字
            int digs = x % 10;
            //算翻转后的一次循环的数字，再此基数
            // 上累加
            res = digs * 10 + digs;
            // 向前挪动一位
            x = x / 10;

        }
        return res;

    }
}


