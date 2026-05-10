package Model;

public class DebugPrinter {

    public static void printSectionStart(String title) {
        System.out.println();
        System.out.println("============== " + title + " ==============");
    }

    public static void printSectionEnd(String title) {
        System.out.println("============== " + title + " ==============");
    }

    public static void printLine(String message) {
        System.out.println(message);
    }

    public static void printHorizontalLine(String message) {
        System.out.print(message + " ");
    }

    public static void seperator() {
        System.out.println();
    }
}
