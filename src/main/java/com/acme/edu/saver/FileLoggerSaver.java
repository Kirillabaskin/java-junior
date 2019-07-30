package com.acme.edu.saver;

import java.io.*;

public class FileLoggerSaver extends Saver {
    private File file;
    private BufferedWriter out;

    public FileLoggerSaver(String path) throws IOException {
        this.file = new File(path);
        out = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file,true)), "UTF-16"));
    }

    @Override
    public void save(String message) throws IOException {
        out.write(message + System.lineSeparator());
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.flush();
        out.close();
    }
}
