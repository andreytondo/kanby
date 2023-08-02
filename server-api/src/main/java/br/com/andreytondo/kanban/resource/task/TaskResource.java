package br.com.andreytondo.kanban.resource.task;

import br.com.andreytondo.kanban.model.task.Task;
import br.com.andreytondo.kanban.resource.Resource;
import br.com.andreytondo.kanban.service.task.TaskService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;

@RequestScoped
@Path("/api/v1/task")
public class TaskResource extends Resource {

    @Inject
    TaskService service;

    @POST
    public Long post(Task task) {
        return service.create(task);
    }

    @GET
    @Path("/{id}")
    public Task get(@PathParam("id") Long entityId) {
        return service.findById(entityId);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long entityId) {
        service.delete(entityId);
    }

}
