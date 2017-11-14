package cn.material.base.sale.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.sale.vo.SaleVo;
import cn.material.common.base.BaseRepositoryImpl;
import cn.material.common.constants.Constants;

public class SaleRepositoryImpl extends BaseRepositoryImpl{
	 public Page<SaleVo> findSales(Pageable pageable,SaleVo saleVo, boolean delFlag){
		 String xsql="select s.id as id, s.serial_no as serialNo, m.id as materialId, m.material_name as materialName, mb.id as memberId, "
					+ "mb.name as memberName, s.amount as amount, s.order_time as orderTime, s.sale_status as saleStatus, s.total_price as totalPrice, "
					+ "s.pay_type as payType, s.pay_time as payTime, "
					+ "s.remark as remark "
					+ "from sale_order s "
					+ "left join base_material m on s.material_id=m.id "
					+ "left join base_member mb on s.member_id=mb.id"
					+ " where s.del_flag =?0 "
					+ "/~ and s.serial_no like '%[serialNo]%'~/"
					+ "/~ and m.id = '[materialId]'~/"
					+ "/~ and mb.id = '[memberId]'~/"
					+ "/~ and s.total_price >= '%[minTotalPrice]%'~/"
					+ "/~ and s.total_price <= '%[maxTotalPrice]%'~/"
					+ "/~ and s.sale_status = '[saleStatus]'~/"
					+ "order by s.serial_no asc";
			Map<String, Object> filter = new HashMap<String, Object>();
			if (saleVo != null) {
				filter.put("serialNo", saleVo.getSerialNo());
				filter.put("materialId", saleVo.getMaterialId());
				filter.put("memberId", saleVo.getMemberId());
				filter.put("minTotalPrice", saleVo.getMinTotalPrice());
				filter.put("maxTotalPrice", saleVo.getMaxTotalPrice());
				filter.put("saleStatus", saleVo.getSaleStatus());

			}
			String sql = this.xsqlConvertSql(xsql, filter);
			System.out.println("sql---->" + sql);
			
			if(delFlag){
				return this.findBySql(pageable, sql, new Object[]{Constants.DIC_YES}, SaleVo.class);

			}
			return this.findBySql(pageable, sql, new Object[]{Constants.DIC_NO}, SaleVo.class);

	 }

}
