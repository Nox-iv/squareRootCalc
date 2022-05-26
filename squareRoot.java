/*
SQUARE ROOT CALCULATOR TEST TABLE
Test | Input | Expected | Actual
INITIAL NUMBER INPUT
1 | -1 | prompts user to enter only positive integers | As Expected
2 | 0 | prompts user to enter only positive integers | As Expected
3 | "abc" | prompts user to enter only integers | As Expected
4 | 1 | moves onto asking for decimal places | As Expected
5 | 32 | moves onto asking for decimal places | As Expected
6 | 734 | moves onto asking for decimal places | As Expected
DECIMAL PLACES INPUT
1 | -1 | prompts user to enter only integers within the range 1-7 | As Expected
2 | 0 | prompts user to enter only integers within the range 1-7 | As Expected
3 | 8 | prompts user to enter only integers within the range 1-7 | As Expected
4 | "abc" | prompts user to enter only integers | As Expected
5 | 1 | moves onto calculating the square root | As Expected
6 | 2 | moves onto calculating the square root | As Expected
7 | 3 | moves onto calculating the square root | As Expected
8 | 4 | moves onto calculating the square root | As Expected
9 | 5 | moves onto calculating the square root | As Expected
10 | 6 | moves onto calculating the square root | As Expected
11 | 7 | moves onto calculating the square root | As Expected
SQUARE ROOT OUTPUT
1 | 5,1 | 2.2 | As Expected
2 | 5,2 | 2.23 | As Expected
3 | 5,3 | 2.236 | As Expected
4 | 5,4 | 2.2360 | As Expected
5 | 5,5 | 2.23606 | As Expected
6 | 5,6 | 2.236067 | As Expected
7 | 5,7 | 2.2360679 | As Expected
8 | 2349,7 | 48.4664832 | As Expected
*/
static void squareRootCalc(Scanner scannerInput) {
    int numToRoot = validIntInput(scannerInput); //Validates user input to only allow an integer
    while ((numToRoot < 1)) { //Checks to ensure the integer input is positive
        System.out.println("Please Enter Only Positive Integers.");
        numToRoot = validIntInput(scannerInput);
    }
    System.out.println("How many decimal places do you want the solution calculated to (Range 1-7):");
    int numOfDecimals = validIntInput(scannerInput);
    while (numOfDecimals < 1 || numOfDecimals > 7) { //Ensures the desired number of decimal places the uses wants is within the rage the specification allows
        System.out.println("Please Enter Only Integers Within The Range 1-7.");
        numOfDecimals = validIntInput(scannerInput);
    }
    double lowerBound = 0;
    double upperBound = 0;
    double average; //Predefining variables to be used throughout the 2 following loops
    DecimalFormat formatter = new DecimalFormat();
    formatter.setMaximumFractionDigits(numOfDecimals); //Tells the formatter to only allow a maximum number of decimal places that's equal to the number the user entered earlier for desired decimal places
    formatter.setMinimumFractionDigits(numOfDecimals); //Tells the formatter it must have at least numOfDecimals amount of decimal places which is equal to the number the user entered earlier for desired decimal places
    formatter.setRoundingMode(RoundingMode.DOWN); //Sets the rounding mode to down if the number being formatted is larger than the specified maximum allowed decimals, this stops rounding errors and ensures the output is correct
    for (double x = 0; x < numToRoot; x += 0.1) { //starts at 0 and increments by 0.1 each time calculating the square of that number until it reaches a number that's square is greater than the number we are square rooting, this gives us our upper bound as if the number we found squared is higher than the number we wish to square root it means the square root value must be lower than this number we found
        if ((x * x) > numToRoot) {
            upperBound = x;
            break;
        } else {
            lowerBound = x;
        }
    }
    do {
        average = (upperBound + lowerBound) / 2;
        if (numToRoot < (average * average)) {
            upperBound = average;
        } else {
            lowerBound = average;
        }
    } while (upperBound - lowerBound > (1 * Math.pow(10, -(numOfDecimals + 1)))); //We refine our bounds according to the method outlined in the specification, we do this until the difference between our bounds is less than our decimal place precision
    //To check if the difference is less than the decimal place precision I take the number of decimals we want and times 1 by 10 to the power of - the number of decimals we want, this gives a number like 0.001 which is the number the difference must be less than
    System.out.println("The square root of " + numToRoot + " to " + numOfDecimals + " decimal places is " + formatter.format(average));
}