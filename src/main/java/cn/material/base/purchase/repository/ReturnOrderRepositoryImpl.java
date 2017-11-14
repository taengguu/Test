package cn.material.base.purchase.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.purchase.vo.ReturnOrderVo;
import cn.material.common.base.BaseRepositoryImpl;
import cn.material.common.constants.Constants;

public class ReturnOrderRepositoryImpl extends BaseRepositoryImpl{
	 public Page<ReturnOrderVo> findReturnOrders(Pageable pageable,ReturnOrderVo returnOrderVo, boolean delFlag){
		 String xsql="select r.id as id, p.serial_no as purchaseOrderSerialNo, p.id as purchaseOrderId, r.return_status as returnStatus, r.principal as principal,r.return_time as returnTime,"
					+ " r.return_reason as returnReason "
					+ "from return_order r left join purchase_order p on r.purchase_order_id = p.id "
					+ "where r.del_flag =?0 "
					+ "/~ and p.serial_no like '%[purchaseOrderSerialNo]%'~/"
					+ " order by p.serial_no asc";
			Map<String, Object> filter = new HashMap<String, Object>();
			if (returnOrderVo != null) {
				filter.put("purchaseOrderSerialNo", returnOrderVo.getPurchaseOrderSerialNo());

		}
			String sql = this.xsqlConvertSql(xsql, filter);
			System.out.println("sql---->" + sql);
			
			if(delFlag){
				return this.findBySql(pageable, sql, new Object[]{Constants.DIC_YES}, ReturnOrderVo.class);

			}
			return this.findBySql(pageable, sql, new Object[]{Constants.DIC_NO}, ReturnOrderVo.class);

	 }

}
