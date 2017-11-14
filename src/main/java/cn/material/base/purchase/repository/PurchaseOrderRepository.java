package cn.material.base.purchase.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.material.base.purchase.entity.PurchaseOrder;
import cn.material.base.purchase.vo.PurchaseOrderVo;
import cn.material.common.base.BaseRepository;

/**
 * 进货信息的持久层接口
 * 
 * @author xjl
 *
 */
public interface PurchaseOrderRepository extends BaseRepository<PurchaseOrder, String> {
	/**
	 * 分页查询进货单信息
	 * @param pageable  分页请求
	 * @param purchaseVo  查询参数
	 * @param delFlag  删除标记
	 * @return
	 */
	public Page<PurchaseOrderVo> findPurchases(Pageable pageable, PurchaseOrderVo purchaseVo, boolean delFlag);
	
	/**
	 * 通过编号查询进货单的信息
	 * @param serialNo  进货单编号
	 * @return
	 */
	@Query(value = "select p from PurchaseOrder p where p.serialNo =?1")
	public List<PurchaseOrder> findPurchaseBySerialNo(String serialNo);
}
