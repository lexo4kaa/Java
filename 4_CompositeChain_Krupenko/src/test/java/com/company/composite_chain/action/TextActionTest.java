package com.company.composite_chain.action;

import com.company.composite_chain.composite.TextComponent;
import com.company.composite_chain.exception.CustomerException;
import com.company.composite_chain.parser.impl.TextParser;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextActionTest {
    TextAction action;
    @BeforeClass
    public void setUp() {
        action = new TextAction();
    }

    @Test(dataProvider = "sort paragraphs")
    public void sortParagraphsBySentencesTest(TextComponent textComponent, TextComponent expected) throws CustomerException {
        TextComponent actual = action.sortParagraphsBySentences(textComponent);
        Assert.assertEquals(actual.toString(), expected.toString());
    }

    @Test(dataProvider = "delete sentences")
    public void deleteSentencesWithWordsLessThanNumberTest(TextComponent textComponent, int value, TextComponent expected) throws CustomerException {
        TextComponent actual = action.deleteSentencesWithWordsLessThanNumber(textComponent, value);
        Assert.assertEquals(actual.toString(), expected.toString());
    }

    @Test(dataProvider = "longest word")
    public void getSentencesWithLongestWordTest(TextComponent textComponent, List<String> expected) throws CustomerException {
        List<TextComponent> actualList = action.getSentencesWithLongestWord(textComponent);
        List<String> actualString = new ArrayList<>();
        for(TextComponent component : actualList) {
            actualString.add(component.toString().strip());
        }
        Assert.assertEquals(actualString, expected);
    }

    @Test(dataProvider = "find words and count")
    public void findWordsAndTheirCountTest(TextComponent textComponent, Map<String, Integer> expected) throws CustomerException {
        Map<String, Integer> actual = action.findWordsAndTheirCount(textComponent);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "sort paragraphs")
    public Object[][] sortParagraphsBySentencesData(){
        TextParser textParser = TextParser.getInstance();
        String text1 = "От того порою грустно. Что в жизни бывает. Много несправедливых вещей.\n" +
                "Того кто о нас ноги вытирает... Как ни странно мы любим да еще и сильнее...\n" +
                "Ты будешь вырываться и бежать к ней - я тебя крепко свяжу.\n" +
                "Ты сколько лет доверял ей напрасно? Теперь! Братишка! Доверься.";
        String sortedText1 = "Ты будешь вырываться и бежать к ней - я тебя крепко свяжу.\n" +
                "Того кто о нас ноги вытирает... Как ни странно мы любим да еще и сильнее...\n" +
                "От того порою грустно. Что в жизни бывает. Много несправедливых вещей.\n" +
                "Ты сколько лет доверял ей напрасно? Теперь! Братишка! Доверься.";
        TextComponent expectedTextComponent1 = textParser.parse(sortedText1);
        TextComponent textComponent1 = textParser.parse(text1);

        String text2 = "Я тосковал по тебе... В минуты расставанья.\n" +
                "Ты возвращалась! Ко мне? Сквозь сны. И расстоянья.\n" +
                "Но несмотря ни на что... Пришла! Судьба-злодейка!\n" +
                "И у любви у нашей села батарейка";
        String sortedText2 = "И у любви у нашей села батарейка.\n" +
                "Я тосковал по тебе... В минуты расставанья.\n" +
                "Но несмотря ни на что... Пришла! Судьба-злодейка!\n" +
                "Ты возвращалась! Ко мне? Сквозь сны. И расстоянья.\n";
        TextComponent expectedTextComponent2 = textParser.parse(sortedText2);
        TextComponent textComponent2 = textParser.parse(text2);

        return new Object[][]{
                {textComponent1, expectedTextComponent1},
                {textComponent2, expectedTextComponent2}
        };
    }

    @DataProvider(name = "delete sentences")
    public Object[][] deleteSentencesWithWordsLessThanNumberData() {
        TextParser textParser = TextParser.getInstance();
        String text = "От того порою грустно. Что в жизни бывает. Много несправедливых вещей.\n" +
                "Того кто о нас ноги вытирает... Как ни странно мы любим да еще и сильнее...\n" +
                "Ты будешь вырываться и бежать к ней - я тебя крепко свяжу.\n" +
                "Ты сколько лет доверял ей напрасно? Теперь! Братишка! Доверься.";
        String sortedText1 = "От того порою грустно. Что в жизни бывает.\n" +
                "Того кто о нас ноги вытирает... Как ни странно мы любим да еще и сильнее...\n" +
                "Ты будешь вырываться и бежать к ней - я тебя крепко свяжу.\n" +
                "Ты сколько лет доверял ей напрасно?";
        TextComponent expectedTextComponent1 = textParser.parse(sortedText1);
        String sortedText2 = "От того порою грустно. Что в жизни бывает.\n" +
                "Того кто о нас ноги вытирает... Как ни странно мы любим да еще и сильнее...\n" +
                "Ты будешь вырываться и бежать к ней - я тебя крепко свяжу.\n" +
                "Ты сколько лет доверял ей напрасно?";
        TextComponent expectedTextComponent2 = textParser.parse(sortedText2);
        TextComponent textComponent = textParser.parse(text);

        return new Object[][]{
                {textComponent, 4, expectedTextComponent1},
                {textComponent, 3, expectedTextComponent2},
                {textComponent, 2, expectedTextComponent2},
                {textComponent, 1, textComponent}
        };
    }

    @DataProvider(name = "longest word")
    public Object[][] getSentencesWithLongestWordData(){
        TextParser textParser = TextParser.getInstance();
        String text1 = "От того порою грустно. Что в жизни бывает. Много несправедливых вещей.\n" +
                "Того кто о нас ноги вытирает... Как ни странно мы любим да еще и сильнее...\n" +
                "Ты будешь вырываться и бежать к ней - я тебя крепко свяжу.\n" +
                "Ты сколько лет доверял ей напрасно? Теперь! Братишка! Доверься.";
        List<String> expected1 = List.of("Много несправедливых вещей");
        TextComponent textComponent1 = textParser.parse(text1);

        String text2 = "Я тосковал по тебе... В минуты расставаньяя.\n" +
                "Ты возвращалась! Ко мне? Сквозь сны. И расстоянья.\n" +
                "Но несмотря ни на что... Пришла! Судьба-злодейка!\n" +
                "И у любви у нашей села батарейка";
        List<String> expected2 = List.of("В минуты расставаньяя", "Ты возвращалась");
        TextComponent textComponent2 = textParser.parse(text2);

        return new Object[][]{
                {textComponent1, expected1},
                {textComponent2, expected2}
        };
    }

    @DataProvider(name = "find words and count")
    public Object[][] findWordsAndTheirCountData(){
        TextParser textParser = TextParser.getInstance();
        String text1 = "Привет! Как дела?\n" +
                "Привет! Хорошо. А ты кто?\n" +
                "Кто я? Я твой лучший друг! Забыл?";
        Map<String, Integer> expected1 = new HashMap<>();
        expected1.put("привет", 2);
        expected1.put("как", 1);
        expected1.put("дела", 1);
        expected1.put("хорошо", 1);
        expected1.put("а", 1);
        expected1.put("ты", 1);
        expected1.put("кто", 2);
        expected1.put("я", 2);
        expected1.put("твой", 1);
        expected1.put("лучший", 1);
        expected1.put("друг", 1);
        expected1.put("забыл", 1);
        TextComponent textComponent1 = textParser.parse(text1);

        String text2 = "Дважды два - четыре! Дважды два - четыре!\n" +
                "Это же известно всем в мире!\n" +
                "Но не пять, но не шесть. Это надо знать!";
        Map<String, Integer> expected2 = new HashMap<>();
        expected2.put("дважды", 2);
        expected2.put("два", 2);
        expected2.put("четыре", 2);
        expected2.put("это", 2);
        expected2.put("же", 1);
        expected2.put("известно", 1);
        expected2.put("всем", 1);
        expected2.put("в", 1);
        expected2.put("мире", 1);
        expected2.put("но", 2);
        expected2.put("не", 2);
        expected2.put("пять", 1);
        expected2.put("шесть", 1);
        expected2.put("надо", 1);
        expected2.put("знать", 1);

        TextComponent textComponent2 = textParser.parse(text2);

        return new Object[][]{
                {textComponent1, expected1},
                {textComponent2, expected2}
        };
    }

    @AfterClass
    public void tearDown() {
        action = null;
    }
}