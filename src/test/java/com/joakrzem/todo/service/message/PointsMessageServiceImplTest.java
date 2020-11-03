package com.joakrzem.todo.service.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PointsMessageServiceImplTest {

    PointsMessageServiceImpl pointMessageService;

    MessageTranslationService messageTranslationService = mock(MessageTranslationService.class);

    @BeforeEach
    public void setUp() {
        pointMessageService = new PointsMessageServiceImpl(messageTranslationService);

        when(messageTranslationService.getMessage("pointsMessageServiceImplMoreThan200Points")).thenReturn("You are really task master!");
        when(messageTranslationService.getMessage("pointsMessageServiceImplMoreThan100Points")).thenReturn("Good luck, keep going!");
        when(messageTranslationService.getMessage("pointsMessageServiceImplMoreThan50Points")).thenReturn("Nice");
        when(messageTranslationService.getMessage("pointsMessageServiceImplMoreThan20Points")).thenReturn("You are starting rolling");
        when(messageTranslationService.getMessage("pointsMessageServiceImplLessThan20Points")).thenReturn("Don't give up!");

    }

    @ParameterizedTest
    @CsvSource(value = {"220:You are really task master!",
            "150:Good luck, keep going!",
            "60:Nice",
            "30:You are starting rolling",
            "10:Don't give up!"
    }, delimiter = ':')
    void getMessage_ShouldReturnCorrectMessageForCorrectPoints(String points, String expectedMessage) {
        assertEquals(expectedMessage, pointMessageService.getMessage(Integer.parseInt(points)));
    }

}