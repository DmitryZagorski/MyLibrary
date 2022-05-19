package com.epam.library.service;

import com.epam.library.models.Catalog;
import com.epam.library.repositories.JDBCCatalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CatalogService {

    private static final Logger Log = LoggerFactory.getLogger(CatalogService.class);

    private static CatalogService instance;

    private CatalogService() {
        instance = this;
    }

    public static CatalogService getInstance() {
        if (instance == null) {
            instance = new CatalogService();
        }
        return instance;
    }

    public Catalog addOrUpdateBookInCatalog(int bookId, int totalQuantityInt, int freeQuantityInt) {
        Catalog catalog = createCatalog(bookId, totalQuantityInt, freeQuantityInt);

        List<Catalog> allBooksInCatalog = JDBCCatalogRepository.getInstance().findAll();

        boolean needToUpdate = false;
        for (Catalog book : allBooksInCatalog) {
            if (bookId == book.getBookId()) {
                needToUpdate = true;
                totalQuantityInt += book.getTotalQuantity();
            }
        }
        chooseAddOrUpdate(bookId, totalQuantityInt, catalog, needToUpdate);
        return catalog;
    }

    private void chooseAddOrUpdate(int bookId, int totalQuantityInt, Catalog catalog, boolean needToUpdate) {
        if (needToUpdate) {
            JDBCCatalogRepository.getInstance().updateTotalQuantityOfBook(bookId, totalQuantityInt);
        } else {
            JDBCCatalogRepository.getInstance().addBookToCatalog(catalog);
        }
    }

    private Catalog createCatalog(int bookId, int totalQuantityInt, int freeQuantityInt) {
        Catalog catalog = new Catalog();
        catalog.setBookId(bookId);
        catalog.setTotalQuantity(totalQuantityInt);
        catalog.setFreeQuantity(freeQuantityInt);
        return catalog;
    }
}
