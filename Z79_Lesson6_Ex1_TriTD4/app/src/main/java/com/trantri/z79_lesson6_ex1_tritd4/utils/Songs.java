package com.trantri.z79_lesson6_ex1_tritd4.utils;

public class Songs {
    private String nameSongs;
    private String timeSongs;
    private String kichThuocSongs;
    private String author;

    public Songs() {

    }

    public Songs(String nameSongs, String timeSongs, String kichThuocSongs, String author) {
        this.nameSongs = nameSongs;
        this.timeSongs = timeSongs;
        this.kichThuocSongs = kichThuocSongs;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getNameSongs() {
        return nameSongs;
    }

    public void setNameSongs(String nameSongs) {
        this.nameSongs = nameSongs;
    }

    public String getTimeSongs() {
        return timeSongs;
    }

    public void setTimeSongs(String timeSongs) {
        this.timeSongs = timeSongs;
    }

    public String getKichThuocSongs() {
        return kichThuocSongs;
    }

    public void setKichThuocSongs(String kichThuocSongs) {
        this.kichThuocSongs = kichThuocSongs;
    }
}
