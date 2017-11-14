package cn.material.base.sale.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.sale.entity.Sale;
import cn.material.base.sale.vo.SaleVo;
import cn.material.common.base.BaseService;

public interface SaleService extends BaseService<Sale, String>{
	public Page<SaleVo> findAllSales(Pageable pageable,SaleVo saleVo, boolean delFlag);

	public void  updateSale(Sale sale);
	public void saveSaleInfo(SaleVo saleVo);
	public void updaSaleInfo(SaleVo saleVo);
	public void updateSale4isDelete(String[] ids);
	public boolean isExistSale(String serialNo);
	public void recoverSaleById(String[] ids);

	
	
}
