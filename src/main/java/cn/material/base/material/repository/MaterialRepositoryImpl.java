package cn.material.base.material.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.material.vo.MaterialVo;
import cn.material.common.base.BaseRepositoryImpl;
import cn.material.common.constants.Constants;

public class MaterialRepositoryImpl extends BaseRepositoryImpl{

	 public Page<MaterialVo> findMaterials(Pageable pageable,MaterialVo materialVo, boolean delFlag){
		 
			String xsql="select m.id as id, m.serial_no as serialNo, m.material_name as materialName,mc.id as categoryId, "
					+ "mc.use_category as useCategory, mc.specific_category as specificCategory, m.material_model as materialModel, "
					+ "m.material_unit as materialUnit, m.material_stock as materialStock, "
					+ "m.wholesale_price as wholesalePrice, m.retail_price as retailPrice, "
					+ "w.id as warehouseId, w.warehouse_name as warehouseName,"
					+ " s.id as supplierId, s.supplier_name as supplierName, m.remark as remark "
					+ "from base_material m "
					+ "left join base_warehouse w on m.warehouse_id = w.id "
					+ "left join base_supplier s on m.supplier_id = s.id "
					+ "left join base_material_category mc on m.category_id=mc.id "
					+ "where m.del_flag =?0 "
					+ "/~ and m.serial_no like '%[serialNo]%'~/"
					+ "/~ and m.material_name like '%[materialName]%'~/"
					+ "/~ and mc.use_category = '[useCategory]'~/"
					+ "/~ and mc.id = '[categoryId]'~/"
					+ "/~ and w.id = '[warehouseId]' ~/"
					+ "/~ and s.id = '[supplierId]'~/"
					+ "/~ and m.wholesale_price >= [minWholesalePrice] ~/"
					+ "/~ and m.wholesale_price <= [maxWholesalePrice] ~/"
					+ "order by s.serial_no asc";
			Map<String, Object> filter = new HashMap<String, Object>();
			if (materialVo != null) {
				filter.put("serialNo", materialVo.getSerialNo());
				filter.put("materialName", materialVo.getMaterialName());
				filter.put("useCategory", materialVo.getUseCategory());
				filter.put("categoryId", materialVo.getCategoryId());
				filter.put("warehouseId", materialVo.getWarehouseId());
				filter.put("supplierId", materialVo.getSupplierId());
				filter.put("minWholesalePrice", materialVo.getMinWholesalePrice());
				filter.put("maxWholesalePrice", materialVo.getMaxWholesalePrice());

			}
			String sql = this.xsqlConvertSql(xsql, filter);
			System.out.println("sql---->" + sql);
			
			if(delFlag){
				return this.findBySql(pageable, sql, new Object[]{Constants.DIC_YES}, MaterialVo.class);

			}
			return this.findBySql(pageable, sql, new Object[]{Constants.DIC_NO}, MaterialVo.class);

	 }
}
