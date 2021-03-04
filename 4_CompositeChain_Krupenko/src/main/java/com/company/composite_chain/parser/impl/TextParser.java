package com.company.composite_chain.parser.impl;

import com.company.composite_chain.composite.Symbol;
import com.company.composite_chain.composite.TextComponent;
import com.company.composite_chain.composite.TextComposite;
import com.company.composite_chain.composite.TextType;
import com.company.composite_chain.exception.CustomerException;
import com.company.composite_chain.parser.BaseParser;
import com.company.composite_chain.reader.CustomerFileReader;

public class TextParser implements BaseParser {
    private static final TextParser INSTANCE = new TextParser();
    private static final String TEXT_DELIMITER = "\n";
    private ParagraphParser paragraphParser = ParagraphParser.getInstance();

    private TextParser() {
    }

    public static TextParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String text) {
        TextComponent textComponent = new TextComposite(TextType.TEXT);
        String[] paragraphs = text.split(TEXT_DELIMITER);
        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = paragraphParser.parse(paragraph);
            textComponent.add(paragraphComponent);
        }
        return textComponent;
    }
    /*
    public static void main(String[] args) throws CustomerException {
        TextParser parser = TextParser.getInstance();
        System.out.println(parser.parse(CustomerFileReader.fileReading("src/main/resources/text.txt")));
    }
    */
}