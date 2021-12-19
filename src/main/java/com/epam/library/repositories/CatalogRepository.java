package com.epam.library.repositories;

import com.epam.library.models.Catalog;

import java.util.List;

public interface CatalogRepository {

    List<Catalog> getFreeBooksInLibrary();

}
