package com.acme.edu.command;

import com.acme.edu.saver.ConsoleLoggerSaver;
import com.acme.edu.saver.Saver;

import java.io.IOException;

public class IntMas4Command implements Command {
    private int[][][][] message ;
    private Saver saver = null;
    private Command prevCommand = null ;

    public IntMas4Command(int[][][][] message) {
        this.message = message;
    }

    @Override
    public void accumulate(Command command, Saver saver) throws IOException {
        this.saver = saver;
        if (command instanceof NoneCommand) {
            prevCommand = this;
        } else {
            prevCommand = command;
            flush();
        }
    }

    public String messageDecorate() {
        String str= "primitives multimatrix: {"+System.lineSeparator();
        for(int[][][] i:message) {
            str=str+"{"+System.lineSeparator();
            for(int[][] j:i) {
                str=str+"{"+System.lineSeparator();
                for(int[] k:j){
                    str=str+"{";
                    for(int m:k){
                        str += m + ", ";
                    }
                    if(k.length>0)
                        str=str.substring(0,str.length()-2)+"}"+System.lineSeparator();
                    else
                        str+="}"+System.lineSeparator();
                }
                str+="}"+System.lineSeparator();
            }
            str+="}"+System.lineSeparator();
        }
        str+="}";
        return str;
    }

    @Override
    public void flush() throws IOException {
        saver.save(prevCommand.messageDecorate());
        prevCommand = this;
    }

    @Override
    public Command getPrevCommand() {
        return prevCommand;
    }

}
