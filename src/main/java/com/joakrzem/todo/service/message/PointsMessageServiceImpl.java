package com.joakrzem.todo.service.message;


public class PointsMessageServiceImpl implements PointsMessageService {

    MessageTranslationService messageTranslationService;

    public PointsMessageServiceImpl(MessageTranslationService messageTranslationService) {
        this.messageTranslationService = messageTranslationService;
    }

    @Override
    public String getMessage(int points) {
        if (points >= 201) {
            return messageTranslationService.getMessage("pointsMessageServiceImplMoreThan200Points");
        }

        if (points >= 101) {
            return messageTranslationService.getMessage("pointsMessageServiceImplMoreThan100Points");
        }

        if (points >= 51) {
            return messageTranslationService.getMessage("pointsMessageServiceImplMoreThan50Points");
        }

        if (points >= 21) {
            return messageTranslationService.getMessage("pointsMessageServiceImplMoreThan20Points");
        }

        return messageTranslationService.getMessage("pointsMessageServiceImplLessThan20Points");
    }
}
