package interpreters;

import annotations.InjectArgs;
import annotations.InjectType;
import commands.Executable;
import core.EmergencyManagementSystem;
import core.ManagementSystem;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreter implements Interpreter{
    private static final String COMMAND_SUFFIX = "Command";
    private static final String COMMANDS_PACKAGE = "commands.";

    private ManagementSystem system;

    public CommandInterpreter(ManagementSystem system) {
        this.system = system;
    }


    @Override
    public Executable interpretCommand(String line) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException,
            InstantiationException {


        String[] tokens = line.split("\\|");
        String command = tokens[0];

        Class<Executable> commandClass = (Class<Executable>) Class.forName(COMMANDS_PACKAGE + command + COMMAND_SUFFIX);
        Constructor<Executable> constructor = commandClass.getConstructor(ManagementSystem.class);
        Executable executable = constructor.newInstance(this.system);

        this.injectDependencies(executable, tokens);

        return executable;
    }

    private void injectDependencies(Executable executable, String[] tokens) throws IllegalAccessException {
        Field[] fields = executable.getClass().getDeclaredFields();

        for (Field field: fields) {
            if(field.isAnnotationPresent(InjectArgs.class)){
                field.setAccessible(true);
                field.set(executable, tokens);
            }else if(field.isAnnotationPresent(InjectType.class)){
                field.setAccessible(true);
                field.set(executable,tokens[1]);
            }
        }

    }
}
