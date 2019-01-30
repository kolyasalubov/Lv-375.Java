package com.it.academy.dto;

import java.util.List;

/**
 * Class CollectionDto represents DTO object with List of some entity
 */
public class CollectionDto<TDto> {

    // constants for pagination
    private final int DEFAULT_PAGE_OFFSET = 10; // if user do not choose
    private final int DEFAULT_PAGE = 1; // display the first page by default

    private List<TDto> collection;
    private int pageCount;
    private int pageOffset;
    private int page;

    public CollectionDto(){}

    public CollectionDto(List<TDto> collection, int pageOffset) {
        this.collection = collection;
        this.page = DEFAULT_PAGE;
        this.pageOffset = pageOffset;
        this.pageCount = calculatePageCount();
    }

    public CollectionDto(List<TDto> collection) {
        this.collection = collection;
        this.page = DEFAULT_PAGE;
        this.pageOffset = DEFAULT_PAGE_OFFSET;
        this.pageCount = calculatePageCount();
    }

    public List<TDto> getCollection() {
        return collection;
    }

    public void setCollection(List<TDto> collection) {
        this.collection = collection;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
        this.pageCount = calculatePageCount();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "CollectionDto{" +
                "DEFAULT_PAGE_OFFSET=" + DEFAULT_PAGE_OFFSET +
                ", DEFAULT_PAGE=" + DEFAULT_PAGE +
                ", collection=" + collection +
                ", pageCount=" + pageCount +
                ", pageOffset=" + pageOffset +
                ", page=" + page +
                '}';
    }

    private int calculatePageCount(){
        return (int)Math.ceil((double)collection.size() / pageOffset);
    }
}
