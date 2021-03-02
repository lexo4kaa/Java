package com.company.composite_chain.parser.impl;

import com.company.composite_chain.composite.PartType;
import com.company.composite_chain.composite.TextComponent;
import com.company.composite_chain.composite.TextComposite;
import com.company.composite_chain.parser.BaseParser;

public class LexemeParser implements BaseParser {
    private static final LexemeParser INSTANCE = new LexemeParser();
    private static final String LEXEME_SPLIT_REGEX = "\\W";
    private WordParser wordParser = WordParser.getInstance();

    private LexemeParser(){}

    public static LexemeParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String lexeme) {
        TextComponent lexemeComponent = new TextComposite(PartType.LEXEME);
        String[] words = lexeme.split(LEXEME_SPLIT_REGEX);
        for (String word : words) {
            TextComponent wordComponent = wordParser.parse(word);
            lexemeComponent.add(wordComponent);
        }
        return lexemeComponent;
    }
}
