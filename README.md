# censor
Tools to censor texts, mainly intended for secrets such as passwords.

Maven dependency:
```xml
        <dependency>
            <groupId>com.selalerer</groupId>
            <artifactId>censor</artifactId>
            <version>1.0.0</version>
        </dependency>
```

Censor strings:
```java
        var censor = new Censor();
        
        censor.censor("x645kvfd@Gfgr");
        censor.censor("chont5tog!");
        
        var input = """
                Logging in as user: jery, password: x645kvfd@Gfgr
                Successful login as user: jery, password: x645kvfd@Gfgr
                Logging in as user: elis, password: chont5tog!
                Successful login as user: elis, password: chont5tog!
                """;
        
        var output = censor.edit(input);
```

Can also be used with output streams:
```java
        var censor = new Censor();
        
        censor.censor("x645kvfd@Gfgr");
        censor.censor("chont5tog!");
        
        var output = """
                Logging in as user: jery, password: x645kvfd@Gfgr
                Successful login as user: jery, password: x645kvfd@Gfgr
                Logging in as user: elis, password: chont5tog!
                Successful login as user: elis, password: chont5tog!
                """;

        var censoredOutputStream = new OutputStreamEditor(testSubject.getBufferEditor(), outputStream);
    
        // Will filter output the passwords before wrting the data to the underlying output stream.
        censoredOutputStream.write(input.getBytes());
```

Or with input streams:
```java
        var censor = new Censor();
        
        censor.censor("x645kvfd@Gfgr");
        censor.censor("chont5tog!");

        var censoredInputStream = new InputStreamEditor(testSubject.getBufferEditor(), inputStream);

        // All data read from the underlying input stream will be censored before it is returned.
        censoredInputStream.readAllBytes()
```

