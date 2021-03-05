package com.company.composite_chain.action;

import com.company.composite_chain.composite.TextComponent;
import com.company.composite_chain.composite.TextComposite;
import com.company.composite_chain.composite.TextType;
import com.company.composite_chain.exception.CustomerException;
import com.company.composite_chain.parser.impl.TextParser;
import com.company.composite_chain.reader.CustomerFileReader;

import java.util.List;
import java.util.Map;

import static com.company.composite_chain.reader.CustomerFileReader.logger;

public class TextAction {
    public TextComponent sortParagraphsBySentences(TextComponent text) throws CustomerException {
        if (text == null || text.getTextType() != TextType.TEXT) {
            logger.error("text is null or not text", text);
            throw new CustomerException("text is null or not text: " + text);
        }
        List<TextComponent> paragraphs = text.getAll();
        int length = paragraphs.size();
        for (int i = 1; i < length; i++) {
            TextComponent current = paragraphs.get(i);
            int j = i;
            while(j > 0 && compareComponents(current, paragraphs.get(j - 1)) < 0) {
                TextComponent element = paragraphs.get(j - 1);
                paragraphs.set(j, element);
                j--;
            }
            paragraphs.set(j, current);
        }
        TextComponent resultText = new TextComposite(TextType.TEXT);
        for (int k = 0; k < length; k++) {
            resultText.add(paragraphs.get(k));
        }
        return resultText;
    }

    public int compareComponents(TextComponent component1, TextComponent component2) {
        return Integer.compare(component1.getAll().size(), component2.getAll().size());
    }

    public List<TextComponent> getSentencesWithLongestWord(TextComposite text) {
        return null;
    }

    public void deleteSentencesWithWordsLessNumber(TextComposite text, int value) {

    }

    public Map<String, Integer> findWordsAndCountThem(TextComposite text) {
        return null;
    }

    public static void main(String[] args) throws CustomerException {
        TextAction action = new TextAction();
        TextParser parser = TextParser.getInstance();
        System.out.println(action.sortParagraphsBySentences(
                parser.parse(CustomerFileReader.fileReading("src/main/resources/text.txt"))).toString());
    }
}
