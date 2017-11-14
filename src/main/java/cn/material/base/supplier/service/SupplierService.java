package cn.material.base.supplier.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.supplier.entity.Supplier;
import cn.material.common.base.BaseService;

public interface SupplierService extends BaseService<Supplier, String>{
	public Page<Supplier> findAllSuppliers(Pageable pageable,Supplier supplier, boolean delFlag);

	public void  updateSupplier(Supplier supplier);
	
	public void updateSupplier4isDelete(String[] ids);
	public boolean isExistSupplier(String serialNo);
}
