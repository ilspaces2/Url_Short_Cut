package ru.urlshortcut.dto;

public class UrlStatisticResponseDto {

    private String url;

    private int total;

    public UrlStatisticResponseDto() {
    }

    public UrlStatisticResponseDto(String url, int total) {
        this.url = url;
        this.total = total;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
