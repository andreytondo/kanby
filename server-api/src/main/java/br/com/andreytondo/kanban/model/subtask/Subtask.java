package br.com.andreytondo.kanban.model.subtask;

import br.com.andreytondo.kanban.enums.SubtaskStatus;
import br.com.andreytondo.kanban.model.task.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(schema = "public", name = "subtask")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_subtask", sequenceName = "seq_subtask", allocationSize = 1)
public class Subtask {

    @Id
    @Column(name = "subtaskid")
    @GeneratedValue(generator = "seq_subtask", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "taskid")
    private Task task;

    private String description;

    @Enumerated(EnumType.STRING)
    private SubtaskStatus status;

    public Subtask(Long id) {
        this.id = id;
    }
}
