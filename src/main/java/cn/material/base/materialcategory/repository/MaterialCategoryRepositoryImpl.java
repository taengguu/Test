package cn.material.base.materialcategory.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.materialcategory.entity.MaterialCategory;
import cn.material.common.base.BaseRepositoryImpl;
import cn.material.common.constants.Constants;


/**
 * 具体分类的持久层接口的实现类
 * @author xjl
 *
 */
public class MaterialCategoryRepositoryImpl extends BaseRepositoryImpl{

	/**
	 * 分页查询 xsql
	 * @param pageable   分页请求
	 * @param materialCategory   查询的参数
	 * @param delFlag   删除标记
	 * @return
	 */
	 public Page<MaterialCategory> findMaterialCategorys(Pageable pageable,MaterialCategory materialCategory, boolean delFlag){
		 
		 String xsql="select mc.id as id, mc.serial_no as serialNo, mc.use_category as useCategory, mc.specific_category as specificCategory, mc.decription as decription "
		 		+ "from base_material_category mc "
					+ "where mc.del_flag =?0 "
					+ "/~ and mc.serial_no like '%[serialNo]%'~/"
					+ "/~ and mc.use_category = '[useCategory]'~/"
					+ "/~ and mc.specific_category like '%[specificCategory]%'~/"
					+ "order by mc.serial_no asc";
			Map<String, Object> filter = new HashMap<String, Object>();
			if (materialCategory != null) {
				filter.put("serialNo", materialCategory.getSerialNo());
				filter.put("useCategory", materialCategory.getUseCategory());
				filter.put("specificCategory", materialCategory.getSpecificCategory());
	
			}
			String sql = this.xsqlConvertSql(xsql, filter);
			System.out.println("sql---->" + sql);
			if(delFlag){
				return this.findBySql(pageable, sql, new Object[]{Constants.DIC_YES}, MaterialCategory.class);
			}
			return this.findBySql(pageable, sql, new Object[]{Constants.DIC_NO}, MaterialCategory.class);

	 }

}
