package com.yourcompany.domain;

import com.yourcompany.api.factories.TemplateFactory;
import com.yourcompany.exceptions.NoSuchTemplateExists;

import java.util.List;

/**
 * Implemented in the DBTemplateRepository
 */
public interface TemplateRepository {
    List<Template> findAll();
    Template findById(int id) throws NoSuchTemplateExists;
    Template create(TemplateFactory factory);
}
