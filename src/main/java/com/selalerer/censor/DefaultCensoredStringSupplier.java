package com.selalerer.censor;

public class DefaultCensoredStringSupplier implements CensoredStringSupplier {

    private final String constantCensoredString;

    public DefaultCensoredStringSupplier() {
        this("****");
    }

    public DefaultCensoredStringSupplier(String constantCensoredString) {
        this.constantCensoredString = constantCensoredString;
    }

    @Override
    public String getCensoredString(String secret) {
        return constantCensoredString;
    }
}
