package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import net.hypnoz.core.models.Groupes;
import net.hypnoz.core.models.UserGroupes;
import net.hypnoz.core.models.Users;

@ApiModel()
public class UserGroupesDto extends AbstractDto<UserGroupes.UserGroupesPK> {
    private UserGroupes.UserGroupesPK id;
    private Groupes groupes;
    private Users users;
    private boolean isNew;

    public UserGroupesDto() {
    }

    public void setId(net.hypnoz.core.models.UserGroupes.UserGroupesPK id) {
        this.id = id;
    }

    public net.hypnoz.core.models.UserGroupes.UserGroupesPK getId() {
        return this.id;
    }

    public void setGroupes(Groupes groupes) {
        this.groupes = groupes;
    }

    public Groupes getGroupes() {
        return this.groupes;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean getIsNew() {
        return this.isNew;
    }
}