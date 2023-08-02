package br.com.andreytondo.kanban.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class ServiceImpl {

    @Inject
    protected EntityManager em;
}
