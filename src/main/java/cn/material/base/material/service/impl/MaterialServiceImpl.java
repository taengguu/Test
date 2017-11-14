package cn.material.base.material.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.material.base.material.entity.Material;
import cn.material.base.material.repository.MaterialRepository;
import cn.material.base.material.service.MaterialService;
import cn.material.base.material.vo.MaterialVo;
import cn.material.base.materialcategory.entity.MaterialCategory;
import cn.material.base.materialcategory.repository.MaterialCategoryRepository;
import cn.material.base.supplier.entity.Supplier;
import cn.material.base.supplier.repository.SupplierRepository;
import cn.material.base.warehouse.entity.Warehouse;
import cn.material.base.warehouse.repository.WarehouseRepository;
import cn.material.common.base.BaseRepository;
import cn.material.common.base.BaseServiceImpl;
import cn.material.common.constants.Constants;
import cn.material.common.utils.CopyPropertiesUtils;


@Service
public class MaterialServiceImpl extends BaseServiceImpl<Material, String> implements MaterialService{

	@Resource
	private MaterialRepository materialRepository;
	@Resource
	private WarehouseRepository warehouseRepository;
	@Resource
	private SupplierRepository supplierRepository;
	@Resource
	private MaterialCategoryRepository materialCategoryRepository;
	
	@Override
	protected BaseRepository<Material, String> getRepository() {
		// TODO Auto-generated method stub
		return this.materialRepository;
	}

	public Page<MaterialVo> findAllMaterials(Pageable pageable, MaterialVo materialVo, boolean delFlag) {
		return materialRepository.findMaterials(pageable,materialVo,delFlag);
	}
	
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateMaterial(Material material) {
		Material material1 = this.findOne(material.getId());
		CopyPropertiesUtils.copyPropertiesIgnoreNull(material1, material);
		
		this.save(material1);
		
	}
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateMaterial4isDelete(String[] ids) {
		for (String id : ids) {
			Material material = this.findOne(id);
			if (material != null) {
				material.setDelFlag(material.getDelFlag() == Constants.DIC_YES ? Constants.DIC_NO : Constants.DIC_YES);
				this.save(material);
			}
		}
		
	}

	public boolean isExistMaterial(String serialNo) {
		List<Material> suppliers = materialRepository.findMaterialBySerialNo(serialNo);
		return suppliers != null && suppliers.size() > 0;

	}
	
	public void saveMaterialInfo(MaterialVo materialVo) {

		Material material = new Material();
		CopyPropertiesUtils.copyPropertiesIgnoreNull(material, materialVo); // 设置属性
		if (materialVo.getWarehouseId() != null && !"".equals(materialVo.getWarehouseId())) { // 设置楼层
			Warehouse warehouse = warehouseRepository.findOne(materialVo.getWarehouseId());
			material.setWarehouse(warehouse);
		}

		if (materialVo.getSupplierId() != null && !"".equals(materialVo.getSupplierId())) { // 设置客房类型
			Supplier supplier = supplierRepository.findOne(materialVo.getSupplierId());
			material.setSupplier(supplier);
		}

		if (materialVo.getCategoryId() != null && !"".equals(materialVo.getCategoryId())) { // 设置客房类型
			MaterialCategory materialCategory = materialCategoryRepository.findOne(materialVo.getCategoryId());
			material.setMaterialCategory(materialCategory);
		}

		this.materialRepository.save(material);
	}
	
	
	public void updaMaterialInfo(MaterialVo materialVo) {

		if (materialVo != null) {
			Material material = this.findOne(materialVo.getId());
			CopyPropertiesUtils.copyPropertiesIgnoreNull(material, materialVo);
			Warehouse warehouse = material.getWarehouse();
			Supplier supplier = material.getSupplier();
			if (warehouse != null) { // 查询客房是否有所属楼层
				if (!StringUtils.equals(warehouse.getId(), materialVo.getWarehouseId())) { // 所属楼层是否跟修改后的一样
					if (materialVo.getWarehouseId() == null) {
						material.setWarehouse(null);
					} else {
						material.setWarehouse((this.warehouseRepository.findOne(materialVo.getWarehouseId())));
					}

				}
			} else {
				if (materialVo.getWarehouseId() == null) {
					material.setWarehouse(null);
				} else {
					material.setWarehouse((this.warehouseRepository.findOne(materialVo.getWarehouseId())));
				}
			}
			if (supplier != null) { // 查询客房是否有所属客房类型
				if (!StringUtils.equals(supplier.getId(), materialVo.getSupplierId())) { // 所属客房类型是否跟修改后的一样
					if (materialVo.getSupplierId() == null) {
						material.setSupplier(null);
					} else {
						material.setSupplier((this.supplierRepository.findOne(materialVo.getSupplierId())));
					}

				}
			} else {
				if (materialVo.getSupplierId() == null) {
					material.setSupplier(null);
				} else {
					material.setSupplier((this.supplierRepository.findOne(materialVo.getSupplierId())));
				}
			}

			this.save(material);
		}
	}

	public void recoverMaterialById(String[] ids){
		for(String id : ids){
			Material material = this.materialRepository.findOne(id);
			if(material!=null){
				material.setDelFlag(material.getDelFlag()==Constants.DIC_YES ? Constants.DIC_YES : Constants.DIC_NO);;
				this.materialRepository.save(material);
			}
		}
	}
	
	
}
