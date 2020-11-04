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

        when(messageTranslationService.getMessage("moreThan200PointsMessage")).thenReturn("You are really task master!");
        when(messageTranslationService.getMessage("moreThan100PointsMessage")).thenReturn("Good luck, keep going!");
        when(messageTranslationService.getMessage("moreThan50PointsMessage")).thenReturn("Nice");
        when(messageTranslationService.getMessage("moreThan20PointsMessage")).thenReturn("You are starting rolling");
        when(messageTranslationService.getMessage("lessThan20PointsMessage")).thenReturn("Don't give up!");

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