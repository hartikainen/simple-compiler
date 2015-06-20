import fi.tkk.cs.tkkcc.SlxCompiler;
import fi.tkk.cs.tkkcc.slx.*;

// import mycompiler.SymbolTable;

// import Parser;
// import Scanner;

public class Compiler implements SlxCompiler {
    private Scanner scanner;
    private Parser parser;

    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        SlxProgram program = compiler.compile(args[0]);

        System.out.println("compiler.isErrors: " + (compiler.isErrors()));
        if (program != null && !compiler.isErrors()) {
            // Run the program
        }
        return;
    }

    public Compiler() {
        return;
    }

    @Override
    public boolean isErrors() {
        return this.parser.errors.count > 0;
    }

    @Override
    public SlxProgram compile(String sourceFilename) {
        this.scanner = new Scanner(sourceFilename);
        this.parser = new Parser(this.scanner,  new Printer(false));
        this.parser.st = new SymbolTable(this.parser);
        this.parser.Parse();
        return null;
    }
}
