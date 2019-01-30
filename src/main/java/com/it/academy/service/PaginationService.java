package com.it.academy.service;

import com.it.academy.dto.CollectionDto;

import java.util.List;

/**
 * Class PaginationService provide method to display the appropriate page to user
 */
public class PaginationService<TDto> {

    /**
     * Returns the Collection of Dtos to display in current page
     */
    public CollectionDto<TDto> updateCollection(CollectionDto<TDto> collection, String pageOffset, String page){
        if (pageOffset != null)         // if user chose not default pageOffset
            collection.setPageOffset(Integer.parseInt(pageOffset));
        if (page != null)               // if user chose to what page to redirect
            collection.setPage(Integer.parseInt(page));

        int fromIndex = (collection.getPage() - 1) * collection.getPageOffset();    // calculate the first index of sublist
        int toIndex = collection.getPage() * collection.getPageOffset();            // calculate the last index of sublist
        List<TDto> pageCollection;
        try {
            pageCollection = collection.getCollection().subList(fromIndex, toIndex);
        } catch (IndexOutOfBoundsException e) {
            // if the last index is not in list -- last index ill be the last index in the list
            pageCollection = collection.getCollection().subList(fromIndex, collection.getCollection().size());
        }

        collection.setCollection(pageCollection);
        return collection;
    }
}
