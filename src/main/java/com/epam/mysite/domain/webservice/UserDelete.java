package com.epam.mysite.domain.webservice;

import java.util.List;

public class UserDelete {
    private List<Integer> userIds;

    public UserDelete() {
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
