package com.epam.mysite.domain.enums;

import java.util.Objects;

public enum OrderStatus {
    IN_PROCESS("В обработке", "In process"), IN_PROGRESS("В процессе", "In progress"), DONE("Выполнено", "Done"), CANCELLED("Отменено", "Cancelled");
    private final String ru;
    private final String en;

    public String getStatus() {
        if (Objects.equals(System.getProperty("locale"), "ru")) {
            return ru;
        } else {
            return en;
        }
    }

    OrderStatus(String ru, String en) {
        this.ru = ru;
        this.en = en;
    }
}
