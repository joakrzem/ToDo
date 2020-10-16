package com.joakrzem.todo.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelper {
    public String readFile(String name) {
        InputStream is = FileHelper.class.getClassLoader().getResourceAsStream(name);

        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }

        return null;
    }

    public List<String> readFileLines(String name) {
        String file = readFile(name);
        if (file == null) {
            return List.of();
        }

        return Arrays.stream(file.split("\n"))
                .map(line -> line.replace("\n", "").replace("\r", ""))
                .collect(Collectors.toList());
    }

}
