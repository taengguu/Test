package cn.material.base.warehouse.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.material.base.warehouse.entity.Warehouse;
import cn.material.base.warehouse.repository.WarehouseRepository;
import cn.material.base.warehouse.service.WarehouseService;
import cn.material.common.base.BaseRepository;
import cn.material.common.base.BaseServiceImpl;
import cn.material.common.constants.Constants;
import cn.material.common.utils.CopyPropertiesUtils;



@Service
public class WarehouseServiceImpl extends BaseServiceImpl<Warehouse, String> implements WarehouseService{
	
	@Resource
	private WarehouseRepository warehouseRepository;

	@Override
	protected BaseRepository<Warehouse, String> getRepository() {
		// TODO Auto-generated method stub
		return warehouseRepository;
	}

	public Page<Warehouse> findAllWarehouses(Pageable pageable, Warehouse warehouse, boolean delFlag) {
		// TODO Auto-generated method stub
		return warehouseRepository.findWarehouses(pageable, warehouse,delFlag);
	}
	
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateWarehouse(Warehouse warehouse) {
		Warehouse warehouse1 = warehouseRepository.findOne(warehouse.getId());
		CopyPropertiesUtils.copyPropertiesIgnoreNull(warehouse1, warehouse);
		
		this.save(warehouse1);
		
	}
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateWarehouse4isDelete(String[] ids) {
		for (String id : ids) {
			Warehouse warehouse = this.findOne(id);
			if (warehouse != null) {
				warehouse.setDelFlag(warehouse.getDelFlag() == Constants.DIC_YES ? Constants.DIC_NO : Constants.DIC_YES);
				this.save(warehouse);
			}
		}
		
	}
	/**
	 * 检查仓库编号是否存在
	 */
	public boolean isExistWarehouse(String serialNo) {
		List<Warehouse> warehouses=warehouseRepository.findWarehouseBySerialNo(serialNo);
		
		return warehouses != null && warehouses.size() > 0;
	}
	
	/**
	 * 检查仓库序号是否存在
	 */
	public boolean isExistwarehouseNo(String warehouseNo) {
		
		List<Warehouse> warehouses=warehouseRepository.findWarehouseByWarehouseNo(Integer.parseInt(warehouseNo));
		
		return warehouses != null && warehouses.size() > 0;
	}

	/**
	 * 生成serialNo,
	 * 
	 */
	public Integer createSerialNo() {
		// TODO Auto-generated method stub
		Integer csn;

			csn = this.warehouseRepository.getMaxSerialNo();// 获得serialNo最大值
			if (csn != null) { // 非空则最大值+1,空则为1
				csn = csn + 1;
			} else {
				csn = 1;
			}
		
			return csn;
		
	}
	
	/**
	 * 生成floorNo
	 */
	public Integer createWarehouseNo() {
		// TODO Auto-generated method stub
		Integer csn;

		csn = this.warehouseRepository.getMaxWarehouseNo();// 获得floorNo最大值
		if (csn != null) { // 非空则最大值+1,空则为1
			csn = csn + 1;
		} else {
			csn = 1;
		}
		
		return csn;
	}
}
