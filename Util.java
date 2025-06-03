public class Util {
    public static void mySleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    public static void println(String s) {
        System.out.println(s);
    }
}
