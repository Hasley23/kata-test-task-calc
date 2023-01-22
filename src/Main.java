import java.util.Scanner;

// KATA
// ТЕСТОВАЯ ЗАДАЧА “КАЛЬКУЛЯТОР”
class Main {
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
    //
    public static void main(String[] args) throws Exception {
        // user input
        Scanner readerScanner = new Scanner(System.in);
        // tell user who am I
        System.out.println("Simple calc app. Input example: a + b, a - b, a * b, a / b");
        // expression get
        String inString = readerScanner.nextLine();
        // calc input
        String outString = calc(inString);

        // calc output
        System.out.println(outString);
    }

    // parse to int array
    static double[] parseFunc(String oneString, String twoString){
        double[] ret = new double[2];
        //numOneString numTwoString
        ret[0] = Double.parseDouble(oneString);
        ret[1] = Double.parseDouble(twoString);
        return ret;
    }

    // calc engine (a + b, a - b, a * b, a / b)
    public static String calc(String input) throws Exception {
        // Delete white spaces
        input = input.replaceAll(" ", "");
        // uncaps (for Roman digits)
        input = input.toLowerCase();

        // mode bool
        boolean isArabic = true;
        // current action
        actionsEnum currentActionEnum = actionsEnum.EMPTY;

        // was strings, but IDE wanted it, maybe better
        StringBuilder numOneString = new StringBuilder();
        StringBuilder numTwoString = new StringBuilder();

        // get array from input string
        char[] inputChars = input.toCharArray();

        // Roman digits
        String romanString = "ivx";
        // mode detector
        if(romanString.contains(String.valueOf(inputChars[0])))
            isArabic = false;


        // action symbols
        String opString = "+-*/";
        // operation counter (should be one)
        int opCountInt = 0;
        for (int i = 0; i<input.length(); i++){
            // when the operator found
            if (opString.contains(String.valueOf(inputChars[i]))){
                // when an expression starts with operators
                if (numOneString.toString().equals(""))
                    throw new Exception();
                // action recognition
                switch (inputChars[i]){
                    case '+':
                        currentActionEnum = actionsEnum.SUM;
                        i++;
                        break;
                    case '-':
                        currentActionEnum = actionsEnum.SUB;
                        i++;
                        break;
                    case '*':
                        currentActionEnum = actionsEnum.MUL;
                        i++;
                        break;
                    case '/':
                        currentActionEnum = actionsEnum.DIV;
                        i++;
                        break;
                    default:
                        throw new Exception();
                }
                // prevent complex tasks
                if (opCountInt == 1)
                    throw new Exception();
                opCountInt++;
            }

            if (opCountInt == 0)
                numOneString.append(inputChars[i]);
            else numTwoString.append(inputChars[i]);
        }

        double[] tempDoubles = new double[2];

        // Digits recognition
        // Roman
        if (!isArabic) {
            String tempString = "";
            for (int i = 0; i < tempDoubles.length; i++){
                // current active operand
                if (i == 0){
                    tempString = numOneString.toString();
                } else tempString = numTwoString.toString();

                // get normal values
                switch (tempString){
                    case "i":
                        tempDoubles[i] = 1;
                        break;
                    case "ii":
                        tempDoubles[i] = 2;
                        break;
                    case "iii":
                        tempDoubles[i] = 3;
                        break;
                    case "iv":
                        tempDoubles[i] = 4;
                        break;
                    case "v":
                        tempDoubles[i] = 5;
                        break;
                    case "vi":
                        tempDoubles[i] = 6;
                        break;
                    case "vii":
                        tempDoubles[i] = 7;
                        break;
                    case "viii":
                        tempDoubles[i] = 8;
                        break;
                    case "ix":
                        tempDoubles[i] = 9;
                        break;
                    case "x":
                        tempDoubles[i] = 10;
                        break;
                }
            }
        } else {
            // Arabic
            tempDoubles = parseFunc(numOneString.toString(), numTwoString.toString());
        }

        double res = 0;
        switch (currentActionEnum){
            case SUM:
                res = tempDoubles[0]+tempDoubles[1];
                break;
            case SUB:
                res = tempDoubles[0]-tempDoubles[1];
                break;
            case MUL:
                res = tempDoubles[0]*tempDoubles[1];
                break;
            case DIV:
                res = tempDoubles[0]/tempDoubles[1];
                break;
            default:
                throw new Exception();
        }
        if (!isArabic){
            // answer in Roman

        }

        // remove reminder if zero
        if (res % 1 == 0){
            String resString = String.valueOf(res);
            return resString.substring(0,resString.length() - 2);
        }

        return String.valueOf(res);
    }
}