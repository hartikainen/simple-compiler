import fi.tkk.cs.tkkcc.slx.*;

public class CodeGenerator {
    private int label_counter;
    private SlxProgram slx;

    public CodeGenerator() {
        this.label_counter = 0;
        this.slx = new SlxProgram();
    }

    public void emit(String word) {
        this.slx.emit(CommandWord.valueOf(word));
    }

    public void emit(String word, int arg1) {
        this.slx.emit(CommandWord.valueOf(word), arg1);
    }

    public void emit(String word, int arg1, int arg2) {
        this.slx.emit(CommandWord.valueOf(word), arg1, arg2);
    }

    public int newLabel() {
        return this.label_counter++;
    }

    public SlxProgram getProgram() {
        return this.slx;
    }
}
