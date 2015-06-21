import fi.tkk.cs.tkkcc.SlxCompiler;
import fi.tkk.cs.tkkcc.slx.*;
import java.io.*;

public class Compiler implements SlxCompiler {
    private Scanner scanner;
    private Parser parser;

    public static void main(String[] args) {
        String input_file_path = args[0];
        Compiler compiler = new Compiler();
        SlxProgram program = compiler.compile(input_file_path);
        String output;
        String filename = Compiler.parseFilename(input_file_path);

        if (program != null && !compiler.isErrors()) {
            // System.out.println("No errors found, running the program");
            System.out.println("No errors found, writing the program to .slx file");

            output = program.toString();
            try {
                System.out.println("trying");
                PrintWriter writer = new PrintWriter("slx/" + input_file_path + ".slx", "UTF-8");
                writer.print(output);
                writer.close();
            } catch (IOException ex) {
                System.out.println("writer error");
            }

            // Run the program
        } else {
            System.out.println("Errors in the compiler");
        }

        return;
    }

    public Compiler() {
    }

    public static String parseFilename(String filepath) {
        // e.g. filepath = "./easy/test/assignment.tst"
        String[] parts;
        String   filename;

        parts = filepath.split("/"); // parts = [".", "easy", "test", "assignment.tst"]
        filename = parts[parts.length - 1]; // filename = "assignment.tst"
        parts = filename.split("\\."); // parts = ["assignment", "tst"]
        filename = parts[0]; // filename = "assignment"

        return filename;
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
        this.parser.slx = new SlxProgram();
        this.parser.Parse();
        return parser.slx;
    }
}
