package br.com.andreytondo.kanban.resource.board;

import br.com.andreytondo.kanban.model.board.Board;
import br.com.andreytondo.kanban.model.totalizaer.Totalizer;
import br.com.andreytondo.kanban.service.board.BoardService;
import br.com.andreytondo.kanban.resource.Resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RequestScoped
@Path("/api/v1/board")
public class BoardResource extends Resource {

    @Inject
    BoardService service;

    @POST
    public Long post(Board board) {
        return service.create(board);
    }

    @GET
    @Path("/{id}")
    public Board get(@PathParam("id") Long entityId) {
        return service.findById(entityId);
    }

    @GET
    @Path("/list")
    public Totalizer getList() {
        return service.listBoards();
    }

}
