package tinkoff_2020_07_18;

// Наименьшее двузначное число, у которого сумма цифр не меняется при умножении на 2,3,4,5

/**
 * Created by asavan on 13.07.2020.
 */
public class Numbers__100 {
    public static void main(String[] args) {
        int i = 0;
        for (i = 10; i < 99; ++i) {
            int res2 = sum(i*2);
            int res3 = sum(i*3);
            if (res2 == res3) {
                int res4 = sum(i*4);
                int res5 = sum(i*5);
                if (res4 == res5) {
                    if (res3 == res4) {
                        System.out.println(sum(i) + " " + res3 + " " + i);
                        break;
                    }
                }
            }
        }
        System.out.println(i);
    }

    public static int sum(int a) {
        return a/10+a%10;
    }
}
