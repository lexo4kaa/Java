package com.company.composite_chain.composite;

import java.util.List;

public class Symbol implements TextComponent{
    private char symbol;

    public Symbol(char symbol){
        this.symbol = symbol;
    }

    @Override
    public void add(TextComponent textComponent) {
    }

    @Override
    public void remove(TextComponent textComponent) {
    }

    @Override
    public TextComponent get(int index) {
        return null;
    }

    @Override
    public List<TextComponent> getAll() {
        return null;
    }

    @Override
    public TextType getTextType() {
        return TextType.SYMBOL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Symbol that = (Symbol) o;
        return symbol == that.symbol;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + symbol;
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
