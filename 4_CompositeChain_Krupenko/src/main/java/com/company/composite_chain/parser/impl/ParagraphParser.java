package com.company.composite_chain.parser.impl;

import com.company.composite_chain.composite.TextType;
import com.company.composite_chain.composite.TextComponent;
import com.company.composite_chain.composite.TextComposite;
import com.company.composite_chain.parser.BaseParser;

public class ParagraphParser implements BaseParser {
    private static final ParagraphParser INSTANCE = new ParagraphParser();
    private static final String PARAGRAPH_DELIMITER = "(?<=([.!?.{3}]\\s))";
    private SentenceParser sentenceParser = SentenceParser.getInstance();

    private ParagraphParser(){}

    public static ParagraphParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String paragraph) {
        TextComponent paragraphComponent = new TextComposite(TextType.PARAGRAPH);
        String[] sentences = paragraph.split(PARAGRAPH_DELIMITER);
        for (String sentence : sentences) {
            TextComponent sentenceComponent = sentenceParser.parse(sentence);
            paragraphComponent.add(sentenceComponent);
        }
        return paragraphComponent;
    }
}
