import java.util.Scanner;

public class Main {

    public static void main(String[] a) {

        String str = new String();
        Scanner in = new Scanner(System.in);
        str = in.nextLine();
        while(str != null && !"".equals(str)) {
            char[] chars = str.toCharArray();
            for(int i = 0 ; i < chars.length >> 1; i++) {
                char c = chars[i];
                chars[i] = chars[chars.length - 1 - i];
                chars[chars.length - 1 - i] = c;
            }
            System.out.println(new String(chars));
            str = in.nextLine();
        }
    }
}
