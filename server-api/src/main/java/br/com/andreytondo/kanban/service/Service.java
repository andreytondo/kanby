package br.com.andreytondo.kanban.service;

public interface Service<E> {

    Long create(E entity);
    Long update(E entity);
    void delete(Long entityId);
    E findById(Long entityId);

}
