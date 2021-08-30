package com.epam.jwd.texthandling.builder;

import com.epam.jwd.texthandling.model.Paragraph;
import com.epam.jwd.texthandling.model.Sentence;
import com.epam.jwd.texthandling.model.Text;
import com.epam.jwd.texthandling.model.TextPart;
import com.epam.jwd.texthandling.model.Word;

import java.util.List;

public class TextBuilder {

    // contains only 3 white spaces because word will produce another one
    private static final String FIRST_PARAGRAPH_TABULATION = "   ";
    private static final String NEW_PARAGRAPH_TABULATION = "\n   ";
    private static final String WHITE_SPACE = " ";

    public static String build(Text text) {
        StringBuilder sb = new StringBuilder();

        sb.append(FIRST_PARAGRAPH_TABULATION);
        buildFromParts(text.getParts(), sb);

        return sb.toString();
    }

    private static void buildFromParts(List<TextPart> textParts, StringBuilder sb) {
        for (TextPart textPart : textParts) {
            if (textPart instanceof Word) { // add white space and a word
                sb.append(WHITE_SPACE);
                sb.append(((Word) textPart).getWord());
            } else {
                buildFromParts(textPart.getParts(), sb); // recursive search of words (due to text classes structure)
            }
            if (textPart instanceof Sentence) { // add ending of a sentence
                sb.append(((Sentence) textPart).getSentenceEnding());
            } else if (textPart instanceof Paragraph) { // add new paragraph tabulation
                sb.append(NEW_PARAGRAPH_TABULATION);
            }
        }
    }
}
