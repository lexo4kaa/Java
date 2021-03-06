package com.company.composite_chain.action;

import com.company.composite_chain.composite.TextComponent;
import com.company.composite_chain.composite.TextComposite;
import com.company.composite_chain.composite.TextType;
import com.company.composite_chain.exception.CustomerException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import static com.company.composite_chain.reader.CustomerFileReader.logger;

public class TextAction {
    public TextComponent sortParagraphsBySentences(TextComponent text) throws CustomerException {
        if (text == null || text.getTextType() != TextType.TEXT) {
            logger.error("text is null or not text: " + text);
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
        for (TextComponent paragraph : paragraphs) {
            resultText.add(paragraph);
        }
        return resultText;
    }

    public int compareComponents(TextComponent component1, TextComponent component2) {
        return Integer.compare(component1.getAll().size(), component2.getAll().size());
    }

    public List<TextComponent> getSentencesWithLongestWord(TextComponent text) throws CustomerException {
        if (text == null || text.getTextType() != TextType.TEXT) {
            logger.error("text is null or not text: " + text);
            throw new CustomerException("text is null or not text: " + text);
        }
        int maxLength = findMaxLengthOfWords(text);
        List<TextComponent> listWithLongestWords = new ArrayList<>();
        for (TextComponent paragraph : text.getAll()) {
            for (TextComponent sentence : paragraph.getAll()) {
                for (TextComponent lexeme : sentence.getAll()) {
                    for (TextComponent word : lexeme.getAll()) {
                        int wordLength = 0;
                        for (TextComponent symbol : word.getAll()) {
                            wordLength++;
                        }
                        if (wordLength == maxLength && !listWithLongestWords.contains(sentence)) {
                            listWithLongestWords.add(sentence);
                        }
                    }
                }
            }
        }
        return listWithLongestWords;
    }

    public int findMaxLengthOfWords(TextComponent text) throws CustomerException {
        if (text == null || text.getTextType() != TextType.TEXT) {
            logger.error("text is null or not text: " + text);
            throw new CustomerException("text is null or not text: " + text);
        }
        int maxLength = 1;
        for (TextComponent paragraph : text.getAll()) {
            for (TextComponent sentence : paragraph.getAll()) {
                for (TextComponent lexeme : sentence.getAll()) {
                    for (TextComponent word : lexeme.getAll()) {
                        int wordLength = 0;
                        for (TextComponent symbol : word.getAll()) {
                            wordLength++;
                        }
                        if (wordLength > maxLength) {
                            maxLength = wordLength;
                        }
                    }
                }
            }
        }
        return maxLength;
    }

    public TextComponent deleteSentencesWithWordsLessThanNumber(TextComponent text, int value) throws CustomerException {
        if (text == null || text.getTextType() != TextType.TEXT) {
            logger.error("text is null or not text: " + text);
            throw new CustomerException("text is null or not text: " + text);
        }
        for (TextComponent paragraph : text.getAll()) {
            List<TextComponent> sentences = paragraph.getAll();
            for (int i = 0; i < sentences.size(); i++) {
                TextComponent sentence = sentences.get(i);
                if (sentence.getAll().size() < value) {
                    paragraph.remove(sentence);
                }
            }
        }
        return text;
    }

    public Map<String, Integer> findWordsAndTheirCount(TextComponent text) throws CustomerException {
        if (text == null || text.getTextType() != TextType.TEXT) {
            logger.error("text is null or not text: " + text);
            throw new CustomerException("text is null or not text: " + text);
        }
        Map<String, Integer> wordsAndTheirCount = new HashMap<>();
        for (TextComponent paragraph : text.getAll()) {
            for (TextComponent sentence : paragraph.getAll()) {
                for (TextComponent lexeme : sentence.getAll()) {
                    for (TextComponent word : lexeme.getAll()) {
                        String lowerCaseWord = word.toString().toLowerCase();
                        if(wordsAndTheirCount.containsKey(lowerCaseWord)) {
                            int count = wordsAndTheirCount.get(lowerCaseWord);
                            wordsAndTheirCount.put(lowerCaseWord, count + 1);
                        } else {
                            wordsAndTheirCount.put(lowerCaseWord, 1);
                        }
                    }
                }
            }
        }
        return wordsAndTheirCount;
    }
}
