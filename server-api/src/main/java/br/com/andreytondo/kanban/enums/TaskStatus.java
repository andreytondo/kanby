package br.com.andreytondo.kanban.enums;

import lombok.Getter;

public enum TaskStatus {

    TODO("TODO"),
    DOING("DOING"),
    DONE("DONE");

    @Getter
    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }
}
