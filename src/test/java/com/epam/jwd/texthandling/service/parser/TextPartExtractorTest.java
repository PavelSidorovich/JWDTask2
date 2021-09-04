package com.epam.jwd.texthandling.parser;

import com.epam.jwd.texthandling.model.Symbol;
import com.epam.jwd.texthandling.model.TextComponent;
import com.epam.jwd.texthandling.model.TextComposite;
import com.epam.jwd.texthandling.model.TextPart;
import com.epam.jwd.texthandling.model.Word;
import com.epam.jwd.texthandling.service.parser.TextParser;
import com.epam.jwd.texthandling.service.parser.TextParserFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class TextPartExtractorTest {

    @DataProvider(name = "TextPartProvider")
    public Object[][] getTextPartsFromProvider() {
        List<TextComponent> symbols = new ArrayList<>();
        symbols.add(new Symbol("."));
        symbols.add(new Symbol("!"));

        List<TextComponent> words = new ArrayList<>();
        List<TextComponent> words1 = new ArrayList<>();
        List<TextComponent> words2 = new ArrayList<>();
        words1.add(new Word("It"));
        words1.add(new Word("is"));
        words1.add(new Word("a"));
        words1.add(new Word("text"));
        words1.add(new Symbol("."));
        words2.add(new Word("Bye"));
        words2.add(new Symbol("!"));
        words.addAll(words1);
        words.addAll(words2);

        List<TextComponent> sentences = new ArrayList<>();
        sentences.add(new TextComposite(words1, TextPart.SENTENCE));
        sentences.add(new TextComposite(words2, TextPart.SENTENCE));

        List<TextComponent> paragraphs = new ArrayList<>();
        paragraphs.add(new TextComposite(sentences, TextPart.PARAGRAPH));

        return new Object[][] {
                { paragraphs, TextPart.PARAGRAPH },
                { sentences, TextPart.SENTENCE },
                { words, TextPart.WORD },
                { symbols, TextPart.SYMBOL },
        };
    }

    @Test(dataProvider = "TextPartProvider")
    public void extractParts(List<TextComponent> components, TextPart type) {
        String text = "It is a text. Bye!";
        TextParserFactory factory = TextParserFactory.getInstance();
        TextParser parser = factory.of();
        TextComposite allText = new TextComposite(parser.parse(text), TextPart.TEXT);

        assertNotNull(TextPartExtractor.extractParts(allText, type));
        assertTrue(TextPartExtractor.extractParts(allText, type).size() > 0);
        assertEquals(TextPartExtractor.extractParts(allText, type), components);
        assertSame(TextPartExtractor.extractParts(allText, type).get(0).getType(), type);
    }
}