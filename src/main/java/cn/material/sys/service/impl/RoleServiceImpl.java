package cn.material.sys.service.impl;

import cn.material.common.base.BaseRepository;
import cn.material.common.base.BaseServiceImpl;
import cn.material.common.exception.ServiceException;
import cn.material.sys.entity.Role;
import cn.material.sys.repository.RoleRepository;
import cn.material.sys.service.RoleService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by Mr.wang on 2016/12/2.
 */
@Service("roleService")
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements RoleService {

    @Resource
    private RoleRepository roleRepo ;

    
    protected BaseRepository<Role, String> getRepository() {
        return this.roleRepo ;
    }


    
    public Page<Role> findRoles(Pageable pageable, Role role) {
        return this.roleRepo.findRoles(pageable, role) ;
    }


    /**
     * 生成serialNo
     */
	
	public Integer createSerialNo() {
		// TODO Auto-generated method stub
		Integer csn;

		csn = this.roleRepo.getMaxSerialNo();// 获得serialNo最大值
		if (csn != null) { // 非空则最大值+1,空则为1
			csn = csn + 1;
		} else {
			csn = 1;
		}
		return csn;
	}

	/**
	 * 通过serialNo查询角色
	 */
	
	public List<Role> findRoleBySerialNo(int serialNo) {
		// TODO Auto-generated method stub
		List<Role> roles = null;
		try {
			roles = this.roleRepo.findRoleBySerialNo(serialNo);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return roles;
	}

	/**
	 * 检验serialNo是否存在
	 */
	
	public boolean isExistSerialNo(int serialNo) {
		// TODO Auto-generated method stub
		List<Role> roles=this.findRoleBySerialNo(serialNo);
    	return roles != null && roles.size() > 0;
	}
}
