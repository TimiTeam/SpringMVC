package com.gmail.timatiblackstar666.SpringMVC.dao;

import com.gmail.timatiblackstar666.SpringMVC.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Document findDocumentById(Long id);
}
