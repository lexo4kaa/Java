package com.company.composite_chain.parser.impl;

import com.company.composite_chain.composite.Symbol;
import com.company.composite_chain.composite.TextComponent;
import com.company.composite_chain.composite.TextComposite;
import com.company.composite_chain.composite.TextType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TextParserTest {
    TextParser parser;
    @BeforeClass
    public void setUp() {
        parser = TextParser.getInstance();
    }

    @Test(dataProvider = "parser")
    public void textParserTest(TextComponent expected, String text){
        TextComponent actual = parser.parse(text);
        Assert.assertEquals(actual.toString().strip(),expected.toString().strip());
    }

    @DataProvider(name = "parser")
    public Object[][] createTextParserData(){
        Symbol Symbol11 = new Symbol('П');
        Symbol Symbol12 = new Symbol('р');
        Symbol Symbol13 = new Symbol('и');
        Symbol Symbol14 = new Symbol('в');
        Symbol Symbol15 = new Symbol('е');
        Symbol Symbol16 = new Symbol('т');
        TextComponent word1 = new TextComposite(TextType.WORD);
        word1.add(Symbol11);
        word1.add(Symbol12);
        word1.add(Symbol13);
        word1.add(Symbol14);
        word1.add(Symbol15);
        word1.add(Symbol16);
        TextComponent sentence1 = new TextComposite(TextType.SENTENCE);
        sentence1.add(word1);
        Symbol Symbol21 = new Symbol('К');
        Symbol Symbol22 = new Symbol('а');
        Symbol Symbol23 = new Symbol('к');
        TextComponent word2 = new TextComposite(TextType.WORD);
        word2.add(Symbol21);
        word2.add(Symbol22);
        word2.add(Symbol23);
        Symbol Symbol25 = new Symbol('д');
        Symbol Symbol26 = new Symbol('е');
        Symbol Symbol27 = new Symbol('л');
        Symbol Symbol28 = new Symbol('а');
        TextComponent word3 = new TextComposite(TextType.WORD);
        word3.add(Symbol25);
        word3.add(Symbol26);
        word3.add(Symbol27);
        word3.add(Symbol28);
        TextComponent sentence2 = new TextComposite(TextType.SENTENCE);
        sentence2.add(word2);
        sentence2.add(word3);
        TextComponent paragraph1 = new TextComposite(TextType.PARAGRAPH);
        paragraph1.add(sentence1);
        paragraph1.add(sentence2);

        Symbol Symbol31 = new Symbol('Х');
        Symbol Symbol32 = new Symbol('о');
        Symbol Symbol33 = new Symbol('р');
        Symbol Symbol34 = new Symbol('о');
        Symbol Symbol35 = new Symbol('ш');
        Symbol Symbol36 = new Symbol('о');
        TextComponent word4 = new TextComposite(TextType.WORD);
        word4.add(Symbol31);
        word4.add(Symbol32);
        word4.add(Symbol33);
        word4.add(Symbol34);
        word4.add(Symbol35);
        word4.add(Symbol36);
        TextComponent sentence3 = new TextComposite(TextType.SENTENCE);
        sentence3.add(word4);
        TextComponent paragraph2 = new TextComposite(TextType.PARAGRAPH);
        paragraph2.add(sentence3);

        TextComponent textComponent = new TextComposite(TextType.TEXT);
        textComponent.add(paragraph1);
        textComponent.add(paragraph2);
        String text = "Привет Как дела\nХорошо";
        return new Object[][]{
                {textComponent, text}
        };
    }

    @AfterClass
    public void tearDown() {
        parser = null;
    }
}