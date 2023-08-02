package br.com.andreytondo.kanban.service.task;

import br.com.andreytondo.kanban.model.task.Task;
import br.com.andreytondo.kanban.service.Service;
import br.com.andreytondo.kanban.service.ServiceImpl;
import br.com.andreytondo.kanban.service.subtask.SubtaskService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class TaskService extends ServiceImpl implements Service<Task> {

    @Inject
    SubtaskService subtaskService;

    @Override
    @Transactional
    public Long create(Task entity) {

        em.persist(entity);
        em.flush();

        if (entity.getSubtaks() != null && !entity.getSubtaks().isEmpty()) {
            entity.getSubtaks().forEach(s -> {
                s.setTask(new Task(entity.getId()));
                subtaskService.create(s);
            });
        }

       return entity.getId();
    }

    @Override
    @Transactional
    public Long update(Task entity) {
        em.merge(entity);
        em.flush();
        return entity.getId();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public void delete(Long entityId) {
        Task task = em.find(Task.class, entityId);

        // get subtaks associated with task
        Query q = em.createNativeQuery("SELECT SUBTASKID FROM PUBLIC.SUBTASK WHERE TASKID = :TASKID");
        q.setParameter("TASKID", entityId);
        List<Long> subtasks = (List<Long>) q.getResultList();

        // delete subtasks
        q = em.createNativeQuery("DELETE FROM PUBLIC.SUBTASK WHERE SUBTASKID IN :SUBTASKS");
        q.setParameter("SUBTASKS", subtasks)
                .executeUpdate();

        em.remove(task);
    }

    @Override
    public Task findById(Long entityId) {
        return em.find(Task.class, entityId);
    }

}
