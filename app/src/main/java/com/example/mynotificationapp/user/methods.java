package com.example.mynotificationapp.user;

public class methods {
    private int ino;
    private static String From;
    private static String To;
    private static String Timestamp;
    public methods(int ino,String From,String To,String Timestamp) {
        this.ino=ino;
        this.From=From;
        this.To=To;
        this.Timestamp=Timestamp;
    }

    public methods() {

    }

    public int getIno() {
        return ino;
    }

    public void setIno(int ino) {
        this.ino = ino;
    }

    public static String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public static String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public static String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.Timestamp = timestamp;
    }
}
