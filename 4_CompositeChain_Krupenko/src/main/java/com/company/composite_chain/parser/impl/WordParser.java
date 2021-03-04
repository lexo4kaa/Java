package com.company.composite_chain.parser.impl;

import com.company.composite_chain.composite.Symbol;
import com.company.composite_chain.composite.TextComponent;
import com.company.composite_chain.composite.TextComposite;
import com.company.composite_chain.composite.TextType;
import com.company.composite_chain.parser.BaseParser;

public class WordParser implements BaseParser {
    private static final WordParser INSTANCE = new WordParser();

    private WordParser(){}

    public static WordParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String word) {
        TextComponent wordComponent = new TextComposite(TextType.WORD);
        char[] symbols = word.toCharArray();
        Symbol symbolComponent;
        for (char symbol : symbols) {
            symbolComponent = new Symbol(symbol);
            wordComponent.add(symbolComponent);
        }
        return wordComponent;
    }
}
