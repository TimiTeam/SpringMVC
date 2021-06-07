package com.gmail.timatiblackstar666.SpringMVC.services.impl;

import com.gmail.timatiblackstar666.SpringMVC.dao.DocumentRepository;
import com.gmail.timatiblackstar666.SpringMVC.exceptions.DocumentNotFoundException;
import com.gmail.timatiblackstar666.SpringMVC.models.Document;
import com.gmail.timatiblackstar666.SpringMVC.services.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements IDocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Document findDocumentById(String id) throws DocumentNotFoundException {
        try {
            long docId = Long.parseLong(id);
            return documentRepository.findById(docId).
                    orElseThrow(() -> new DocumentNotFoundException("Can't find document with id: "+id));
        }catch (NumberFormatException e){
            throw new DocumentNotFoundException("Wrong id: "+id);
        }
    }

    @Override
    public void deleteDocumentById(String id) throws DocumentNotFoundException {
        documentRepository.delete(findDocumentById(id));
    }
}
