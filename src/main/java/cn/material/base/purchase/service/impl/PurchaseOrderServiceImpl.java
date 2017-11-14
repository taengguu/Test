package cn.material.base.purchase.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.material.base.material.entity.Material;
import cn.material.base.material.repository.MaterialRepository;
import cn.material.base.purchase.entity.PurchaseOrder;
import cn.material.base.purchase.repository.PurchaseOrderRepository;
import cn.material.base.purchase.service.PurchaseOrderService;
import cn.material.base.purchase.vo.PurchaseOrderVo;
import cn.material.common.base.BaseRepository;
import cn.material.common.base.BaseServiceImpl;
import cn.material.common.constants.Constants;
import cn.material.common.utils.CopyPropertiesUtils;

@Service
public class PurchaseOrderServiceImpl extends BaseServiceImpl<PurchaseOrder, String> implements PurchaseOrderService{



	@Resource
	private PurchaseOrderRepository purchaseRepository;
	@Resource
	private MaterialRepository	materialRepository;


	public Page<PurchaseOrderVo> findAllPurchaseOrders(Pageable pageable, PurchaseOrderVo purchaseVo, boolean delFlag) {
		// TODO Auto-generated method stub
		return purchaseRepository.findPurchases(pageable, purchaseVo, delFlag);
	}



	public void savePurchaseOrderInfo(PurchaseOrderVo purchaseOrderVo) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		CopyPropertiesUtils.copyPropertiesIgnoreNull(purchaseOrder, purchaseOrderVo);
		if (purchaseOrderVo.getMaterialId() != null && !"".equals(purchaseOrderVo.getMaterialId())) { // 设置楼层
			Material material = materialRepository.findOne(purchaseOrderVo.getMaterialId());
			purchaseOrder.setMaterial(material);
		}
		purchaseOrder.setOrderStatus("audit");
		this.purchaseRepository.save(purchaseOrder);
	}

	public void updaPurchaseOrderInfo(PurchaseOrderVo purchaseOrderVo) {
		if(purchaseOrderVo != null){
			PurchaseOrder purchaseOrder = this.findOne(purchaseOrderVo.getId());
			CopyPropertiesUtils.copyPropertiesIgnoreNull(purchaseOrder, purchaseOrderVo);
			Material material = purchaseOrder.getMaterial();
			if(material!=null){
				if(!StringUtils.equals(material.getId(), purchaseOrderVo.getMaterialId())){
					if(purchaseOrderVo.getMaterialId() == null){
						purchaseOrder.setMaterial(null);
					}else{
						purchaseOrder.setMaterial(this.materialRepository.findOne(purchaseOrderVo.getMaterialId()));
					}
				}
			}else{
				if(purchaseOrderVo.getMaterialId() == null){
					purchaseOrder.setMaterial(null);
				}else{
					purchaseOrder.setMaterial(this.materialRepository.findOne(purchaseOrderVo.getMaterialId()));
				}
			}
			
			this.save(purchaseOrder);
		}
		
	}

	public void updatePurchaseOrder4isDelete(String[] ids) {
		for(String id : ids){
			PurchaseOrder purchaseOrder = this.findOne(id);
			if(purchaseOrder != null){
				purchaseOrder.setDelFlag(purchaseOrder.getDelFlag() == Constants.DIC_NO ? Constants.DIC_YES : Constants.DIC_NO);
				this.save(purchaseOrder);
			}
		}
		
	}

	public boolean isExistPurchaseOrder(String serialNo) {
		List<PurchaseOrder> purchaseOrder = purchaseRepository.findPurchaseBySerialNo(serialNo);
		
		return purchaseOrder !=null && purchaseOrder.size() > 0;
	}

	@Override
	protected BaseRepository<PurchaseOrder, String> getRepository() {
		// TODO Auto-generated method stub
		return this.purchaseRepository;
	}




}
