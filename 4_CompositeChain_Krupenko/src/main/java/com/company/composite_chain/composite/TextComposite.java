package com.company.composite_chain.composite;

import java.util.List;
import java.util.ArrayList;

public class TextComposite implements TextComponent {
    private List<TextComponent> textComponents;
    private TextType type;
    private static final String WORD_DELIMITER = " ";
    private static final String PARAGRAPH_DELIMITER = "\n";

    public TextComposite(TextType type){
        textComponents = new ArrayList<>();
        this.type = type;
    }

    public void setTextType(TextType type) {
        this.type = type;
    }

    @Override
    public TextType getTextType() {
        return type;
    }

    @Override
    public void add(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        textComponents.remove(textComponent);
    }

    @Override
    public void set(int index, TextComponent textComponent) {
       textComponent.set(index, textComponent);
    }

    @Override
    public TextComponent get(int index) {
        return textComponents.get(index);
    }

    @Override
    public List<TextComponent> getAll() {
        return textComponents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent t : textComponents) {
            sb.append(t.toString());
            switch (t.getTextType()) {
                case WORD -> sb.append(WORD_DELIMITER);
                case PARAGRAPH -> sb.append(PARAGRAPH_DELIMITER);
            }
        }
        return sb.toString();
    }
}
