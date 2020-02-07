/*** A driver class meant to open a file in C code and trace over it.
 * It uses stacks to hold each block of code, popping and pushing to find and print
 * variables within those blocks.
 *
 * @author
 * Nicole Niemiec
 * #112039349
 * CSE 214 REC08
 * HOMEWORK #3
 *
 * @version 1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
@SuppressWarnings("all")
public class BlockTracer {

    public static void main(String[] args) {

        Stack<Block> stack = new Stack<Block>();
        Stack<Block> tempStack = new Stack<Block>();
        String line = "";
        int index = 0;


        int intCount = 0;

        Block b = new Block();

        String openBracket = "{";
        String intName = "int ";
        String print = "/*$print ";
        String closeBracket = "}";
        String comma = ", ";
        String equals = " = ";
        String semicolon = ";";

        String[] lineArray;
        String newString = "";
        int value = 0;
        String name = "";
        String[] lineArrayTemp;

        Scanner input = new Scanner(System.in);
        System.out.println("Enter file name: ");
        String fileName = input.nextLine();


        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();

                lineArray = line.split(";");

                for (int x = 0; x < lineArray.length; x++) {

                    if (lineArray[x].contains(openBracket)) {
                        b = new Block();
                        stack.push(b);
                    }

                    if (lineArray[x].contains(" " + intName) || lineArray[x].contains("\t" + intName)) {

                        String lineCopy = lineArray[x];

                        lineCopy = lineCopy.substring(line.indexOf(intName) + 4);

                        lineArrayTemp = lineCopy.split("[\\s+]");

                        newString = "";

                        for (int i = 0; i < lineArrayTemp.length; i++) {
                            newString += lineArrayTemp[i];
                            if (i != lineArrayTemp.length - 1)
                                newString += " ";
                        }

                        String tempString = newString;
                        //newString = newString.substring(0, newString.indexOf(";"));

                        //String[] lineArray2 = newString.split([""])

                        String[] breakdown = newString.split("[\\s+]");
                        newString = "";
                        for (int i = 0; i < breakdown.length; i++) {
                            newString += breakdown[i];
                        }

                        String[] breakdown2 = newString.split("[;,=]");
                        for (int i = 0; i <= breakdown2.length - 1; i++) {
                            if (Character.isLetter(breakdown2[i].charAt(0)) && i != breakdown2.length - 1) {
                                if (Character.isDigit(breakdown2[i + 1].charAt(0))) {
                                    name = breakdown2[i];
                                    value = Integer.parseInt(breakdown2[i + 1]);
                                    Variable v1 = new Variable(name, value);
                                    stack.peek().add(v1);
                                }
                                else if(Character.isLetter(breakdown2[i + 1].charAt(0)) && stack.peek().searchName(breakdown2[i + 1])) {
                                    name = breakdown2[i];
                                    value = stack.peek().getValue(breakdown2[i + 1]);
                                    Variable v1 = new Variable(name, value);
                                    stack.peek().add(v1);
                                    i++;
                                }
                                else if(Character.isLetter(breakdown2[i + 1].charAt(0)) &&
                                        !stack.peek().searchName(breakdown2[i + 1]) && lineArray[x].contains("int " + breakdown2[i] + " = " + breakdown2[i + 1])){
                                    System.out.println("Variable " + breakdown2[i + 1] + " does not exist in the block.");
                                    i++;
                                }
                                else{
                                    name = breakdown2[i];
                                    value = 0;
                                    Variable v1 = new Variable(name, value);
                                    stack.peek().add(v1);
                                }

                            } else if (Character.isLetter(breakdown2[i].charAt(0)) && i == breakdown2.length - 1) {
                                name = breakdown2[i];
                                value = 0;
                                Variable v1 = new Variable(breakdown2[i], value);
                                stack.peek().add(v1);
                            }
                        }

                    }

                    if (lineArray[x].contains(print)) {

                        String tempLine = lineArray[x];
                        if (tempLine.contains("LOCAL*/")) {
                            stack.peek().printAllValues();
                        } else {
                            tempLine = tempLine.substring(line.indexOf(print) + 9);
                            lineArrayTemp = tempLine.split("[*/]");

                            name = lineArrayTemp[0];
                            while (!stack.isEmpty()) {

                                try {
                                    stack.peek().searchPrint(name);
                                    break;
                                } catch (VariableNotFoundException ex) {
                                    tempStack.push(stack.pop());

                                }

                            }
                            if (stack.isEmpty())
                                System.out.println("Variable not found: " + name);
                            while (!tempStack.isEmpty()) {
                                stack.push(tempStack.pop());
                            }
                        }

                    }
                    if (lineArray[x].contains(closeBracket)) {
                        if (!stack.isEmpty())
                            stack.pop();
                    }

                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File not found.");
        }
    }
}