package strategies.WinningStrategy;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(int dimension){
        return new OrderOneWinningStrategy(dimension);
    }
    // TODO : basis of input return a list of winning strategy
}
