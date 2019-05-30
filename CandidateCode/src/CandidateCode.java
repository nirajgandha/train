import java.util.*;

public class CandidateCode {
    private static String[] boxesString;
    private static int[] numberOfBoxes;
    private static int[] result;
    private static int index;
    public static void main(String[] args){
        Scanner scanner = new Scanner (System.in);
        int numberOfTestCases = Integer.parseInt (scanner.nextLine());
        boxesString = new String[numberOfTestCases];
        numberOfBoxes =new int[numberOfTestCases];
        result =new int[numberOfTestCases];
        Random random = new Random();
        for (int i=0; i<numberOfTestCases;i++){
            numberOfBoxes[i] = Integer.parseInt (scanner.nextLine());
            String str = "";
            for (int j=0 ;j<numberOfBoxes[i];j++){
                str=str.concat(random.nextInt(10000) +" ");
            }
            boxesString[i] = str.trim();
//            boxesString[i] = scanner.nextLine ();
            System.out.println(str.trim());
            createBoxNumberArrayfromString(str.trim(), numberOfBoxes[i]);
            index = i;
        }
//        createBoxNumberArray ();
        printResult();
    }

    private static void printResult() {
        System.out.println("print");
        for (int value : result) {
            System.out.println(value);
        }
    }

    private static void createBoxNumberArray() {
        for (int i=0;i<boxesString.length;i++){
            String[] boxNumberArray = boxesString[i].split (" ");
//            index = i;
            createSubsetArray (boxNumberArray,numberOfBoxes[i]);
        }
    }

    private static void createBoxNumberArrayfromString(String string, int numberOfBoxes) {
            String[] boxNumberArray = string.split (" ");
            createSubsetArray (boxNumberArray,numberOfBoxes);

    }
    private static void createSubsetArray(String[] boxNumberArray, int numberOfBoxes) {
        ArrayList<String> subsetList = new ArrayList<String>();
        int num = 0;
        for (int i = 1; i < (1 << numberOfBoxes); i++) {
            String js = "";
            for (int j = 0; j < numberOfBoxes; j++) {
                if ((i & (1 << j)) > 0) {
                    js = js.concat (boxNumberArray[j].concat (" "));
                }
            }
            subsetList.add(js.trim());
            num = i;
        }
        calculateResult(subsetList,num);
    }
    private static void calculateResult(ArrayList<String> subsetArray, int num) {
        int[] localResult = new int[num];
        for (int i=1; i<=num;i++){
            boolean sameChar = false;
            String subsetString = subsetArray.remove(0);
            if (subsetString.equals(""))
                continue;
            if (subsetString.contains (" ")){
                String[] subsetSplit = subsetString.split (" ");
                int strNum=0;
                while (strNum<subsetSplit.length) {
                    char pattern = ' ';
                    int index=0;
                    while (index<subsetSplit[strNum].length ()) {
                        pattern = subsetSplit[strNum].charAt (index);
                        for (int nextStr = strNum + 1; nextStr < subsetSplit.length; nextStr++) {
                            if (subsetSplit[nextStr].indexOf (pattern) != -1) {
                                localResult[i - 1] = 0;
                                sameChar = true;
                                break;
                            }
                        }
                        index++;
                    }
                    if (sameChar){
                        break;
                    }
                    strNum++;
                }
                if(!sameChar){
                    int sum =0;
                    for (String aSubsetSplit : subsetSplit) {
                        sum = sum + Integer.parseInt (aSubsetSplit);
                    }
                    localResult[i-1] = sum;
                }
            } else {
                localResult[i-1] = Integer.parseInt (subsetString);
            }
        }
        Arrays.sort (localResult );
        result[index] = localResult[localResult.length-1];
//        System.out.println (localResult[localResult.length-1]);
    }
}
