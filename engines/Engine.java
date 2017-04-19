package engines;

import commands.Executable;
import interpreters.Interpreter;
import io.Reader;
import io.Writer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {
    private Reader reader;
    private Writer writer;
    private Interpreter interpreter;

    public Engine(Reader reader, Writer writer, Interpreter interpreter) {
        this.reader = reader;
        this.writer = writer;
        this.interpreter = interpreter;
    }

    @Override
    public void run() {

        String line;
        try {
            while (!"EmergencyBreak".equals(line = reader.read())){
                Executable executable = this.interpreter.interpretCommand(line);
                String result = executable.execute();
                this.writer.write(result);
            }
        } catch (IOException |
                InstantiationException |
                InvocationTargetException |
                IllegalAccessException |
                NoSuchMethodException |
                ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
