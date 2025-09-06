package com.selalerer.censor;

public interface CensoredStringSupplier {
    String getCensoredString(String secret);
}
