package cn.material.base.purchase.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.material.base.purchase.entity.PurchaseOrder;
import cn.material.base.purchase.entity.ReturnOrder;
import cn.material.base.purchase.repository.PurchaseOrderRepository;
import cn.material.base.purchase.repository.ReturnOrderRepository;
import cn.material.base.purchase.service.ReturnOrderService;
import cn.material.base.purchase.vo.ReturnOrderVo;
import cn.material.common.base.BaseRepository;
import cn.material.common.base.BaseServiceImpl;
import cn.material.common.constants.Constants;
import cn.material.common.utils.CopyPropertiesUtils;

@Service
public class ReturnOrderServiceImpl extends BaseServiceImpl<ReturnOrder, String> implements ReturnOrderService{

	@Resource
	private ReturnOrderRepository returnOrderRepository;
	@Resource
	private PurchaseOrderRepository purchaseOrderRepository;
	@Override
	protected BaseRepository<ReturnOrder, String> getRepository() {
		// TODO Auto-generated method stub
		return this.returnOrderRepository;
	}


	public void saveReturnOrderInfo(ReturnOrderVo returnOrderVo) {
		ReturnOrder returnOrder = new ReturnOrder();
		CopyPropertiesUtils.copyPropertiesIgnoreNull(returnOrder, returnOrderVo);
		if (returnOrderVo.getPurchaseOrderId() != null && !"".equals(returnOrderVo.getPurchaseOrderId())) { // 设置楼层
			PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(returnOrderVo.getPurchaseOrderId());
			returnOrder.setPurchaseOrder(purchaseOrder);
		}
		this.returnOrderRepository.save(returnOrder);
	}

	public void updaReturnOrderInfo(ReturnOrderVo returnOrderVo) {
		if(returnOrderVo != null){
			ReturnOrder returnOrder = this.findOne(returnOrderVo.getId());
			CopyPropertiesUtils.copyPropertiesIgnoreNull(returnOrder, returnOrderVo);
			PurchaseOrder purchaseOrder = returnOrder.getPurchaseOrder();
			if(purchaseOrder!=null){
				if(!StringUtils.equals(purchaseOrder.getId(), returnOrderVo.getPurchaseOrderId())){
					if(returnOrderVo.getPurchaseOrderId() == null){
						returnOrder.setPurchaseOrder(null);
					}else{
						returnOrder.setPurchaseOrder(this.purchaseOrderRepository.findOne(returnOrderVo.getPurchaseOrderId()));
					}
				}
			}else{
				if(returnOrderVo.getPurchaseOrderId() == null){
					returnOrder.setPurchaseOrder(null);
				}else{
					returnOrder.setPurchaseOrder(this.purchaseOrderRepository.findOne(returnOrderVo.getPurchaseOrderId()));
				}
			}
			
			this.save(returnOrder);
		}
		
	}

	public void updateReturnOrder4isDelete(String[] ids) {
		for(String id : ids){
			ReturnOrder returnOrder = this.findOne(id);
			if(returnOrder != null){
				returnOrder.setDelFlag(returnOrder.getDelFlag() == Constants.DIC_NO ? Constants.DIC_YES : Constants.DIC_NO);
				this.save(returnOrder);
			}
		}
		
	}

	public boolean isExistReturnOrder(String serialNo) {
		List<ReturnOrder> returnOrder = returnOrderRepository.findReturnOrderBySerialNo(serialNo);
		
		return returnOrder !=null && returnOrder.size() > 0;
	}


	public Page<ReturnOrderVo> findAllReturnOrders(Pageable pageable, ReturnOrderVo returnOrderVo, boolean delFlag) {
		// TODO Auto-generated method stub
		return returnOrderRepository.findReturnOrders(pageable, returnOrderVo, delFlag);
	}



}
