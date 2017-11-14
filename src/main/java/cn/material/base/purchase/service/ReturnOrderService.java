package cn.material.base.purchase.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.purchase.entity.ReturnOrder;
import cn.material.base.purchase.vo.ReturnOrderVo;
import cn.material.common.base.BaseService;

public interface ReturnOrderService extends BaseService<ReturnOrder, String>{

	public Page<ReturnOrderVo> findAllReturnOrders(Pageable pageable,ReturnOrderVo returnOrderVo, boolean delFlag);

	public void saveReturnOrderInfo(ReturnOrderVo returnOrderVo);
	public void updaReturnOrderInfo(ReturnOrderVo returnOrderVo);
	public void updateReturnOrder4isDelete(String[] ids);
	public boolean isExistReturnOrder(String serialNo);

	
}
