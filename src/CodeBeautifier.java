// A class full of functions that are used as shortcuts
public class CodeBeautifier {
    public static void break_line(int width, String character) {
        // A cleaner way to write code, this function is used to print breaking lines.
        for (int i = 0; i < width; i++) {
            System.out.print(character);
        }
        System.out.println();
    }
}
