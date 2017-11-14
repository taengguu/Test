package cn.material.base.purchase.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.material.base.purchase.entity.ReturnOrder;
import cn.material.base.purchase.vo.ReturnOrderVo;
import cn.material.common.base.BaseRepository;

public interface ReturnOrderRepository extends BaseRepository<ReturnOrder, String>{
	 public Page<ReturnOrderVo> findReturnOrders(Pageable pageable,ReturnOrderVo returnOrderVo, boolean delFlag);

	  @Query(value="select r from ReturnOrder r left join r.purchaseOrder p where  p.serialNo =?1")
	  public List<ReturnOrder> findReturnOrderBySerialNo(String serialNo);

}
