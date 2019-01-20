package myArticles.edu.dto;

public class PageInfoDto {
    private int pageNumber;
    private int visibleArticles;

    public PageInfoDto(int pageNumber, int visibleArticles){
        this.pageNumber = pageNumber;
        this.visibleArticles = visibleArticles;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getVisibleArticles() {
        return visibleArticles;
    }
}
