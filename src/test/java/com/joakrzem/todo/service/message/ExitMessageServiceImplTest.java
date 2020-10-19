package com.joakrzem.todo.service.message;

import com.joakrzem.todo.utils.FileHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExitMessageServiceImplTest {
    private static final String MESSAGES_FILE_NAME = "test.txt";
    private final ExitMessageService listOfMessages = new ExitMessageServiceImpl(MESSAGES_FILE_NAME);
    private final List<String> fileLines = FileHelper.readFileLines(MESSAGES_FILE_NAME);

    @Test
    void getMessage_ShouldReturnMessageFromFile() {
        //When
        String result = listOfMessages.getMessage();

        //Then
        assertTrue(fileLines.contains(result));
    }
}