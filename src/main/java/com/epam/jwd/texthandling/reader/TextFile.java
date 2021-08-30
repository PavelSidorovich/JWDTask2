package com.epam.jwd.texthandling.reader;

import com.epam.jwd.texthandling.exception.FileReadException;
import com.epam.jwd.texthandling.exception.FileWriteException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TextFile {

    private TextFile() {
    }

    private static final String NEW_LINE = "\n";

    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(
                new File(fileName).getAbsoluteFile()))) {
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s);
                sb.append(NEW_LINE);
            }
        } catch (IOException e) {
            throw new FileReadException(e);
        }
        return sb.toString();
    }

    public static void write(String fileName, String text) {
        try (PrintWriter out = new PrintWriter(
                new File(fileName).getAbsoluteFile())) {
            out.print(text);
        } catch (IOException e) {
            throw new FileWriteException(e);
        }
    }
}
