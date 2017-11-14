package cn.material.base.sale.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.material.base.sale.entity.Sale;
import cn.material.base.sale.vo.SaleVo;
import cn.material.common.base.BaseRepository;

public interface SaleRepository extends BaseRepository<Sale, String>{

	
	 public Page<SaleVo> findSales(Pageable pageable,SaleVo saleVo, boolean delFlag);

	  @Query(value="select s from Sale s where s.serialNo =?1")
	  public List<Sale> findSaleBySerialNo(String serialNo);

}
