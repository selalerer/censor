package com.selalerer.censor;

import com.selalerer.edit.buffer.InputStreamEditor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CensorInputStreamTest {

    @Test
    @SneakyThrows
    public void censorInputStream() {

        var testSubject = new Censor();
        testSubject.censor("x645kvfd@Gfgr");
        testSubject.censor("chont5tog!");

        var input = """
                Logging in as user: jery, password: x645kvfd@Gfgr
                Successful login as user: jery, password: x645kvfd@Gfgr
                Logging in as user: elis, password: chont5tog!
                Successful login as user: elis, password: chont5tog!
                """;

        var censoredInputStream = new InputStreamEditor(testSubject.getBufferEditor(), new ByteArrayInputStream(input.getBytes()));

        var output = new String(censoredInputStream.readAllBytes());

        var expected = """
                Logging in as user: jery, password: ****
                Successful login as user: jery, password: ****
                Logging in as user: elis, password: ****
                Successful login as user: elis, password: ****
                """;

        assertEquals(expected, output);
    }
}
