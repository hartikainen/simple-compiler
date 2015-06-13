package fi.tkk.cs.tkkcc.slx;

/**
 * An SLX instruction. Note that a compiler should use the emit
 * methods of SLXProgram to create instructions.
 *
 * @author Timo Montonen
 */
public class Instruction {

    /** The command word of the instruction */
    private CommandWord commandWord;
    /** The parameters of the command */
    private Integer[]   commandParameter;
    /** The source code line */
    private final int sourceLineNumber;

    /**
     * Initialize a new instruction.
     *
     * @param line the source code line
     * @param lineNumber the source code line number
     */
    Instruction(final String line, final int lineNumber) 
        throws IllegalInstructionException {
        super();
        
        this.parseLine(line);
        this.sourceLineNumber = lineNumber;
    }

    /**
     * Initialize a new instruction.
     *
     * @param command the command
     * @param integers the parameters
     */
    Instruction(final CommandWord command, final Integer[] integers) {
    	super();
    	this.commandWord = command;
    	this.commandParameter = integers;
    	this.sourceLineNumber = 0;
	}

    /**
     * Parse a source code line.
     *
     * @param line the line
     */
    private void parseLine(final String line) 
        throws IllegalInstructionException {
        final String[] lineParts = line.split("[\\s]");
        if (lineParts.length > 0) {
            try {
                this.commandWord = 
                    CommandWord.valueOf(lineParts[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalInstructionException
                    ("Unrecognized command: " + lineParts[0], e);
            }
        }

        if (this.commandWord == null) {
            throw new IllegalInstructionException
                ("Unrecognized command: " + line);
        }

        if (lineParts.length > this.commandWord.getNumberOfParameters()) {
            this.commandParameter = 
                new Integer[this.commandWord.getNumberOfParameters()];
            try {
                for (int i = 0; i < this.commandWord.getNumberOfParameters(); 
                     i++) {
                    this.commandParameter[i] = 
                        Integer.parseInt(lineParts[i + 1]);
                }
            } catch (NumberFormatException e) {
                throw new IllegalInstructionException
                    ("Parameter not a number. Command line: " + line, e);
            }

        } else if (this.commandWord != null && this.
                   commandWord.getNumberOfParameters() > 0) {
            throw new IllegalInstructionException
                ("Mandatory parameter not given. Line " + line);
        }
    }

    /**
     * Get a parameter.
     *
     * @param index the index of the parameter
     * @return the parameter
     */
    public Integer getCommandParameter(int index) {
        return this.commandParameter.length > index ? 
            this.commandParameter[index] : null;
    }

    /**
     * Get the command word
     *
     * @return the command word
     */
    public CommandWord getCommandWord() {
        return commandWord;
    }

    /**
     * Get the source code line
     *
     * @return the line number
     */
    public int getSourceLine() {
        return this.sourceLineNumber;
    }

    /**
     * Return a textual representation of this instruction.
     *
     * @return the textual representation
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.commandWord);
        if (this.commandParameter.length > 0) {
            builder.append(" ");
            builder.append(this.commandParameter[0]);
            for (int i = 1; i < this.commandWord.getNumberOfParameters(); i++) {
                builder.append(" ");
                builder.append(this.commandParameter[i]);
            }
        }

        return builder.toString();
    }

}
