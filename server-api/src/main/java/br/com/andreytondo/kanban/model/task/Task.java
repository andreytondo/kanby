package br.com.andreytondo.kanban.model.task;

import br.com.andreytondo.kanban.enums.TaskStatus;
import br.com.andreytondo.kanban.model.board.Board;
import br.com.andreytondo.kanban.model.subtask.Subtask;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "public", name = "task")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_task", sequenceName = "seq_task", allocationSize = 1)
public class Task {

    @Id
    @Column(name = "taskid")
    @GeneratedValue(generator = "seq_task", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "boardid")
    private Board board;

    private String title;

    private String description;

    @OneToMany(mappedBy = "task")
    private List<Subtask> subtaks;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public Task(Long id) {
        this.id = id;
    }
}
