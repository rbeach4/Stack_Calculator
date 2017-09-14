/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2.beach;

import java.util.Stack;

/**
 * sources in view class  
 * @author rbeach4
 */
public class Model {

    private Stack<Integer> firstNumStk;
    private Stack<Integer> secondNumStk;
    private Stack<Integer> resultStk;
    private boolean swapped;
    private String s1;
    private String s2;

    /**
     * takes the first number in first text box and puts it on the firstNum stack
     * @param s1 - string passed to be put onto the stack
     */
    public void pushOntoStackFirstNum(String s1) {
        firstNumStk = new Stack<Integer>();
        for (int i = 0; i < s1.length(); i++) {
            int digit1 = Character.getNumericValue(s1.charAt(i));
            firstNumStk.push(digit1);
        }
    }

    /**
     * takes the second number in second text box and puts it on the secondNum stack
     * @param s2 - string passed to be put onto the stack
     */
    public void pushOntoStackSecondNum(String s2) {
        secondNumStk = new Stack<Integer>();
        for (int i = 0; i < s2.length(); i++) {
            int digit2 = Character.getNumericValue(s2.charAt(i));
            secondNumStk.push(digit2);
        }
    }

    /**
     * checks to see if the instance variable is true or false 
     * @return swapped - returns the instance variable
     */
    public boolean isSwapped() {
        return swapped;
    }
    /**
     * adds zeros onto the front of string if the lengths differ
     */
    public void addZeros() {
        Stack<Integer> tempStk = new Stack<Integer>();
        String temp = "0";
        int s1add = 0;
        int s2add = 0;
        if (s1.length() > s2.length()) {
            s2add = s1.length() - s2.length();
            for (int i = 0; i < s2add; i++) {
                s2 = temp + s2;
            }
            tempStk = this.stringToStack(s2);
            this.secondNumStk = tempStk;
        } else if (s2.length() > s1.length()) {
            s1add = s2.length() - s1.length();
            for (int i = 0; i < s1add; i++) {
                s1 = temp + s1;
            }
            tempStk = this.stringToStack(s1);
            this.firstNumStk = tempStk;
        }
    }

    /**
     * adds the numbers in the stack by each index
     * @return resultStk - final stack with the product of the addition
     */
    public Stack<Integer> add() {
        int carry = 0;
        resultStk = new Stack<Integer>();
        while (!(firstNumStk.isEmpty()) || !(secondNumStk.isEmpty())) {
            int digit1 = 0;
            int digit2 = 0;
            if (!(firstNumStk.isEmpty())) {
                digit1 = firstNumStk.pop();
            }
            if (!(secondNumStk.isEmpty())) {
                digit2 = secondNumStk.pop();
            }
            int result = digit1 + digit2 + carry;
            int resultDigit = result % 10;
            carry = result / 10;
            resultStk.push(resultDigit);
        }
        if (carry > 0) {
            resultStk.push(carry);
        }
        return resultStk;
    }

    /**
     * checks the strings to see what is bigger, swaps them, and subtracts the value at each index
     * @return resultStk - final product of subtraction of both stacks
     */
    public Stack<Integer> subtract() {
        int borrow = 0;
        int result = 0;
        int digit1 = 0;
        int digit2 = 0;
        s1 = this.stackToString(firstNumStk);
        s2 = this.stackToString(secondNumStk);
        Stack<Integer> temp = new Stack<Integer>();
        resultStk = new Stack<Integer>();
        if (s1.length() != s2.length()) {
            this.addZeros();
        }
        if (s1.compareTo(s2) < 0) {
            temp = firstNumStk;
            firstNumStk = secondNumStk;
            secondNumStk = temp;
            swapped = true;
        }
        while (!(firstNumStk.isEmpty()) || !(secondNumStk.isEmpty())) {
            digit1 = 0;
            digit2 = 0;
            if (!(firstNumStk.isEmpty())) {
                digit1 = firstNumStk.pop();
            }
            if (!(secondNumStk.isEmpty())) {
                digit2 = secondNumStk.pop();
            }
            digit1 = digit1 - borrow;
            borrow = 0;
            if (digit1 < digit2) {
                borrow = 1;
                result = (digit1 + 10) - digit2;
            } else {
                result = digit1 - digit2;
            }
            resultStk.push(result);
        }
        return resultStk;
    }

    /**
     * takes in a stack and converts it to a string and reverses it
     * @param s1 - stack being changed
     * @return result - returns the result but backwards
     */
    public String stackToString(Stack<Integer> s1) {
        String result = "";
        Stack<Integer> temp = (Stack<Integer>) s1.clone();
        while (!(temp.isEmpty())) {
            result += temp.pop();
        }
        return new StringBuilder(result).reverse().toString();
    }
  
    /**
     * converts a string back to a stack
     * @param s1 - string being put into a stack
     * @return temp - final stack made from string parameter
     */
    private Stack<Integer> stringToStack(String s1) {
        Stack<Integer> temp = new Stack<Integer>();
        for (int i = 0; i < s1.length(); i++) {
            int putStk = Character.getNumericValue(s1.charAt(i));
            temp.push(putStk);
        }
        return temp;
    }
}
