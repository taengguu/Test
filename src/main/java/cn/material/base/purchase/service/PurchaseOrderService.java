package cn.material.base.purchase.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.purchase.entity.PurchaseOrder;
import cn.material.base.purchase.vo.PurchaseOrderVo;
import cn.material.common.base.BaseService;

/**
 * 进货单的service层
 * @author xjl
 *
 */
public interface PurchaseOrderService extends BaseService<PurchaseOrder, String>{
	
	public Page<PurchaseOrderVo> findAllPurchaseOrders(Pageable pageable,PurchaseOrderVo purchaseOrderVo, boolean delFlag);

	public void savePurchaseOrderInfo(PurchaseOrderVo purchaseOrderVo);
	public void updaPurchaseOrderInfo(PurchaseOrderVo purchaseOrderVo);
	public void updatePurchaseOrder4isDelete(String[] ids);
	public boolean isExistPurchaseOrder(String serialNo);

}
