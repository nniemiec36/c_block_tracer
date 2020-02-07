/**
 * An object class meant to serve as a representation of an int variable
 * in the C language. Each Variable object holds an int value and a String name.
 *
 * @author
 * Nicole Niemiec
 * #112039349
 * CSE 214 REC08
 * HOMEWORK #3
 *
 * @version 1
 */

public class Variable {

    private String name;
    private int initialValue;

    /**
     * Constructor that creates a new Variable object and initializes the name
     * to an empty string and the initial value to 0.
     */
    public Variable(){
        name = "";
        initialValue = 0;
    }

    /**
     * Constructor that takes in two parameters to create a new Variable object and initialize
     * these two values to the data variables.
     * @param name
     *      Name of the variable to be set to.
     * @param initialValue
     *      Initial value of the variable to be set to.
     */
    public Variable(String name, int initialValue){
        this.name = name;
        this.initialValue = initialValue;
    }

    /**
     * An accessor method that returns the name of the Variable
     * as a String.
     * @return
     *      Returns the name variable as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * A mutator method that sets the name of the variable to
     * the name defined by the user.
     * @param name
     *      Name to be set to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * An accessor method to return the int value
     * of the initialValue of the Variable.
     * @return
     *      Returns the initialValue of the variable as an int.
     */
    public int getInitialValue() {
        return initialValue;
    }

    /**
     * A mutator method to set the initialValue of the Variable
     * to a int value defined by the user.
     * @param initialValue
     *      The int value being set to initialValue.
     */
    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    /**
     * Returns the Variable object as a String.
     * @return
     *      Returns the String representation of the Variable.
     */
    @Override
    public String toString() {
        return name +  "\t \t \t" + initialValue;
    }

    /**
     * Compares two Variable objects to see if they are the same.
     * @param obj
     *      The object that the Variable is being compared to.
     * @return
     *      True if they are the same object, false if they are not the same object.
     */
    @Override
    public boolean equals(Object obj) {
        return ((Variable)obj).name.equals(this.name) && ((Variable)obj).initialValue == this.initialValue;
    }
}
