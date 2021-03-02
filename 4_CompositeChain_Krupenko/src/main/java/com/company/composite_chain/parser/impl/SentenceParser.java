package com.company.composite_chain.parser.impl;

import com.company.composite_chain.composite.PartType;
import com.company.composite_chain.composite.TextComponent;
import com.company.composite_chain.composite.TextComposite;
import com.company.composite_chain.parser.BaseParser;

public class SentenceParser implements BaseParser {
    private static final SentenceParser INSTANCE = new SentenceParser();
    private static final String SENTENCE_DELIMITER = "\\s+";
    private LexemeParser lexemeParser = LexemeParser.getInstance();

    private SentenceParser(){}

    public static SentenceParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String sentence) {
        TextComponent sentenceComponent = new TextComposite(PartType.SENTENCE);
        String[] lexemes = sentence.split(SENTENCE_DELIMITER);
        for (String lexeme : lexemes) {
            TextComponent lexemeComponent = lexemeParser.parse(lexeme);
            sentenceComponent.add(lexemeComponent);
        }
        return sentenceComponent;
    }
}
