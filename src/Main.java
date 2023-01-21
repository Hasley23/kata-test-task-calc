import java.util.Scanner;

// KATA
// ТЕСТОВАЯ ЗАДАЧА “КАЛЬКУЛЯТОР”
public class Main {
    // + - * /
    enum actionsEnum {
        EMPTY,
        SUM,
        SUB,
        MUL,
        DIV
    }
    // digits (N -> zero)
    enum romanEnum {
        N,
        I,
        II,
        III,
        IV,
        V,
        VI,
        VII,
        VIII,
        IX,
        X
    }
    // IO
    public static void main(String[] args) {
        // user input
        Scanner readerScanner = new Scanner(System.in);
        // tell user who am I
        System.out.println("Simple calc app. Input example: a + b, a - b, a * b, a / b");
        // expression get
        String inString = readerScanner.nextLine();
        // calc input
        String outString = calc(inString);
        // calc output
        System.out.println("The result is: " + outString);
    }

    // parse to int array
    static double[] parseFunc(String oneString, String twoString){
        double[] ret = new double[2];
        try {//numOneString numTwoString
            ret[0] = Double.parseDouble(oneString);
            ret[1] = Double.parseDouble(twoString);
        } catch (Exception ex){
            System.out.println("Exception caught! The message is:\n"+ex.toString());
        }
        return ret;
    }

    // calc engine (a + b, a - b, a * b, a / b)
    public static String calc(String input){
        // Delete white spaces
        input = input.replaceAll(" ", "");
        Boolean isArabic = true;
        Boolean isValid = false;
        actionsEnum currentAction = actionsEnum.EMPTY;

        StringBuilder numOneString = new StringBuilder();
        StringBuilder numTwoString = new StringBuilder();

        // get array from input string
        char[] inputChars = input.toCharArray();
        // action recognition
        String operationsString = "+-*/";
        int opCountInt = 0;
        for (int i = 0; i<input.length(); i++){
            if (operationsString.contains(String.valueOf(inputChars[i]))){
                if (numOneString.toString().equals(""))
                    return "ERR";
                if (inputChars[i] == '+'){
                    currentAction = actionsEnum.SUM;
                } else if (inputChars[i] == '-'){
                    currentAction = actionsEnum.SUB;
                } else if (inputChars[i] == '*') {
                    currentAction = actionsEnum.MUL;
                } else currentAction = actionsEnum.DIV;
                if (opCountInt == 1)
                    return "ERR";
                opCountInt++;
                continue;
            }
            if (opCountInt == 0)
                numOneString.append(inputChars[i]);
            else numTwoString.append(inputChars[i]);
        }

        double[] temp = {0,0};
        // Parse numbers to ints
        try {
            temp = parseFunc(numOneString.toString(), numTwoString.toString());
        } catch (Exception ex){

        }
        if (isArabic)
            temp = parseFunc(numOneString.toString(), numTwoString.toString());
        else return "ERR";

        double res = 0;
        switch (currentAction){
            case SUM:
                res = temp[0]+temp[1];
                break;
            case SUB:
                res = temp[0]-temp[1];
                break;
            case MUL:
                res = temp[0]*temp[1];
                break;
            case DIV:
                res = temp[0]/temp[1];
                break;
        }


        return String.valueOf(res);
    }
}