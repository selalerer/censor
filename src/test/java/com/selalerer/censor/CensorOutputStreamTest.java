package com.selalerer.censor;

import com.selalerer.edit.buffer.OutputStreamEditor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CensorOutputStreamTest {

    @Test
    @SneakyThrows
    public void censorOutputStream() {
        var testSubject = new Censor();
        testSubject.censor("x645kvfd@Gfgr");
        testSubject.censor("chont5tog!");

        var input = """
                Logging in as user: jery, password: x645kvfd@Gfgr
                Successful login as user: jery, password: x645kvfd@Gfgr
                Logging in as user: elis, password: chont5tog!
                Successful login as user: elis, password: chont5tog!
                """;
        var outputStream = new ByteArrayOutputStream();

        try (var censoredOutputStream = new OutputStreamEditor(testSubject.getBufferEditor(), outputStream);) {
            censoredOutputStream.write(input.getBytes());
        }

        var output = outputStream.toString();

        var expected = """
                Logging in as user: jery, password: ****
                Successful login as user: jery, password: ****
                Logging in as user: elis, password: ****
                Successful login as user: elis, password: ****
                """;

        assertEquals(expected, output);
    }
}
