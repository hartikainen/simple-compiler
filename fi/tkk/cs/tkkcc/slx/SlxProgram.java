package fi.tkk.cs.tkkcc.slx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SLX program.
 * 
 * This class may be used in a compiler that generates SLX programs.
 * 
 * @author Timo Montonen
 */
public final class SlxProgram {
    /** The instructions of the program */
    private final List<Instruction> program;
    /** Function labels of the program */
    private final Map<Integer, Integer> labelMap;
    
    /**
     * Initialize a new SLX program.
     */
    public SlxProgram() {
        super();
        this.program = new ArrayList<Instruction>();
        this.labelMap = new HashMap<Integer, Integer>();
    }
    
    /**
     * Emit command word without parameters.
     * 
     * @param command Emitted command.
     */
    public void emit(final CommandWord command) {
        if (command.getNumberOfParameters() != 0) {
            throw new IllegalArgumentException
                ("Wrong number of parameters for " + command
                 + ", number of required parameters: " + 
                 command.getNumberOfParameters());
        }
        this.program.add(new Instruction(command, new Integer[] {}));
    }
    
    /**
     * Emit command word with one parameter.
     * 
     * @param command Emitted command.
     * @param param1 Command's only parameter.
     */
    public void emit(final CommandWord command, final int param1) {
        if (command.getNumberOfParameters() != 1) {
            throw new IllegalArgumentException
                ("Wrong number of parameters for " + command
                 + ", number of required parameters: " + 
                 command.getNumberOfParameters());
        }
        this.program.add(new Instruction(command, new Integer[] { param1 }));

        // Labels have to be stored for later use
        if (command.equals(CommandWord.LAB)) {
            // Add labels also to label -> pc map
            this.labelMap.put(param1, new Integer(this.program.size() - 1));
        }
        
    }

    /**
     * Emit command word with two parameters.
     * 
     * @param command Emitted command
     * @param param1 First parameter
     * @param param2 Second parameter
     */
    public void emit(final CommandWord command, final int param1, 
                     final int param2) {
        if (command.getNumberOfParameters() != 2) {
            throw new IllegalArgumentException
                ("Wrong number of parameters for " + command
                 + ", number of required parameters: " + 
                 command.getNumberOfParameters());
        }
        
        this.program.add(new Instruction(command, 
                                         new Integer[] { param1, param2 }));
    }

    /**
     * Get a textual representation of the program.
     *
     * @return the textual representation
     */
    public String toString() {
        String str = this.program.toString();
        str = str.substring(1, str.length() - 1);
        str = str.replaceAll(", ", "\n");
        return str;
    }
	
    /**
     * Get the instructions of this program.
     *
     * @return the instructions
     */
    public List<Instruction> getProgram() {
        return this.program;
    }

    /**
     * Get the label -&gt; pc mapping of this program.
     *
     * @return the mapping
     */
    public Map<Integer, Integer> getLabelMap() {
        return labelMap;
    }
}
