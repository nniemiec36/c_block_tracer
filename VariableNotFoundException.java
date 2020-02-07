/**
 * An object class meant to serve as a representation of an error found in the BlockTracer program.
 * Each Exception object holds a note that can be set by the user.
 *
 * @author
 * Nicole Niemiec
 * #112039349
 * CSE 214 REC08
 * HOMEWORK #3
 *
 * @version 1
 */

public class VariableNotFoundException extends Exception {

    private String note;

    /**
     * A Constructor of the VariableNotFoundException object.
     */
    public VariableNotFoundException(){
        super("Variable not found in block.");
    }

    /**
     * A constructor of the VariableNotFoundException object that
     * takes in a note set by the user.
     * @param note
     *      Error message that the user wants printed.
     */
    public VariableNotFoundException(String note){
        super("Variable not found in block." + note);
        this.note = note;
    }
}
