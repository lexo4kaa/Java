package com.company.composite_chain.parser;

import com.company.composite_chain.composite.TextComponent;

public interface BaseParser {
    TextComponent parse(String text);
}
