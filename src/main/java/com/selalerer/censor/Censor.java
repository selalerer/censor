package com.selalerer.censor;

import com.selalerer.edit.Editor;
import com.selalerer.edit.StringEditor;
import com.selalerer.edit.buffer.BufferEditor;

public class Censor implements Editor<String> {

    private final StringEditor stringEditor = new StringEditor();
    private final CensoredStringSupplier censoredStringSupplier;

    public Censor() {
        this(new DefaultCensoredStringSupplier());
    }

    public Censor(CensoredStringSupplier censoredStringSupplier) {
        this.censoredStringSupplier = censoredStringSupplier;
    }

    public void censor(String secret) {
        stringEditor.addReplacement(secret, censoredStringSupplier.getCensoredString(secret));
    }

    public void uncensor(String secret) {
        stringEditor.removeReplacement(secret);
    }

    @Override
    public String edit(String in) {
        return stringEditor.edit(in);
    }

    public BufferEditor getBufferEditor() {
        return stringEditor.getBufferEditor();
    }
}
