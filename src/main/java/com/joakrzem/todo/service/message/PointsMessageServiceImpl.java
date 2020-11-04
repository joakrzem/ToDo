package com.joakrzem.todo.service.message;


public class PointsMessageServiceImpl implements PointsMessageService {

    MessageTranslationService messageTranslationService;

    public PointsMessageServiceImpl(MessageTranslationService messageTranslationService) {
        this.messageTranslationService = messageTranslationService;
    }

    @Override
    public String getMessage(int points) {
        if (points >= 201) {
            return messageTranslationService.getMessage("moreThan200PointsMessage");
        }

        if (points >= 101) {
            return messageTranslationService.getMessage("moreThan100PointsMessage");
        }

        if (points >= 51) {
            return messageTranslationService.getMessage("moreThan50PointsMessage");
        }

        if (points >= 21) {
            return messageTranslationService.getMessage("moreThan20PointsMessage");
        }

        return messageTranslationService.getMessage("lessThan20PointsMessage");
    }
}
