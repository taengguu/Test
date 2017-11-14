package cn.material.sys.vo;

import java.util.List;

import cn.material.sys.entity.Group;
import cn.material.sys.entity.Role;

/**
 *
 * 用户组的数据载体
 * Created by Mr.wang on 2016/12/2.
 */

public class GroupInfo {

    private Group group ;

    private List<Role> roles ;

    public GroupInfo(){}

    public GroupInfo(Group group, List<Role> roles) {
        this.group = group ;
        this.roles = roles ;
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
