package br.com.andreytondo.kanban.enums;

import lombok.Getter;

public enum SubtaskStatus {

    TODO("TODO"),
    DONE("DONE");

    @Getter
    private final String description;

    SubtaskStatus(String description) {
        this.description = description;
    }
}
