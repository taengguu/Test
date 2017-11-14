package cn.material.base.sale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.material.base.material.entity.Material;
import cn.material.base.material.repository.MaterialRepository;
import cn.material.base.member.entity.Member;
import cn.material.base.member.repository.MemberRepository;
import cn.material.base.sale.entity.Sale;
import cn.material.base.sale.repository.SaleRepository;
import cn.material.base.sale.service.SaleService;
import cn.material.base.sale.vo.SaleVo;
import cn.material.common.base.BaseRepository;
import cn.material.common.base.BaseServiceImpl;
import cn.material.common.constants.Constants;
import cn.material.common.utils.CopyPropertiesUtils;

@Service
public class SaleServiceImpl extends BaseServiceImpl<Sale, String> implements SaleService{

	@Resource
	private SaleRepository saleRepository;
	@Resource
	private MaterialRepository materialRepository;
	@Resource
	private MemberRepository memberRepository;
	
	protected BaseRepository<Sale, String> getRepository() {
		// TODO Auto-generated method stub
		return this.saleRepository;
	}

	
	public Page<SaleVo> findAllSales(Pageable pageable, SaleVo saleVo, boolean delFlag) {
		return saleRepository.findSales(pageable,saleVo,delFlag);
	}
	
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateSale(Sale sale) {
		Sale sale1 = this.findOne(sale.getId());
		CopyPropertiesUtils.copyPropertiesIgnoreNull(sale1, sale);
		
		this.save(sale1);
		
	}
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateSale4isDelete(String[] ids) {
		for (String id : ids) {
			Sale sale = this.findOne(id);
			if (sale != null) {
				sale.setDelFlag(sale.getDelFlag() == Constants.DIC_YES ? Constants.DIC_NO : Constants.DIC_YES);
				this.save(sale);
			}
		}
		
	}

	public boolean isExistSale(String serialNo) {
		List<Sale> sales = saleRepository.findSaleBySerialNo(serialNo);
		return sales != null && sales.size() > 0;

	}
	
	public void saveSaleInfo(SaleVo saleVo) {

		Sale sale = new Sale();
		CopyPropertiesUtils.copyPropertiesIgnoreNull(sale, saleVo); // 设置属性
		if (saleVo.getMaterialId()!= null && !"".equals(saleVo.getMaterialId())) { // 设置楼层
			Material material = materialRepository.findOne(saleVo.getMaterialId());
			sale.setMaterial(material);
		}

		if (saleVo.getMemberId() != null && !"".equals(saleVo.getMemberId())) { // 设置客房类型
			Member member = memberRepository.findOne(saleVo.getMemberId());
			sale.setMember(member);
		}



		this.saleRepository.save(sale);
	}
	
	
	public void updaSaleInfo(SaleVo saleVo) {

		if (saleVo != null) {
			Sale sale = this.findOne(saleVo.getId());
			CopyPropertiesUtils.copyPropertiesIgnoreNull(sale, saleVo);
			Material material = sale.getMaterial();
			Member member = sale.getMember();
			if (material != null) { // 查询客房是否有所属楼层
				if (!StringUtils.equals(material.getId(), saleVo.getMaterialId())) { // 所属楼层是否跟修改后的一样
					if (saleVo.getMaterialId() == null) {
						sale.setMaterial(null);
					} else {
						sale.setMaterial((this.materialRepository.findOne(saleVo.getMaterialId())));
					}

				}
			} else {
				if (saleVo.getMaterialId() == null) {
					sale.setMaterial(null);
				} else {
					sale.setMaterial((this.materialRepository.findOne(saleVo.getMaterialId())));

				}
			}
			if (member != null) { // 查询客房是否有所属客房类型
				if (!StringUtils.equals(member.getId(), saleVo.getMemberId())) { // 所属客房类型是否跟修改后的一样
					if (saleVo.getMemberId() == null) {
						sale.setMember(null);
					} else {
						sale.setMember((this.memberRepository.findOne(saleVo.getMemberId())));
					}

				}
			} else {
				if (saleVo.getMemberId()  == null) {
					sale.setMember(null);
				} else {
					sale.setMember((this.memberRepository.findOne(saleVo.getMemberId())));
				}
			}

			this.save(sale);
		}
	}

	public void recoverSaleById(String[] ids){
		for(String id : ids){
			Sale sale = this.saleRepository.findOne(id);
			if(sale!=null){
				sale.setDelFlag(sale.getDelFlag()==Constants.DIC_YES ? Constants.DIC_YES : Constants.DIC_NO);;
				this.saleRepository.save(sale);
			}
		}
	}
	
}
