package com.epam.mysite.domain.entity.content.order;

import java.util.List;

public class ClientOrderEntity {
    private List<ClientOrderMainPartEntity> mainPartEntities;

    public ClientOrderEntity() {
    }

    public List<ClientOrderMainPartEntity> getMainPartEntities() {
        return mainPartEntities;
    }

    public void setMainPartEntities(List<ClientOrderMainPartEntity> mainPartEntities) {
        this.mainPartEntities = mainPartEntities;
    }
}
