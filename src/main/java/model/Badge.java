package model;

import java.util.Arrays;
import java.util.Comparator;

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
        return new String(badgeName);
    }

    public static Badge of(int totalBenefitAmount) {
        return Arrays.stream(values())
                .filter(badge -> badge.minimumPurchase <= totalBenefitAmount * -1)
                .max(Comparator.comparingInt(badge -> badge.minimumPurchase))
                .orElse(null);
    }
}