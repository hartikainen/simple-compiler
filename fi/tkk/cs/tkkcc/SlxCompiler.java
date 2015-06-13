package fi.tkk.cs.tkkcc;

import fi.tkk.cs.tkkcc.slx.SlxProgram;

/**
 * Interface for TKKCC compilers.
 * 
 * Before the first call to compile method, the presence of errors is
 * not defined. After that the isErrors method refers to the latest
 * compile call.
 * 
 * @author Timo Montonen
 * @version 1.0
 */
public interface SlxCompiler {
    /**
     * Did compiler found any errors on compilation unit?
     * 
     * @return <code>true</code> if Compiler found errors during the 
     *         compilation.
     */
    boolean isErrors();
    
    /**
     * Compile source file to SLX program.
     * 
     * @param sourceFilename Filename of the program to be compiled.
     * @return Instance of compiled SlxProgram.
     */
    SlxProgram compile(String sourceFilename);
}
