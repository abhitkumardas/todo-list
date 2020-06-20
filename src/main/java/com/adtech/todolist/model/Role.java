package com.adtech.todolist.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ferozk
 */

@Entity
public class Role {

    @Id
    private long roleId;
    private String name;
    private String slug;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
