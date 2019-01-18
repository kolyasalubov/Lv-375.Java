package com.it.academy.dto;

import java.util.List;

public class CollectionDto<TDto> {

    private final int DEFAULT_PAGE_OFFSET = 10; // TODO

    private List<TDto> collection;
    private int pageCount;
    private int pageOffset;

    public CollectionDto(){}

    public CollectionDto(List<TDto> collection, int pageOffset) {
        this.collection = collection;
        this.pageOffset = pageOffset;
        this.pageCount = collection.size() / pageOffset + 1;
    }

    public CollectionDto(List<TDto> collection) {
        this.collection = collection;
        this.pageOffset = DEFAULT_PAGE_OFFSET;
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
    }

}
