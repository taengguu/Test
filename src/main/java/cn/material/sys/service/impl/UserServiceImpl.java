package cn.material.sys.service.impl;


import cn.material.common.base.BaseRepository;
import cn.material.common.base.BaseServiceImpl;
import cn.material.common.constants.Constants;
import cn.material.common.exception.ServiceException;
import cn.material.common.utils.CopyPropertiesUtils;
import cn.material.common.utils.EncryptUtils;
import cn.material.common.utils.UUIDGenerator;
import cn.material.sys.entity.Group;
import cn.material.sys.entity.Permission;
import cn.material.sys.entity.Role;
import cn.material.sys.entity.User;
import cn.material.sys.repository.GroupRepository;
import cn.material.sys.repository.PermissionRepository;
import cn.material.sys.repository.RoleRepository;
import cn.material.sys.repository.UserRepository;
import cn.material.sys.service.UserService;
import cn.material.sys.vo.UserVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  用户service的实现
 *
 * Created by Mr.wang on 2016/11/29.
 */
@Service("userServie")
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Resource
    private UserRepository userRepo ;

    @Resource
    private RoleRepository roleRepo ;

    @Resource
    private GroupRepository groupRepo ;

    @Resource
    private PermissionRepository permissionRepo ;

    
    protected BaseRepository<User, String>  getRepository() {
        return this.userRepo ;
    }


    
    public List<User> findByUsername(String username) {

        List<User> users = null ;
        try {
           users = this.userRepo.findByUsername(username) ;
        } catch (Exception e) {
            throw new ServiceException(e) ;
        }
        return users ;
    }

    
    public List<Role> findRolesByUsername(String username) {
       return this.roleRepo.findRolesByUsername(username) ;
    }

    
    public List<String> getRoleValuesByUsername(String username) {
        List<Role> roles = this.findRolesByUsername(username) ;
        List<String> roleValues = new ArrayList<String>() ;
        if(roles != null && roles.size() > 0) {
            for (int i = 0,length = roles.size(); i < length; i++) {
                roleValues.add(roles.get(i).getRoleValue()) ;
            }
        }
        return roleValues ;
    }

    
    public List<Permission> findPermissionsByUsername(String username) {
        return this.permissionRepo.findPermissionsByUsername(username) ;
    }

    
    public List<String> getPermissionValuesByUsername(String username) {
        List<Permission> permissions = this.findPermissionsByUsername(username) ;
        List<String> permissionValues = new ArrayList<String>() ;
        if(permissions != null && permissions.size() > 0) {
            for (int i = 0,length = permissions.size(); i < length; i++) {
                permissionValues.add(permissions.get(i).getPermissionValue()) ;
            }
        }
        return permissionValues ;
    }

    
    public Page<UserVo> findUsers(Pageable pageable, UserVo userVo) {

        return this.userRepo.findUsers(pageable, userVo, false) ;
    }

    @Transactional(readOnly = false,rollbackFor = RuntimeException.class)
    
    public void saveUserInfo(UserVo userVo, String password) {

        User user = new User() ;
        CopyPropertiesUtils.copyPropertiesIgnoreNull(user, userVo) ;    //设置属性
        if(userVo.getGroupId() != null && !"".equals(userVo.getGroupId())) { //设置用户组
            Group group = groupRepo.findOne(userVo.getGroupId());
            user.setGroup(group) ;
        }
        user.setRegisterTime(new Date());
        String salt = UUIDGenerator.createSalt() ;  //盐
        user.setPassword(EncryptUtils.encryptToBase64(password)) ;  //
        user.setSalt(salt) ;
        this.userRepo.save(user) ;
    }

    
    public boolean isExistUser(String username) {

        List<User> users = this.findByUsername(username);
        return users != null && users.size() > 0 ;

    }

    @Transactional(readOnly = false,rollbackFor = RuntimeException.class)
    
    public void updateUser4isDelete(String[] ids) {
        for (String id: ids) {
            User user = this.findOne(id);
            if(user != null) {
                user.setIsDelete(user.getIsDelete() == Constants.DIC_YES ? Constants.DIC_NO: Constants.DIC_YES);
                this.save(user) ;
            }
        }
    }

    
    public Page<UserVo> findUserisDelete(Pageable pageable, UserVo userVo) {
        return this.userRepo.findUsers(pageable, userVo, true) ;
    }

    @Transactional(readOnly = false,rollbackFor = RuntimeException.class)
    
    public void updateUserInfo(UserVo userVo) {
        if(userVo != null) {
            User user = this.findOne(userVo.getId());
            CopyPropertiesUtils.copyPropertiesIgnoreNull(user,userVo) ;
            Group group = user.getGroup();
            if(group != null) { //查询用户是否有所属用户组
                if(!StringUtils.equals(group.getId(),userVo.getGroupId())) { //所属用户组是否跟修改后的一样
                    if(userVo.getGroupId() == null) {
                        user.setGroup(null) ;
                    } else {
                        user.setGroup(this.groupRepo.findOne(userVo.getGroupId())) ;
                    }

                }
            } else {
                if(userVo.getGroupId() == null) {
                    user.setGroup(null) ;
                } else {
                    user.setGroup(this.groupRepo.findOne(userVo.getGroupId())) ;
                }
            }
            this.save(user) ;
        }

    }

}
