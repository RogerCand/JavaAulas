package java_avc;

public class Casting {
    public static void main(String[] args) {
        double d = 10.50;

        int i = (int) d;
        System.out.println(i);

        long n = 1000000;
        System.out.println(n);

        long n1 = 1_000_000_000_000L;
        System.out.println(n1);

        float f1 = 500;
        System.out.println(f1);

        long k = 10000000000000L;
        int i2 = (int) k;
        System.out.println(i2);
    }
}
