import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GenerateRandomTest {
    public static void main(String[] args) {
        Scanner myObj1 = new Scanner(System.in);
        System.out.println("Enter max page number (Integer): ");
        int max = myObj1.nextInt();

        Scanner myObj2 = new Scanner(System.in);
        System.out.println("Enter test size (Integer): ");
        int size = myObj2.nextInt();

        Random ran = new Random();

        String name = "test" + size + ".txt";
        int number = 0;

        try (PrintWriter file = new PrintWriter(
            new BufferedWriter(
                new FileWriter(name)));
        ) {
            for (int i = 0; i < size; i++) {
                number = ran.nextInt(max);
                file.println(number);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File " + name +  " has been created!");
    }
}
