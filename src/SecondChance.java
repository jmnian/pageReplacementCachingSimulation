/*
* Based on Queue
*
*
*
* */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SecondChance {
    private static void secondChance(List<String> input, int cacheSize) {
        int[] array = convertToIntArray(input);
        Queue<Integer> queue = new ArrayDeque<>();
        Queue<Integer> buffer = new ArrayDeque<>();
        //List<Integer> pageFaults = new ArrayList<>();
        int[] refBits = new int[findMax(array) + 1];
        int numPageFault = 0;
        System.out.println("Second Chance simulation begins: ");
        System.out.println("Page Fault Occurred: ");
        for (int i = 0; i < array.length; i++) {
            if (queue.contains(array[i])) {
                refBits[array[i]] = 1;
            }
            // current page not in cache
            else {
                System.out.print(array[i] + ", ");
                numPageFault++;
                //pageFaults.add(array[i]);
                if (queue.size() == cacheSize) {
                    while (!queue.isEmpty() && refBits[queue.peek()] != 0) {
                        refBits[queue.peek()] = 0;
                        int temp = queue.poll();
                        buffer.offer(temp);
                    }
                    if (queue.isEmpty()) {
                        while (!buffer.isEmpty()) {
                            int temp = buffer.poll();
                            queue.offer(temp);
                        }
                        queue.poll();
                    } else {
                        queue.poll();
                        while (!queue.isEmpty()) {
                            int temp = queue.poll();
                            buffer.offer(temp);
                        }
                        while (!buffer.isEmpty()) {
                            int temp = buffer.poll();
                            queue.offer(temp);
                        }
                    }
                }
                queue.offer(array[i]);
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

    private static int findMax(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
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
            secondChance(input, cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
