package cn.material.base.warehouse.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.warehouse.entity.Warehouse;
import cn.material.common.base.BaseRepositoryImpl;
import cn.material.common.constants.Constants;

public class WarehouseRepositoryImpl extends BaseRepositoryImpl{
	
	
	/**
	 * 
	 * 分页查询
	 * @param pageable
	 * @param warehouse
	 * @param delFlag
	 * @return
	 */
	public Page<Warehouse> findWarehouses(Pageable pageable,Warehouse warehouse, boolean delFlag){
		String xsql="select w.id as id, w.serial_no as serialNo, w.warehouse_no as warehouseNo, w.warehouse_name as warehouseName, w.description as description from base_warehouse w "
				+ "where w.del_flag =?0 "
				+ "/~ and w.serial_no like '%[serialNo]%'~/"
				+ "/~ and w.warehouse_name like '%[warehouseName]%'~/"
				+ "order by w.serial_no asc";
		Map<String, Object> filter = new HashMap<String, Object>();
		if (warehouse != null) {
			filter.put("serialNo", warehouse.getSerialNo());
			filter.put("name", warehouse.getWarehouseName());
		}
		String sql = this.xsqlConvertSql(xsql, filter);
		System.out.println("sql---->" + sql);
		
		if(delFlag){
			return this.findBySql(pageable, sql, new Object[]{Constants.DIC_YES}, Warehouse.class);

		}
		return this.findBySql(pageable, sql, new Object[]{Constants.DIC_NO}, Warehouse.class);

	}
	

}
