package com.joakrzem.todo.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileHelperTest {
    FileHelper fileHelper;

    @BeforeEach
    void setUp() {
        fileHelper = new FileHelper();
    }

    @Test
    void readFileLines_ShouldReturnEmptyList() {
        //When
        List<String> result = fileHelper.readFileLines("not-existing.txt");

        //Then
        assertEquals(0, result.size());
    }

    @Test
    void readFileLines_ShouldDeviceStringToLines() {
        //When
        List<String> result = fileHelper.readFileLines("test.txt");

        //Then
        assertEquals(4, result.size());
        assertEquals("Line1", result.get(0));
        assertEquals("Line2", result.get(1));
        assertEquals("Jack", result.get(2));
        assertEquals("Steve", result.get(3));
    }
}