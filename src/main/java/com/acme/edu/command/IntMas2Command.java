package com.acme.edu.command;

import com.acme.edu.Type;

public class IntMas2Command implements Command {
    private int[][] message;
    private Type CURRENT_TYPE = Type.INT_MAS2;

    public IntMas2Command(int[][] message) {
        this.message = message;
    }

    public Type getType() {
        return CURRENT_TYPE;
    }

    public String messageDecorate() {
        String str= "primitives matrix: {"+System.lineSeparator();
        for(int[] i:message) {
            str=str+"{";
            for(int j:i) {
                str += j + ", ";
            }
            if(i.length>0)
            str=str.substring(0,str.length()-2)+"}"+System.lineSeparator();
        }
        str+="}";
        return str;
    }

    public int[][] getIntMasValue() {
        return message;
    }

    @Override
    public boolean accumulate(Command accumulator) {
        return false;
    }
}
