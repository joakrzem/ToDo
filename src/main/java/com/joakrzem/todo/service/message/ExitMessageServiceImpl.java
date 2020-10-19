package com.joakrzem.todo.service.message;

import com.joakrzem.todo.utils.FileHelper;

import java.util.List;
import java.util.Random;

public class ExitMessageServiceImpl implements ExitMessageService {
    private final Random random = new Random();
    private final List<String> exitMessages;

    public ExitMessageServiceImpl(String exitMessagesFileName) {
        exitMessages = FileHelper.readFileLines(exitMessagesFileName);
    }

    @Override
    public String getMessage() {
        int numberOfMessages = exitMessages.size();
        int randomIndex = random.nextInt(numberOfMessages);

        return exitMessages.get(randomIndex);
    }
}
