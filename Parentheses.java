package com.company;

/**
 * @autor Iuliia Patrusheva (ip.tiamo@gmail.com)
 *
 * This programm calculates the biggest amountof balanced parentheses in input string.
 * Task 2.
 *
 */
public class Parentheses {
    private static final char OPENNING_P= '(';
    private static final char CLOSING_P = ')';
    private static int maxCountBalanced = 0;

    public static void main(String[] args) {
	String entryParetheses = "()((()))";
	if (entryIsValid(entryParetheses)) {
	    calculateBalancedParentheses(entryParetheses);
    }
        System.out.println("Maximal amount of balanced parentheses in " + entryParetheses + " is " + maxCountBalanced);
    }

    /**
     * Checks if the input strung valid or not: false if
     * 1) input is empty
     * 2) input contains any other character (howeverбша this check wasnt there,
     * it would still count the paretheses right ;) )
     *
     * @param entryParetheses Input string, that should be checked
     * @return true if entry is valid
     */
    private static boolean entryIsValid(String entryParetheses) {
        if (entryParetheses == "") {
            System.out.println("Input is empty :(");
            return false;
        } else if (!entryParetheses.matches("([\\(\\)]*)")) {
            System.out.println("There are wrong characters in the input");
            return false;
        }
        return true;
    }

    /**
     * Searches in the Input string opening parenthis and start counting from there (in stepInto() method)
     *
     * @param entryParetheses Input string, that should be analysed
     */
    private static void calculateBalancedParentheses(String entryParetheses) {
        //here we are looking for entry point, then start analysing
        //all the possibilities with starting parethes gets into calculation
        int countBalanced = 0;
        int lengthOfInput =  entryParetheses.length();
        for (int i = 0; i < lengthOfInput; i++) {
            if (entryParetheses.charAt(i) == OPENNING_P) {
                String openingPart = entryParetheses.substring(i);
                stepInto(openingPart);
            }
        }
    }

    /**
     * The logic in the method might seem a bit strange, but it is an interpretation of Final state automat.
     * Starting from the "(" it counts amount of all opening P., then, as soon there is ")" (closing P.) it starts
     * counting the closing ones, till another opening ( begins. Then it calculates the result of how many of them
     * where balanced.
     *
     * @param entryParetheses   The part of input string, that starts with "("
     */
    private static void stepInto(String entryParetheses) {
        //start analysing the part of the code till the next part
        int openingP = 0;
        int closingP = 0;
        int resultingBalancedP = 0;
        for (int i = 0; i < entryParetheses.length(); i++) {
                switch (entryParetheses.charAt(i)) {
                    case OPENNING_P:
                        openingP++;
                        break;
                    case CLOSING_P:
                        closingP++;
                        if (i + 1 < entryParetheses.length()) {
                            if (entryParetheses.charAt(i + 1) == OPENNING_P) {
                                resultingBalancedP = summariseMax(openingP, closingP);
                                openingP = 0;
                                closingP = 0;
                                String openingPart = entryParetheses.substring(i+1);
                                stepInto(openingPart);
                            }
                        }  else if (i+1 == entryParetheses.length()) {
                            resultingBalancedP = summariseMax(openingP, closingP);
                        }
                        break;

                }
            }
            if (maxCountBalanced < resultingBalancedP) maxCountBalanced = resultingBalancedP;
    }

    /**
     * This method calculates the final amount of balanced parentheses for the specific part in the entry
     * @param openingP amount of opening Paretheses from the beginning till first closingP. beginns
     * @param closingP amount of closing Paretheses from the beginning till next openingP. beginns
     * @return final number of balanced paretheses
     */
    private static int summariseMax(int openingP, int closingP) {
        return (openingP >= closingP) ? closingP*2 : openingP*2;
    }
}

