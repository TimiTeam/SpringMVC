package com.gmail.timatiblackstar666.SpringMVC.services;

import com.gmail.timatiblackstar666.SpringMVC.exceptions.DocumentNotFoundException;
import com.gmail.timatiblackstar666.SpringMVC.models.Document;

public interface IDocumentService {

    Document saveDocument(Document document);
    Document findDocumentById(String id) throws DocumentNotFoundException;
    void deleteDocumentById(String id) throws DocumentNotFoundException;
}
