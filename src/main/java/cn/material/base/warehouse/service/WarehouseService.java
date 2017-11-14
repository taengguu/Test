package cn.material.base.warehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.warehouse.entity.Warehouse;
import cn.material.common.base.BaseService;

public interface WarehouseService extends BaseService<Warehouse, String>{

	
	public Page<Warehouse> findAllWarehouses(Pageable pageable,Warehouse warehouse, boolean delFlag);

	public void  updateWarehouse(Warehouse warehouse);
	
	public void updateWarehouse4isDelete(String[] ids);
	
	public boolean isExistWarehouse(String serialNo);
	public Integer createSerialNo();
	public Integer createWarehouseNo();
	public boolean isExistwarehouseNo(String warehouseNo);
}
