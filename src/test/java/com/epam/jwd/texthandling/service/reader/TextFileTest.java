package com.epam.jwd.texthandling.service.reader;

import com.epam.jwd.texthandling.exception.FileReadException;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

public class TextFileTest {

    private final TextFile textFile = TextFile.getInstance();

    @Test
    public void getInstance_shouldReturnInstanceOfClass_always() {
        assertNotNull(TextFile.getInstance());
        assertSame(TextFile.getInstance(), TextFile.getInstance());
    }

    @Test
    public void read_shouldReturnString_whenFilepathIsValid() {
        String text = "    It has survived - not only (five) centuries, but also the leap into 13<<2 electronic\n" +
                      "typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularized in the\n" +
                      "5(1&2&(3|(4&(^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum\n" +
                      "passages, and more recently with desktop publishing software like Aldus PageMaker including\n" +
                      "versions of Lorem Ipsum.\n" +
                      "    It is a long-established fact that a reader will be distracted by the readable content of a\n" +
                      "page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78\n" +
                      "Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content\n" +
                      "here), content here', making it look like readable English.\n" +
                      "    It is a (^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when\n" +
                      "looking at its layout...\n" +
                      "    Bye.\n";

        assertNotNull(textFile.read("src\\test\\resources\\text.txt"));
        assertEquals(textFile.read("src\\test\\resources\\text.txt"), text);
    }

    @Test(expectedExceptions = FileReadException.class)
    public void read_shouldThrowFileReadException_whenFilepathIsInvalid() {
        textFile.read("src\\test\\resources\\text45.txt");
    }

    @Test
    public void write_shouldWriteTextIntoSpecifiedFile_whenFilepathIsValid() {
        String text = "Hello, Jimmy! How are you?";
        textFile.write("src\\test\\resources\\written.txt", text);
        new File("src\\test\\resources\\written.txt").delete();
    }
}