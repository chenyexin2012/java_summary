package com.holmes.regex;

public class RegexMain {

    public static void main(String[] args) {

        // * 零次或多次匹配前面的字符或子表达式
        System.out.println("* 零次或多次匹配前面的字符或子表达式");
        System.out.println("ab".matches("abc*") == true);
        System.out.println("abcc".matches("abc*") == true);
        System.out.println("abccc".matches("abc*") == true);
        System.out.println("abcbc".matches("abc*") == false);
        System.out.println("abcbc".matches("a(bc)*") == true);

        // + 一次或多次匹配前面的字符或子表达式
        System.out.println("+ 一次或多次匹配前面的字符或子表达式");
        System.out.println("ab".matches("abc+") == false);
        System.out.println("abcc".matches("abc+") == true);
        System.out.println("abccc".matches("abc+") == true);
        System.out.println("abcbc".matches("abc+") == false);
        System.out.println("abcbc".matches("a(bc)+") == true);

        // ? 零次或一次匹配前面的字符或子表达式
        System.out.println("? 零次或一次匹配前面的字符或子表达式");
        System.out.println("ab".matches("abc?") == true);
        System.out.println("abc".matches("abc?") == true);
        System.out.println("abcc".matches("abc?") == false);
        System.out.println("abcbc".matches("abc?") == false);
        System.out.println("abc".matches("a(bc)?") == true);
        System.out.println("abcbc".matches("a(bc)?") == false);

        // {n} n是非负整数，表示匹配 n 次
        System.out.println("{n} n是非负整数，表示匹配n次");
        System.out.println("ab".matches("abc{1}") == false);
        System.out.println("abc".matches("abc{1}") == true);
        System.out.println("abc".matches("abc{2}") == false);
        System.out.println("abcc".matches("abc{2}") == true);
        System.out.println("abccc".matches("abc{2}") == false);
        System.out.println("abcbc".matches("a(bc){2}") == true);

        // {n,} n是非负整数，表示至少匹配n次
        System.out.println("{n,} n是非负整数，表示至少匹配n次");
        System.out.println("ab".matches("abc{1,}") == false);
        System.out.println("abc".matches("abc{1,}") == true);
        System.out.println("abcc".matches("abc{1,}") == true);
        System.out.println("abccc".matches("abc{2,}") == true);
        System.out.println("abcbc".matches("a(bc){2,}") == true);
        System.out.println("abcbcbc".matches("a(bc){2,}") == true);

        // {n,m} m和n是非负整数，其中n<=m，表示至少匹配n次，至多匹配m次
        System.out.println("{n,m} m和n是非负整数，其中n<=m，表示至少匹配n次，至多匹配m次");
        System.out.println("ab".matches("abc{1,3}") == false);
        System.out.println("abc".matches("abc{1,3}") == true);
        System.out.println("abcccc".matches("abc{1,3}") == false);
        System.out.println("abccc".matches("abc{2,3}") == true);
        System.out.println("abcbcbc".matches("a(bc){2,3}") == true);

        // ? 紧随任何其他限定符（*、+、?、{n}、{n,}、{n,m}）之后时，匹配模式是"非贪心的"。
        System.out.println();
        System.out.println("ab".matches("abc+?") == false);
        System.out.println("abc".matches("abc+?") == true);
        System.out.println("abcc".matches("abc+?") == true);
        System.out.println("abccc".matches("abc+?") == true);


    }
}
