package com.acme.edu.command;

import com.acme.edu.saver.ConsoleLoggerSaver;

public class ByteCommand implements Command {
    private byte message = 0;
    private ConsoleLoggerSaver saver = null;
    private Command prevCommand = null;

    public ByteCommand(byte message) {
        this.message = message;
    }

    @Override
    public void accumulate(Command command, ConsoleLoggerSaver saver) {
        this.saver = saver;
        if (command instanceof NoneCommand) {
            prevCommand = this;
        } else if (isAccumulative(command)) {
            message = (byte) (message + ((ByteCommand) command).message);
            prevCommand = this;
        } else {
            prevCommand = command;
            flush();
        }

    }

    private boolean isAccumulative(Command command) {
        if (command instanceof ByteCommand) {
            int byteBuff = ((ByteCommand) command).message;
            if (byteBuff > 0 && (Byte.MAX_VALUE - byteBuff < message)) return false;
            if (byteBuff <= 0 && (Byte.MIN_VALUE - byteBuff > message)) return false;
            return true;
        }
        return false;
    }

    public String messageDecorate() {
        return String.valueOf(message);
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
