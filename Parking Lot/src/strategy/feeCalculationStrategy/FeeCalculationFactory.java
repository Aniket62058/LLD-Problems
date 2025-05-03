package strategy.feeCalculationStrategy;

public class FeeCalculationFactory {
    public static FeeCalculationStrategy getFeeCalculationStrategy(){
        return new LowFeeCalculationStrategy();
    }
}
