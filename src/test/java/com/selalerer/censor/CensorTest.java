package com.selalerer.censor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CensorTest {

    @Test
    public void censorNoSecrets() {
        var testSubject = new Censor();
        var input = "Logging in as user: jery, password: x645kvfd@Gfgr";
        var output = testSubject.edit(input);
        assertEquals(input, output);
    }

    @Test
    public void censorOneSecret() {
        var testSubject = new Censor();
        testSubject.censor("x645kvfd@Gfgr");
        var input = "Logging in as user: jery, password: x645kvfd@Gfgr";
        var output = testSubject.edit(input);
        var expected = "Logging in as user: jery, password: ****";
        assertEquals(expected, output);
    }

    @Test
    public void sameSecretMultipleTimes() {
        var testSubject = new Censor();
        testSubject.censor("x645kvfd@Gfgr");
        var input = """
                Logging in as user: jery, password: x645kvfd@Gfgr
                Successful login as user: jery, password: x645kvfd@Gfgr
                """;
        var output = testSubject.edit(input);
        var expected = """
                Logging in as user: jery, password: ****
                Successful login as user: jery, password: ****
                """;
        assertEquals(expected, output);
    }

    @Test
    public void severalSecrets() {
        var testSubject = new Censor();
        testSubject.censor("x645kvfd@Gfgr");
        var input = """
                Logging in as user: jery, password: x645kvfd@Gfgr
                Successful login as user: jery, password: x645kvfd@Gfgr
                Logging in as user: elis, password: chont5tog!
                Successful login as user: elis, password: chont5tog!
                """;
        var output = testSubject.edit(input);
        var expected = """
                Logging in as user: jery, password: ****
                Successful login as user: jery, password: ****
                Logging in as user: elis, password: chont5tog!
                Successful login as user: elis, password: chont5tog!
                """;
        assertEquals(expected, output);
        testSubject.censor("chont5tog!");
        output = testSubject.edit(input);
        expected = """
                Logging in as user: jery, password: ****
                Successful login as user: jery, password: ****
                Logging in as user: elis, password: ****
                Successful login as user: elis, password: ****
                """;
        assertEquals(expected, output);
        testSubject.uncensor("x645kvfd@Gfgr");
        output = testSubject.edit(input);
        expected = """
                Logging in as user: jery, password: x645kvfd@Gfgr
                Successful login as user: jery, password: x645kvfd@Gfgr
                Logging in as user: elis, password: ****
                Successful login as user: elis, password: ****
                """;
        assertEquals(expected, output);
        testSubject.uncensor("chont5tog!");
        output = testSubject.edit(input);
        expected = """
                Logging in as user: jery, password: x645kvfd@Gfgr
                Successful login as user: jery, password: x645kvfd@Gfgr
                Logging in as user: elis, password: chont5tog!
                Successful login as user: elis, password: chont5tog!
                """;
        assertEquals(expected, output);
    }
}

