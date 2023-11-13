package model;

import java.util.Arrays;
import java.util.Optional;

public enum Badge {

    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String badgeName;
    private final int minimumPurchase;

    Badge(String badgeName, int minimumPurchase) {
        this.badgeName = badgeName;
        this.minimumPurchase = minimumPurchase;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public int getMinimumPurchase() {
        return minimumPurchase;
    }

    public static Optional<Badge> findByMinimumPurchase(int minimumPurchase) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.minimumPurchase == minimumPurchase)
                .findFirst();
    }
}
