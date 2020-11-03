package com.joakrzem.todo.service.message;


public class PointsMessageServiceImpl implements PointsMessageService {

    MessageTranslationService messageTranslationService = new MessageTranslationServiceFile();

    @Override
    public String getMessage(int points) {
        if (points >= 201) {
            return messageTranslationService.getMessage("pointsMessageServiceImpl1");
        }

        if (points >= 101) {
            return messageTranslationService.getMessage("pointsMessageServiceImpl2");
        }

        if (points >= 51) {
            return messageTranslationService.getMessage("pointsMessageServiceImpl3");
        }

        if (points >= 21) {
            return messageTranslationService.getMessage("pointsMessageServiceImpl4");
        }

        return messageTranslationService.getMessage("pointsMessageServiceImpl5");
    }
}
