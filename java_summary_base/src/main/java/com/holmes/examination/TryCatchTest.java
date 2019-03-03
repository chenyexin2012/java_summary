package com.holmes.examination;

/**
 * @author Administrator
 */
public class TryCatchTest {

    public static void main(String[] args) {
        System.out.println(getNum1());
        System.out.println(getNum2());
        System.out.println(getNum3());
        System.out.println(getNum4());
        System.out.println(getNum5());
    }

    public static int getNum1() {

        int num = 0;
        try {
            return num;
        } catch (Exception e) {
            num = 1;
            return num;
        } finally {
            num = 2;
        }
    }

    public static int getNum2() {

        int num = 0;
        try {
        } catch (Exception e) {
            num = 1;
            return num;
        } finally {
            num = 2;
        }
        return num;
    }

    public static int getNum3() {

        int num = 0;
        try {
            return num;
        } catch (Exception e) {
            num = 1;
            return num;
        } finally {
            num = 2;
            return num;
        }
    }

    public static int getNum4() {

        int num = 0;
        try {
            int i = 1 / 0;
            return num;
        } catch (Exception e) {
            num = 1;
            return num;
        } finally {
            num = 2;
        }
    }

    public static int getNum5() {

        int num = 0;
        try {
            int i = 1 / 0;
            return num;
        } catch (Exception e) {
            num = 1;
            return num;
        } finally {
            num = 2;
            return num;
        }
    }

}
