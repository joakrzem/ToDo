package com.joakrzem.todo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileHelper {
    private FileHelper() {

    }

    public static String readFile(String name) {
        InputStream is = FileHelper.class.getClassLoader().getResourceAsStream(name);

        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }

        return null;
    }

    public static List<String> readFileLines(String name) {
        String file = readFile(name);
        if (file == null) {
            return List.of();
        }

        return Arrays.stream(file.split("\n"))
                .map(line -> line.replace("\n", "").replace("\r", ""))
                .collect(Collectors.toList());
    }

    public static List<String> getFileNamesInDirectory(String folderName) {
        String path = Objects.requireNonNull(FileHelper.class.getClassLoader().getResource(folderName)).getPath();
        return Arrays.stream(Objects.requireNonNull(new File(path).listFiles())).map(File::getName).collect(Collectors.toList());
    }

}
