package strategies.BotPlayingStrategy;

import models.BotDifficultyLevel;

public class BotPlayingStrategyFactory{
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel level){
        return switch (level){
            case EASY -> new RandomBotPlayingStrategy();
            case MEDIUM -> new RandomBotPlayingStrategy();
            case HARD -> new RandomBotPlayingStrategy();
        };
    }
}
