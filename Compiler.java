import fi.tkk.cs.tkkcc.SlxCompiler;
import fi.tkk.cs.tkkcc.slx.*;
import java.io.*;

public class Compiler implements SlxCompiler {
    private Scanner scanner;
    private Parser parser;

    public Compiler() {}

    @Override
    public boolean isErrors() {
        return this.parser.errors.count > 0;
    }

    @Override
    public SlxProgram compile(String source_file) {
        this.scanner = new Scanner(source_file);
        this.parser = new Parser(this.scanner);
        this.parser.st = new SymbolTable(this.parser);
        this.parser.gen = new CodeGenerator();
        this.parser.Parse();
        return parser.gen.getProgram();
    }

    public static void main(String[] args) {
        String input_file_path = args[0];
        Compiler compiler = new Compiler();
        SlxProgram program = compiler.compile(input_file_path);
        String output;

        if (program != null && !compiler.isErrors()) {
            System.out.println("No errors found, writing the program to .slx file");

            output = program.toString();
            try {
                PrintWriter writer = new PrintWriter("out.slx", "UTF-8");
                writer.print(output);
                writer.close();
            } catch (IOException ex) {
                System.out.println("writer error");
            }
        } else {
            System.out.println("Errors in the compiler");
        }

        return;
    }

}
