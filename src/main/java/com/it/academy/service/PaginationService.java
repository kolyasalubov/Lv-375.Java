package com.it.academy.service;

import com.it.academy.dto.CollectionDto;

import java.util.List;

public class PaginationService<TDto> {

    public CollectionDto<TDto> updateCollection(CollectionDto<TDto> collection, String pageOffset, String page){
        if (pageOffset != null)
            collection.setPageOffset(Integer.parseInt(pageOffset));
        if (page != null)
            collection.setPage(Integer.parseInt(page));

        int fromIndex = (collection.getPage() - 1) * collection.getPageOffset();
        int toIndex = collection.getPage() * collection.getPageOffset();
        List<TDto> pageCollection;
        try {
            pageCollection = collection.getCollection().subList(fromIndex, toIndex);
        } catch (IndexOutOfBoundsException e) {
            pageCollection = collection.getCollection().subList(fromIndex, collection.getCollection().size());
        }

        collection.setCollection(pageCollection);
        return collection;
    }
}
