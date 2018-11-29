package com.holmes.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMain {

    public static void main(String[] args) {

        // * 零次或多次匹配前面的字符或子表达式
        System.out.println("* 零次或多次匹配前面的字符或子表达式");
        System.out.println("ab".matches("abc*") == true);
        System.out.println("abcc".matches("abc*") == true);
        System.out.println("abccc".matches("abc*") == true);
        System.out.println("abcbc".matches("abc*") == false);
        System.out.println("abcbc".matches("a(bc)*") == true);
        System.out.println();

        // + 一次或多次匹配前面的字符或子表达式
        System.out.println("+ 一次或多次匹配前面的字符或子表达式");
        System.out.println("ab".matches("abc+") == false);
        System.out.println("abcc".matches("abc+") == true);
        System.out.println("abccc".matches("abc+") == true);
        System.out.println("abcbc".matches("abc+") == false);
        System.out.println("abcbc".matches("a(bc)+") == true);
        System.out.println();

        // ? 零次或一次匹配前面的字符或子表达式
        System.out.println("? 零次或一次匹配前面的字符或子表达式");
        System.out.println("ab".matches("abc?") == true);
        System.out.println("abc".matches("abc?") == true);
        System.out.println("abcc".matches("abc?") == false);
        System.out.println("abcbc".matches("abc?") == false);
        System.out.println("abc".matches("a(bc)?") == true);
        System.out.println("abcbc".matches("a(bc)?") == false);
        System.out.println();

        // {n} n是非负整数，表示匹配 n 次
        System.out.println("{n} n是非负整数，表示匹配n次");
        System.out.println("ab".matches("abc{1}") == false);
        System.out.println("abc".matches("abc{1}") == true);
        System.out.println("abc".matches("abc{2}") == false);
        System.out.println("abcc".matches("abc{2}") == true);
        System.out.println("abccc".matches("abc{2}") == false);
        System.out.println("abcbc".matches("a(bc){2}") == true);
        System.out.println();

        // {n,} n是非负整数，表示至少匹配n次
        System.out.println("{n,} n是非负整数，表示至少匹配n次");
        System.out.println("ab".matches("abc{1,}") == false);
        System.out.println("abc".matches("abc{1,}") == true);
        System.out.println("abcc".matches("abc{1,}") == true);
        System.out.println("abccc".matches("abc{2,}") == true);
        System.out.println("abcbc".matches("a(bc){2,}") == true);
        System.out.println("abcbcbc".matches("a(bc){2,}") == true);
        System.out.println();

        // {n,m} m和n是非负整数，其中n<=m，表示至少匹配n次，至多匹配m次
        System.out.println("{n,m} m和n是非负整数，其中n<=m，表示至少匹配n次，至多匹配m次");
        System.out.println("ab".matches("abc{1,3}") == false);
        System.out.println("abc".matches("abc{1,3}") == true);
        System.out.println("abcccc".matches("abc{1,3}") == false);
        System.out.println("abccc".matches("abc{2,3}") == true);
        System.out.println("abcbcbc".matches("a(bc){2,3}") == true);
        System.out.println();

        // ? 紧随任何其他限定符（*、+、?、{n}、{n,}、{n,m}）之后时，匹配模式是"非贪心的"。
        System.out.println("? 紧随任何其他限定符（*、+、?、{n}、{n,}、{n,m}）之后时，匹配模式是\"非贪心的\"");
        System.out.println("贪心模式");
        Pattern pattern = Pattern.compile("abc+");
        Matcher matcher = pattern.matcher("abccccccc");
        if(matcher.find()) {
            System.out.println(matcher.group().equals("abccccccc"));
        }
        System.out.println("非贪心模式");
        pattern = Pattern.compile("abc+?");
        matcher = pattern.matcher("abccccccc");
        if(matcher.find()) {
            System.out.println(matcher.group().equals("abc"));
        }
        System.out.println();

        // . 匹配除"\r\n"之外的任何单个字符
        System.out.println(". 匹配除\"\\r\\n\"之外的任何单个字符");
        System.out.println("abc".matches("...") == true);
        System.out.println("abcd".matches("...") == false);
        System.out.println("ab".matches("a.b") == false);
        System.out.println("abc".matches("a.c") == true);
        System.out.println("abcd".matches("a.c") == false);
        System.out.println();

        // x|y 匹配x或y
        System.out.println("x|y 匹配x或y");
        System.out.println("abc".matches("a|bbc") == false);
        System.out.println("abc".matches("a|bbc") == false);
        System.out.println("a".matches("a|bbc") == true);
        System.out.println("bbc".matches("a|bbc") == true);
        System.out.println("abc".matches("(a|b)bc") == true);
        System.out.println("bbc".matches("(a|b)bc") == true);
        System.out.println();

        // [xyz] 字符集，匹配包含的任一字符
        System.out.println("[xyz] 字符集，匹配包含的任一字符");
        System.out.println("aba".matches("ab[abc]") == true);
        System.out.println("abb".matches("ab[abc]") == true);
        System.out.println("abc".matches("ab[abc]") == true);
        System.out.println("abab".matches("ab[abc]") == false);

        // [^xyz] 反向字符集，匹配未包含的任何字符
        System.out.println("[^xyz] 反向字符集，匹配未包含的任何字符");
        System.out.println("abf".matches("ab[^abc]") == true);
        System.out.println("abfd".matches("ab[^abc]") == false);
        System.out.println("aba".matches("ab[^abc]") == false);
        System.out.println("abb".matches("ab[^abc]") == false);
        System.out.println("abc".matches("ab[^abc]") == false);
        System.out.println("abab".matches("ab[^abc]") == false);
        System.out.println();

        // [a-z] 字符范围，匹配指定范围内的任何字符
        System.out.println("[a-z] 字符范围，匹配指定范围内的任何字符");
        System.out.println("abf".matches("ab[a-c]") == false);
        System.out.println("aba".matches("ab[a-c]") == true);
        System.out.println("abb".matches("ab[a-c]") == true);
        System.out.println("abc".matches("ab[a-c]") == true);
        System.out.println("abfd".matches("ab[a-c]") == false);
        System.out.println();

        // [^a-z] 反向范围字符，匹配不在指定的范围内的任何字符
        System.out.println("[^a-z] 反向范围字符，匹配不在指定的范围内的任何字符");
        System.out.println("abf".matches("ab[^a-c]") == true);
        System.out.println("abfd".matches("ab[^a-c]") == false);
        System.out.println("aba".matches("ab[^a-c]") == false);
        System.out.println("abb".matches("ab[^a-c]") == false);
        System.out.println("abc".matches("ab[^a-c]") == false);
        System.out.println("abab".matches("ab[^a-c]") == false);
        System.out.println();

        // \d 数字字符匹配，相当于[0-9]
        System.out.println("\\d 数据匹配，相当于[0-9]");
        System.out.println("ab1".matches("ab\\d") == true);
        System.out.println("abc".matches("ab\\d") == false);
        System.out.println("1348723947249723".matches("\\d+") == true);
        System.out.println();

        // \D 非数字字符匹配，相当于[0-9]
        System.out.println("\\D 数据匹配，相当于[^0-9]");
        System.out.println("ab1".matches("ab\\D") == false);
        System.out.println("abc".matches("ab\\D") == true);
        System.out.println("1348723947249723".matches("\\D+") == false);
        System.out.println("sadfasgafdg".matches("\\D+") == true);
        System.out.println("sadfasgafdg\n".matches("\\D+") == true);
        System.out.println();

        //
    }
}
