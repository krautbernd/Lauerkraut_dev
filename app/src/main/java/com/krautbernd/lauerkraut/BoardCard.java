package com.krautbernd.lauerkraut;

public class BoardCard {
    private String boarduri;
    private String boardname;
    private int pps;

    public BoardCard(String boarduri, String boardname, int pps) {
        this.boarduri = boarduri;
        this.boardname = boardname;
        this.pps = pps;
    }

    public String getBoarduri() {
        return boarduri;
    }

    public String getBoardname() {
        return boardname;
    }

    public int getPps() {
        return pps;
    }
}
