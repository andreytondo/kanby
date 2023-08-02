package br.com.andreytondo.kanban.service.subtask;

import br.com.andreytondo.kanban.service.ServiceImpl;
import br.com.andreytondo.kanban.model.subtask.Subtask;
import br.com.andreytondo.kanban.service.Service;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

@RequestScoped
public class SubtaskService extends ServiceImpl implements Service<Subtask> {

    @Override
    @Transactional
    public Long create(Subtask entity) {
       em.persist(entity);
       em.flush();
       return entity.getId();
    }

    @Override
    @Transactional
    public Long update(Subtask entity) {
        em.merge(entity);
        em.flush();
        return entity.getId();
    }

    @Override
    @Transactional
    public void delete(Long entityId) {
        em.remove(new Subtask(entityId));
        em.flush();
    }

    @Override
    public Subtask findById(Long entityId) {
        return em.find(Subtask.class, entityId);
    }

}
