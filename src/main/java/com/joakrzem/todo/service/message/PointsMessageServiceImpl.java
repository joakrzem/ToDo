package com.joakrzem.todo.service.message;


public class PointsMessageServiceImpl implements PointsMessageService {


    @Override
    public String getMessage(int points) {
        if (points >= 201) {
            return "You are really task master!";
        }

        if (points >= 101) {
            return "Good luck, keep going!";
        }

        if (points >= 51) {
            return "Nice";
        }

        if (points >= 21) {
            return "You are starting rolling";
        }

        return "Don't give up!";
    }
}
