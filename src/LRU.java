import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
 * Based on Queue
 * Input: a txt file with numbers or characters on each line.
 *          only accept unsigned integer lines into the program
 * Output: print all the numbers that resulted a page fault
 *
 * 0. main function will take in "lru 27" and pass 27 to LRU as the size of the cache (queue)
 *      handle "simulate -lru 27" as well
 * 1. test how to get input from "cat input.txt"
 * 2. cleanUp() to clean up the input
 * 3. LRU(input, cacheSize) uses a queue to simulate cache,
 *      print the input element when there is a page fault
 *
 * Question:
 *      1. should "3abv" be treated as 3 or should it be discarded
 *      2. How to start the java program by typing "lru 42"?
 *
 *  Things to do:
 *      1. check after cleanup, if the input size is between 10 and 500
 *
 * parsing command line input/param:
 * $cat sample1.txt | java LeastRecentlyUsed 3
 * */
public class LRU {
    private static void LRU(List<String> input, int cacheSize) {
        int[] array = convertToIntArray(input);
        Queue<Integer> queue = new ArrayDeque<>();
        Queue<Integer> buffer = new ArrayDeque<>();
        //List<Integer> pageFaults = new ArrayList<>();
        int numPageFault = 0;
        System.out.println("LRU simulation begins");
        System.out.println("Page Fault Occurred: ");
        //1. print the request that results in page fault
        //2. add this page into pageFaults
        //3. update numPageFault
        for (int i = 0; i < array.length; i++) {
            if (queue.contains(array[i])) {
                while (queue.peek() != array[i]) {
                    int temp = queue.poll();
                    buffer.offer(temp);
                }
                //most recently used
                int mru = queue.poll();
                while (!queue.isEmpty()) {
                    int temp = queue.poll();
                    buffer.offer(temp);
                }
                while (!buffer.isEmpty()) {
                    int temp = buffer.poll();
                    queue.offer(temp);
                }
                queue.offer(mru);
            } else {
                queue.offer(array[i]);
                System.out.print(array[i] + ", ");
                //pageFaults.add(array[i]);
                numPageFault++;
                if (queue.size() > cacheSize) {
                    queue.poll();
                }
            }
        }
        System.out.println();
        System.out.println("Number of requests: " + input.size());
        System.out.println("Number of page faults occurred: " + numPageFault);
        double percent = ((double) numPageFault / input.size()) * 100;
        System.out.println("Percentage of page fault: " + String.format("%.2f", percent) + "%");
        //System.out.println("Page faults are also stored in a List of Integers");
        System.out.println();
    }

    private static int[] convertToIntArray(List<String> input) {
        int[] pure = new int[input.size()];
        for (int i = 0; i < input.size(); i++) {
            pure[i] = Integer.parseInt(input.get(i));
        }
        return pure;
    }

    private static boolean isPositiveNumber(String a) {
        try {
            int intValue = Integer.parseInt(a);
            if (intValue >= 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    private static void cleanUp(List<String> rawInput) {
        for (int i = 0; i < rawInput.size(); i++) {
            if (!isPositiveNumber(rawInput.get(i))) {
                rawInput.remove(i);
                i--;
            }
        }
    }
    public static void main(String[] args) {
        int cacheSize = Integer.parseInt(args[0]);
        System.out.println();
        System.out.println("Cache size is: " + cacheSize);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            List<String> input = new ArrayList<>();
            String x;
            while ((x = br.readLine()) != null) {
                input.add(x);
            }
            cleanUp(input);
            LRU(input, cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
