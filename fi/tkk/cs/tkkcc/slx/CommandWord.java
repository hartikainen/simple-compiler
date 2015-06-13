package fi.tkk.cs.tkkcc.slx;

/**
 * Supported commands.
 * 
 * @author Timo Montonen
 */
public enum CommandWord {

    /** Define label(address) */
    LAB(1),
        /** Jump to (address) if pop() == 0 */
    JZE(1),
        /** Jump to (address) */
    JMP(1),
        /** Allocate memory area size of x = pop(). Push memory
         * address to allocated memory */
    ALC(0),
        /** Store frame, n frame size */
    SFR(1),
        /** Call subprogram (address) */
    SBR(2), 
        /** Return from subprogram */
    RET(0), 
        /** Push to stack (number) */
    ENT(1), 
        /** Store x = pop() to address = pop() + FP */
    STL(0), 
        /** Store x = pop() to address = pop() */
    STM(0), 
        /** Load to stack from address = pop() + FP */
    LDL(0), 
        /** Load to stack from address = pop() */
    LDM(0), 
        /** Push( pop() + pop()) */
    ADD(0), 
        /** Push( pop() - pop()) */
    SUB(0), 
        /** Push( pop() * pop()) */
    MUL(0), 
        /** Push( pop() / pop()) */
    DIV(0), 
        /** Push( -pop()) */
    UMN(0), 
        /** Write pop() to stdout */
    WRI(0), 
        /** Read an integer from  stdin and push it to the stack*/
    REA(0), 
        /** x = pop(), y = pop; push (y==x) */
    REQ(0), 
        /** x = pop(), y = pop; push (y!=x) */
    RNE(0), 
        /** x = pop(), y = pop; push (y<x) */
    RLT(0), 
        /** x = pop(), y = pop; push (y>x) */
    RGT(0), 
        /** x = pop(), y = pop; push (y<=x) */
    RLE(0), 
        /** x = pop(), y = pop; push (y>=x) */
    RGE(0), 
        /** x = pop(); push(!x) */
    NOT(0), 
        /** Stop the program */
    HLT(0); 

    /** The number of parameters of this instruction  */
    private final int numberOfParameters;

    /**
     *  Initialize a new command word.
     *
     * @param numberOfParameters the number of parameters
     */
    private CommandWord(int numberOfParameters) {
        this.numberOfParameters = numberOfParameters;
    }

    /**
     * Get the number of parameters for this instruction.
     */
    public int getNumberOfParameters() {
        return this.numberOfParameters;
    }

}
