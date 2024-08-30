package ya;

public class RateLimiter {
    private long time;
    private final double rate;
    private final int max;
    private int tokens;
    public RateLimiter(double rate, int max) {
        this.rate = rate;
        this.max = max;
        time = System.nanoTime();
        this.tokens = max;
    }

    synchronized public boolean process(int cost) {
        fillTokens();
        if (this.tokens < cost) {
            return false;
        }
        this.tokens -= cost;
        return true;
    }

    private void fillTokens() {
        var now = System.nanoTime();
        tokens = Math.min(tokens + (int)(rate*(now - time)), max);
        time = now;
    }
}
