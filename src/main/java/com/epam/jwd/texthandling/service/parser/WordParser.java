package com.epam.jwd.texthandling.parser;

import com.epam.jwd.texthandling.interpreter.BitExpressionCalculator;
import com.epam.jwd.texthandling.model.Symbol;
import com.epam.jwd.texthandling.model.TextComponent;
import com.epam.jwd.texthandling.model.Word;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordParser extends TextParser {

    private static final Logger LOG = LogManager.getLogger(WordParser.class);

    private static final String WORD_REGEX = "\\s";
    private static final String WORD_IS_WORKING_MSG = String.format("%s is working", WordParser.class);
    private static final String WORD_WITH_SYMBOLS_REGEX = "(.+)([,:])?";
    private static final String OPERATOR_REGEX = "[~|&^]|[<]{2}|[>]{2}";

    private static WordParser instance;

    private WordParser() {
    }

    public static WordParser getInstance() {
        if (instance == null) {
            instance = new WordParser();
        }
        return instance;
    }

    @Override
    public List<TextComponent> parse(String text) {
        LOG.trace(WORD_IS_WORKING_MSG);

        String[] words = text.split(WORD_REGEX);
        List<TextComponent> wordList = new ArrayList<>();

        for (String word : words) {
            Optional<Integer> bitExpressionResult = countTheBitExpression(word);

            if (bitExpressionResult.isPresent()) {
                word = bitExpressionResult.get().toString();
            }

            Optional<String> symbolAfterWord = getSymbolAfterWord(word);
            word = getWordWithoutSymbols(word);
            wordList.add(new Word(word));
            symbolAfterWord.ifPresent(s -> wordList.add(new Symbol(s)));
        }
        return wordList.stream()
                       .filter(word -> word.getLexemeLength() > 0)
                       .collect(Collectors.toList());
    }

    private Optional<String> getSymbolAfterWord(String word) {
        Matcher matcher = Pattern.compile(WORD_WITH_SYMBOLS_REGEX).matcher(word);

        if (matcher.find()) {
            return Optional.ofNullable(matcher.group(2)); // extract symbol
        }
        return Optional.empty();
    }

    private String getWordWithoutSymbols(String word) {
        Matcher matcher = Pattern.compile(WORD_WITH_SYMBOLS_REGEX).matcher(word);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return word;
    }

    private Optional<Integer> countTheBitExpression(String word) {
        Matcher matcher = Pattern.compile(OPERATOR_REGEX).matcher(word);
        if (matcher.find()) {
            return BitExpressionCalculator.calculate(word);
        }
        return Optional.empty();
    }
}
