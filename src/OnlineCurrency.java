import java.util.Random;
/*
This Class has been partially created with the help AI, needed some math help.
 */
public class OnlineCurrency {
    static Random random = new Random();
    private static volatile float CoinValue = 10000;
    public static float getCoinValue() {
        return CoinValue;
    }
    static class Market extends Thread {
        public void run() {
            // Initialization
            float volatilityFactor = 1.0f;

            while (true) {
                // We check a range of 0 to 999 for the 1 in 1000 crash chance
                int chance = random.nextInt(1000);
                float baseFluctuation = 0.01f * volatilityFactor;

                // CRASH 0.1% Chance of that happening
                if (chance == 999) {
                    float dropPercentage = (float) (random.nextDouble(0.2) + 0.4);
                    CoinValue = CoinValue * (1f - dropPercentage);
                    volatilityFactor = 3.0f;

                    // Small Increase 25% Chance
                } else if (chance < 250) {
                    CoinValue = (float) (CoinValue + (random.nextDouble(CoinValue * baseFluctuation * 2.5) - CoinValue * baseFluctuation * 1.5));
                    volatilityFactor = (float) Math.max(0.5, volatilityFactor - 0.05);

                    // Small Decrease 35% Chance
                } else if (chance < 600) {
                    CoinValue = (float) (CoinValue - (random.nextDouble(CoinValue * baseFluctuation * 1) + CoinValue * baseFluctuation * 2));
                    volatilityFactor = (float) Math.max(0.5, volatilityFactor - 0.05);

                    // Significant Growth 40% Chance
                } else {
                    // GROWTH RESISTANCE: Subtract a small fraction of CoinValue to dampen long-term growth
                    float growthMultiplier = (float) (random.nextDouble(CoinValue * baseFluctuation * 6) + CoinValue * baseFluctuation * 2);

                    // AI: Subtract a resistance factor. Use a small constant like 0.005 to 0.01
                    float resistance = CoinValue * 0.005f;

                    CoinValue = CoinValue + growthMultiplier - resistance;
                    volatilityFactor = (float) Math.min(1.5, volatilityFactor + 0.1);
                }

                if (CoinValue < 500) {
                    // If the coin reaches a critically low value, then float it up a little bit (Very low chance)
                    CoinValue = 1000;
                    volatilityFactor = 1.0f;
                }

                try {
                    // Delay for a second, slows down the progression of the coins value.
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
