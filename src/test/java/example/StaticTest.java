package example;

import org.junit.Test;

public class StaticTest {

    public static class StaticClass{
        public static int a = 0;
        public int b = 0;



        @Override
        public String toString() {
            return "StaticClass{" +
                    "b=" + b + ", a=" + a +
                    '}';
        }
    }

    @Test
    public void foo() {
        StaticClass firstEx = new StaticClass();
        StaticClass secondEx = new StaticClass();
        System.out.println(firstEx);
        System.out.println(secondEx);
        firstEx.a = 809;
        firstEx.b = 6732183;
        System.out.println(firstEx);
        System.out.println(secondEx);
    }

}
