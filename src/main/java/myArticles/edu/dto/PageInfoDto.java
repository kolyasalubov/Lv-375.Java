package myArticles.edu.dto;

public class PageInfoDto {
    private int pageNumber;
    private int visible;

    public PageInfoDto(int pageNumber, int visible){
        this.pageNumber = pageNumber;
        this.visible = visible;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getVisible() {
        return visible;
    }
}
