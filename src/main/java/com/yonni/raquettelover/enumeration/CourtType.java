package com.yonni.raquettelover.enumeration;

public enum CourtType {
    PADEL(4),
    SQUASH(2),
    TENNIS(2);

    private final int minPlayers;

    CourtType(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }
}
