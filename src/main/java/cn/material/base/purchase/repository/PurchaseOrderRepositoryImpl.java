package cn.material.base.purchase.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.purchase.vo.PurchaseOrderVo;
import cn.material.common.base.BaseRepositoryImpl;
import cn.material.common.constants.Constants;
/**
 * 进货信息持久层接口的实现类
 * @author xjl
 *
 */
public class PurchaseOrderRepositoryImpl extends BaseRepositoryImpl {
	
	/**
	 * 分页查询xsql语句
	 * @param pageable 分页请求
	 * @param purchaselVo 查询参数
	 * @param delFlag  删除标记
	 * @return
	 */
	public Page<PurchaseOrderVo> findPurchases(Pageable pageable, PurchaseOrderVo purchaselVo, boolean delFlag) {
		String xsql = "select p.id as id, p.serial_no as serialNo, p.order_amount as orderAmount, p.order_status as orderStatus, p.application_date as applicationDate,p.audit_date as auditDate,"
				+ " p.applicant as applicant, p.auditor as auditor,p.order_total_price as orderTotalPrice, p.remark as remark,"
				+ " m.id as materialId, m.material_name as materialName, m.material_model as materialModel, m.material_unit as materialUnit,"
				+ " m.wholesale_price as materialWholesalePrice,"
				+ " mc.id as categoryId, mc.use_category as useCategory, mc.specific_category as specificCategory "
				+ "from purchase_order p left join base_material m on p.material_id = m.id "
				+ "left join base_material_category mc on m.category_id = mc.id " + "where p.del_flag =?0 "
				+ "/~ and p.serial_no like '%[serialNo]%'~/" + "/~ and m.id like '%[materialId]%'~/"
				+ "/~ and p.order_total_price >= [minOrderTotalPrice]~/"
				+ "/~ and p.order_total_price <= [maxOrderTotalPrice]~/" + "/~ and p.order_status ='[orderStatus]'~/"
				+ " order by p.serial_no asc";
		Map<String, Object> filter = new HashMap<String, Object>();
		if (purchaselVo != null) {
			filter.put("serialNo", purchaselVo.getSerialNo());
			filter.put("materialId", purchaselVo.getMaterialId());
			filter.put("minOrderTotalPrice", purchaselVo.getMinOrderTotalPrice());
			filter.put("maxOrderTotalPrice", purchaselVo.getMaxOrderTotalPrice());
			filter.put("orderStatus", purchaselVo.getOrderStatus());
		}
		String sql = this.xsqlConvertSql(xsql, filter);
		System.out.println("sql---->" + sql);

		if (delFlag) {
			return this.findBySql(pageable, sql, new Object[] { Constants.DIC_YES }, PurchaseOrderVo.class);

		}
		return this.findBySql(pageable, sql, new Object[] { Constants.DIC_NO }, PurchaseOrderVo.class);

	}

}
