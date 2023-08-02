package br.com.andreytondo.kanban.model.totalizaer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Totalizer {

    private int total;
    private List<Object> entities;
}
