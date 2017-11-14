package cn.material.base.supplier.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.material.base.supplier.entity.Supplier;
import cn.material.base.supplier.repository.SupplierRepository;
import cn.material.base.supplier.service.SupplierService;
import cn.material.common.base.BaseRepository;
import cn.material.common.base.BaseServiceImpl;
import cn.material.common.constants.Constants;
import cn.material.common.utils.CopyPropertiesUtils;

@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier, String> implements SupplierService{

	@Resource
	private SupplierRepository supplierRepository;
	
	@Override
	protected BaseRepository<Supplier, String> getRepository() {
		return supplierRepository;
	}
	
	public Page<Supplier> findAllSuppliers(Pageable pageable, Supplier supplier, boolean delFlag) {
		return supplierRepository.findSuppliers(pageable,supplier,delFlag);
	}
	
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateSupplier(Supplier supplier) {
		Supplier supplier1 = this.findOne(supplier.getId());
		CopyPropertiesUtils.copyPropertiesIgnoreNull(supplier1, supplier);
		
		this.save(supplier1);
		
	}
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateSupplier4isDelete(String[] ids) {
		for (String id : ids) {
			Supplier supplier = this.findOne(id);
			if (supplier != null) {
				supplier.setDelFlag(supplier.getDelFlag() == Constants.DIC_YES ? Constants.DIC_NO : Constants.DIC_YES);
				this.save(supplier);
			}
		}
		
	}

	public boolean isExistSupplier(String serialNo) {
		List<Supplier> suppliers = supplierRepository.findSupplierBySerialNo(serialNo);
		return suppliers != null && suppliers.size() > 0;

	}

}
