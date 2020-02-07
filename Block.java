/**
 * An object class meant to serve as a representation of a block of code
 * in the C language. It holds an array of Variable objects in the Object
 * which can be added to from the driver class.
 *
 * @author
 * Nicole Niemiec
 * CSE 214 REC08
 * HOMEWORK #3
 *
 * @version 1
 */

public class Block {

    private Variable[] blockArray;
    private int count = 0;

    /**
     * Constructs a new Block object that holds a Variable array. Sets
     * the Variable array to a new array of 10 null elements. Blocks
     * can only hold ten variables at a time.
     */
    public Block(){

        blockArray = new Variable[10];
    }

    /**
     * Method to add a Variable into the Variable array.
     * @param v
     *      Variable that is being added to the array.
     * @throws IllegalArgumentException
     *      Throws an IllegalArgumentException if there is already
     *      a Variable within that array (the Block) with the same name.
     */
    public void add(Variable v) throws IllegalArgumentException{

        int index = 0;
        boolean unique = true;
        for(int i = 0; i < count; i++){
            if(blockArray[i].getName().equals(v.getName())) {
                unique = false;
                index = i;
                break;
            }
        }
        if(unique)
            blockArray[count++] = v;
        else
            throw new IllegalArgumentException("Two variables cannot have the same name" +
                    " in the same block.");
//blockArray[index].setInitialValue(v.getInitialValue());
    }

    /**
     * Searches for a Variable object within that block.
     * @param v
     *      Variable being compared with the Variables in the array.
     * @return
     *      Returns true if a Variable in that array has the same name,
     *      returns false if no Variable in that array has the same name.
     */
    public boolean search(Variable v){
        for(int i = 0; i < count; i++){
            if(v.getName().equals(blockArray[i].getName())){
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for a Variable object within that block with a certain name.
     * @param name
     *      Name being compared with the names of Variables in that array.
     * @return
     *      Returns true if a Variable in that array has the same name.
     *      Returns false if no Variable in that array has the same name.
     */
    public boolean searchName(String name){
        for(int i = 0; i < count; i++){
            if(name.equals(blockArray[i].getName()))
                return true;
        }
        return false;
    }

    /**
     * Method to return a value of a specific Variable in the array.
     * @param name
     *      The name of Variable we are searching for.
     * @return
     *      Returns the integer value of the Variable.
     */
    public int getValue(String name){
        for(int i = 0; i < count; i++){
            if(name.equals(blockArray[i].getName()))
                return blockArray[i].getInitialValue();
        }
        return 0;
    }



    /**
     * An accessor method to return the array.
     * @return
     *      Returns the Variable array within the Block object.
     */
    public Variable[] getBlockArray() {
        return blockArray;
    }

    /**
     * Mutator method to set the Variable array to a new Variable array.
     * @param blockArray
     *      The Variable array being set.
     */
    public void setBlockArray(Variable[] blockArray) {
        this.blockArray = blockArray;
    }

    /**
     * An accessor method for returning the current number of Variable objects in the array.
     * @return
     *      The number of Variable objects in the array.
     */
    public int getCount() {
        return count;
    }

    /**
     * A mutator method for setting the current number of Variable objects in the array.
     * @param count
     *      The new number of Variable objects in the array.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * A method for printing a table of all the Variable objects within the array.
     */
    public void printAllValues(){

        if (count > 0) {
            System.out.printf("%-30s%-30s", "Variable Name","Initial Value");
            System.out.println();
            for(int i = 0; i < count; i++){
                System.out.printf("%-30s%-30s", blockArray[i].getName(), blockArray[i].getInitialValue());
                System.out.println();
            }
        }
        else
            System.out.println("No local variables to print.");

        //System.out.println();
    }

    /**
     * A method to print a certain Variable object within the block.
     * @param name
     *      Variable that the user is looking to print.
     * @throws VariableNotFoundException
     *      Throws a new VariableNotFoundException if the Variable object is not found in that block.
     */
    public void searchPrint(String name) throws VariableNotFoundException{

        boolean found = false;
        for(int i = 0; i < count; i++){
            if(name.equals(blockArray[i].getName())){
                found = true;
                System.out.printf("%-30s%-30s", "Variable Name","Initial Value");
                System.out.println();
                System.out.printf("%-30s%-30s", blockArray[i].getName(), blockArray[i].getInitialValue());
                System.out.println();
            }
        }

        if(!found)
            throw new VariableNotFoundException("Variable not found.");
    }
}
