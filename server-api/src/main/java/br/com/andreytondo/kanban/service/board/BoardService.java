package br.com.andreytondo.kanban.service.board;

import br.com.andreytondo.kanban.model.board.Board;
import br.com.andreytondo.kanban.model.totalizaer.Totalizer;
import br.com.andreytondo.kanban.service.Service;
import br.com.andreytondo.kanban.service.ServiceImpl;
import br.com.andreytondo.kanban.service.task.TaskService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class BoardService extends ServiceImpl implements Service<Board> {

    @Inject
    TaskService taskService;

    @Override
    @Transactional
    public Long create(Board entity) {

        em.persist(entity);
        em.flush();

        if (entity.getTasks() != null && !entity.getTasks().isEmpty()) {
            entity.getTasks().forEach(s -> {
                s.setBoard(new Board(entity.getId()));
                taskService.create(s);
            });
        }

        return entity.getId();
    }

    @Override
    @Transactional
    public Long update(Board entity) {
        em.merge(entity);
        em.flush();
        return entity.getId();
    }

    @Override
    @Transactional
    public void delete(Long entityId) {
        em.remove(new Board(entityId));
        em.flush();
    }

    @Override
    public Board findById(Long entityId) {
        return em.find(Board.class, entityId);
    }

    @SuppressWarnings("unchecked")
    public Totalizer listBoards() {
        List<Object[]> query = em.createNativeQuery(
                "SELECT boardid, name FROM public.board"
        ).getResultList();

        if (!query.isEmpty()) {
            return new Totalizer(
                    query.size(),
                    query.stream().map(b ->
                            Board.builder()
                                    .id(b[0] != null ? Long.parseLong(b[0].toString()) : null)
                                    .name(b[1] != null ? b[1].toString() : null)
                                    .build()
                    ).collect(Collectors.toList()));
        }
        return null;
    }

}
