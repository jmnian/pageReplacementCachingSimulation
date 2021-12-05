import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class test {
    public static void main(String[] args) {
        Pair test = new Pair(4, 0);
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(test);
        System.out.println(queue.contains(test.val));
        //Read in the input, "cat test.txt" only works if "lru 27" is the first line in test.txt
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            String str = br.readLine();
//            String[] init = str.split("\\s+");
//            int cacheSize = Integer.parseInt(init[init.length - 1]);
//            System.out.println("The cache size is " + cacheSize);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            List<String> input = new ArrayList<>();
//            String x = null;
//            int i = 0;
//            while ((x = br.readLine()) != null) {
//                input.add(x);
//                i++;
//            }
//            System.out.println("The following is what is stored as String[]");
//            for (int j = 0; j < input.size(); j++) {
//                System.out.println(input.get(j));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //Read in "cat test.txt | lru 27" as a string
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            String commandLineInput = bufferedReader.readLine();
//            String[] inputSA = commandLineInput.split("\\s+");
//            int cacheSize = Integer.parseInt(inputSA[inputSA.length - 1]);
//            System.out.println("Cache size is initialize to be " + cacheSize);
//            System.out.println();
//            List<String> input = new ArrayList<>();
//            File file = new File(inputSA[1]);
//            BufferedReader br2 = new BufferedReader(new FileReader(file));
//            String thisLine = null;
//            while ((thisLine = br2.readLine()) != null) {
//                input.add(thisLine);
//            }
//            System.out.println("The following is what is stored as String[]");
//            for (int j = 0; j < input.size(); j++) {
//                System.out.println(input.get(j));
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

        //Old way: run java then type "cat sample.txt | lru 42"
        //        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            String commandLineInput = bufferedReader.readLine();
//            String[] inputSA = commandLineInput.split("\\s+");
//            int cacheSize = Integer.parseInt(inputSA[inputSA.length - 1]);
//            System.out.println("Cache size is initialize to be " + cacheSize);
//            List<String> input = new ArrayList<>();
//            File file = new File(inputSA[1]);
//            BufferedReader br2 = new BufferedReader(new FileReader(file));
//            String thisLine = null;
//            while ((thisLine = br2.readLine()) != null) {
//                input.add(thisLine);
//            }
//            System.out.println("The following is what is stored as String[]");
//            for (int j = 0; j < input.size(); j++) {
//                System.out.println(input.get(j));
//            }
//            cleanUp(input);
//            System.out.println("The following is what is stored as String[] after cleaning up");
//            for (int j = 0; j < input.size(); j++) {
//                System.out.println(input.get(j));
//            }
//            LRU(input, cacheSize);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
