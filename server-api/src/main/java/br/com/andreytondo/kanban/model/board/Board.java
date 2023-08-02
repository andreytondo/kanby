package br.com.andreytondo.kanban.model.board;

import br.com.andreytondo.kanban.model.task.Task;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(schema = "public", name = "board")
@AllArgsConstructor @NoArgsConstructor
@SequenceGenerator(name = "seq_board", sequenceName = "seq_board", allocationSize = 1)
public class Board {

    @Id
    @Column(name = "boardid")
    @GeneratedValue(generator = "seq_board", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "board")
    private List<Task> tasks;

    public Board(Long id) {
        this.id = id;
    }

}
