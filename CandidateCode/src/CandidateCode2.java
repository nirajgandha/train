import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

public class CandidateCode2 {

    static String[] boxStringInput;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = Integer.parseInt(scanner.nextLine());
        boxStringInput = new String[numberOfTestCases];
        int[] numberOfBoxes = new int[numberOfTestCases];
        Random random = new Random();
        for (int i = 0; i < numberOfTestCases; i++) {
            numberOfBoxes[i] = Integer.parseInt(scanner.nextLine());
//            String str = scanner.nextLine ();
            String str = "";
            for (int j = 0; j < numberOfBoxes[i]; j++) {
                str = str.concat(random.nextInt(10000) + " ");
            }
            str = str.trim ();
            boxStringInput[i] = str;
            System.out.println(str);
        }
        createSubsetArray();
    }

    private static void createSubsetArray() {
        result = new int[boxStringInput.length];
        for (int i=0;i<boxStringInput.length;i++){
            String[] arr = boxStringInput[i].split (" ");
            result[i] = 0;
            resetData(i);
            printCombination(arr,arr.length,1);
            int size = 0;
            if (arr.length > 5){
                size = arr.length - 4;
            } else {
                size = (arr.length -1)/2;
            }
            for (int j=arr.length-1;j>=size;j--){
                resetData(i);
                printCombination(arr,arr.length,j);
            }
        }
        for (int res : result){
            System.out.println (res);
        }
    }
    private static String stringToBeChecked = "";
    private static int olduniqueness = 0;
    private static int indexs;
    private static int[] result;
    private static void resetData(int i) {
        stringToBeChecked = "";
        olduniqueness = result[i];
        indexs = i;
    }

    private static void printCombination(String[] arr, int n, int r) {
        int data[] = new int[r];
        combinationUtil(arr, n, r, 0, data, 0);
    }


    private static void combinationUtil(String[] arr, int n, int r, int index, int data[], int i) {
        if (index == r) {
            for (int j = 0; j < r; j++){
                stringToBeChecked = stringToBeChecked.concat (data[j]+" ");

            }
            if (!stringToBeChecked.trim ( ).equals ("")){
                int uniqueness = checkUniqueness(stringToBeChecked.trim());
                if (olduniqueness < uniqueness) {
                    System.out.println(stringToBeChecked.trim ());
                    result[indexs] = uniqueness;
                    olduniqueness = uniqueness;
                }
            }
            resetData (indexs);
            return;
        }

        // When no more elements are there to put in data[]
        if (i >= n)
            return;

        // current is included, put next at next
        // location
        data[index] = Integer.parseInt (arr[i]);
        combinationUtil(arr, n, r, index + 1,
                data, i + 1);

        // current is excluded, replace it with
        // next (Note that i+1 is passed, but
        // index is not changed)
        combinationUtil(arr, n, r, index, data, i + 1);
    }

    private static int checkUniqueness(String subsetString) {
        if (subsetString.contains(" ")) {
            String[] subsetSplit = subsetString.split(" ");
            int strNum = 0;
            while (strNum < subsetSplit.length) {
                int indexs = 0;
                while (indexs < subsetSplit[strNum].length()) {
                    for (int nextStr = strNum + 1; nextStr < subsetSplit.length; nextStr++) {
                        if (subsetSplit[nextStr].contains(String.valueOf(subsetSplit[strNum].charAt(indexs)))) {
                            subsetSplit = null;
                            System.gc();
                            return 0;
                        }
                    }
                    indexs++;
                }
                strNum++;
            }
            int sum = 0;
            for (String aSubsetSplit : subsetSplit) {
                sum = sum + Integer.parseInt(aSubsetSplit);
            }
            subsetSplit = null;
            System.gc();
            return sum;
        } else {
            return Integer.parseInt(subsetString);
        }
    }
}