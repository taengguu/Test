package cn.material.base.supplier.repository;


import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.supplier.entity.Supplier;
import cn.material.common.base.BaseRepositoryImpl;
import cn.material.common.constants.Constants;

/**
 * 分页查询的实现类
 * @author xjl
 *
 */

public class SupplierRepositoryImpl extends BaseRepositoryImpl{
	
	public Page<Supplier> findSuppliers(Pageable pageable,Supplier supplier, boolean delFlag){
		String xsql="select s.id as id, s.serial_no as serialNo, s.supplier_name as supplierName, s.supplier_address as supplierAddress, s.supplier_tel as supplierTel, "
				+ "s.supplier_email as supplierEmail, s.supplier_info as supplierInfo, s.remark as remark from base_supplier s "
				+ "where s.del_flag =?0 "
				+ "/~ and s.serial_no like '%[serialNo]%'~/"
				+ "/~ and s.supplier_name like '%[supplierName]%'~/"
				+ "/~ and s.supplier_address like '%[supplierAddress]%'~/"
				+ "order by s.serial_no asc";
		Map<String, Object> filter = new HashMap<String, Object>();
		if (supplier != null) {
			filter.put("serialNo", supplier.getSerialNo());
			filter.put("supplierName", supplier.getSupplierName());
			filter.put("supplierAddress", supplier.getSupplierAddress());
		}
		String sql = this.xsqlConvertSql(xsql, filter);
		System.out.println("sql---->" + sql);
		
		if(delFlag){
			return this.findBySql(pageable, sql, new Object[]{Constants.DIC_YES}, Supplier.class);

		}
		return this.findBySql(pageable, sql, new Object[]{Constants.DIC_NO}, Supplier.class);

	}
	


}
