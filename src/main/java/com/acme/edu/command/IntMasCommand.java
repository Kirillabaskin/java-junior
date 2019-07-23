package com.acme.edu.command;

import com.acme.edu.Type;
import com.acme.edu.saver.ConsoleLoggerSaver;

public class IntMasCommand implements Command {
    private int[] message ;
    private ConsoleLoggerSaver saver = null;
    private Command prevCommand = null ;

    public IntMasCommand(int[] message) {
        this.message = message;
    }

    @Override
    public void accumulate(Command command, ConsoleLoggerSaver saver) {
        this.saver = saver;
        if (command instanceof NoneCommand) {
            prevCommand = this;
        } else {
            prevCommand = command;
            flush();
        }
    }

    public String messageDecorate() {
        String str= "primitives array: {";
        for(int i:message)
            str+=i+", ";
        str=str.substring(0,str.length()-2)+"}";
        return str;
    }

    @Override
    public void flush() {
        saver.save(prevCommand.messageDecorate());
        prevCommand = this;
    }

    @Override
    public Command getPrevCommand() {
        return prevCommand;
    }
}
