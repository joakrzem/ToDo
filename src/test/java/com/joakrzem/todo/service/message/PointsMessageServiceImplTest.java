package com.joakrzem.todo.service.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PointsMessageServiceImplTest {

    PointsMessageServiceImpl pointMessageService;

    @BeforeEach
    public void setUp (){
        pointMessageService = new PointsMessageServiceImpl();
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