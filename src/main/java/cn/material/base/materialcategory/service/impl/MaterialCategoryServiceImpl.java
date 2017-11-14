package cn.material.base.materialcategory.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.material.base.material.entity.Material;
import cn.material.base.material.service.MaterialService;
import cn.material.base.materialcategory.entity.MaterialCategory;
import cn.material.base.materialcategory.repository.MaterialCategoryRepository;
import cn.material.base.materialcategory.service.MaterialCategoryService;
import cn.material.common.base.BaseRepository;
import cn.material.common.base.BaseServiceImpl;
import cn.material.common.constants.Constants;
import cn.material.common.utils.CopyPropertiesUtils;

/**
 * 分类信息service的实现类
 * @author xjl
 *
 */
@Service
public class MaterialCategoryServiceImpl extends BaseServiceImpl<MaterialCategory, String>
		implements MaterialCategoryService {

	@Resource
	private MaterialCategoryRepository materialCategoryRepository;

	@Override
	protected BaseRepository<MaterialCategory, String> getRepository() {
		return this.materialCategoryRepository;
	}

	public Page<MaterialCategory> findAllMaterialCategorys(Pageable pageable, MaterialCategory materialCategory,
			boolean delFlag) {
		return materialCategoryRepository.findMaterialCategorys(pageable, materialCategory, delFlag);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateMaterialCategory(MaterialCategory materialCategory) {
		MaterialCategory materialCategory1 = this.findOne(materialCategory.getId());
		CopyPropertiesUtils.copyPropertiesIgnoreNull(materialCategory1, materialCategory);
		this.save(materialCategory1);

	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateMaterialCategory4isDelete(String[] ids) {
		for (String id : ids) {
			MaterialCategory materialCategory = this.findOne(id);
			if (materialCategory != null) {
				materialCategory.setDelFlag(
						materialCategory.getDelFlag() == Constants.DIC_YES ? Constants.DIC_NO : Constants.DIC_YES);
				this.save(materialCategory);
			}
		}

	}

	public boolean isExistMaterialCategory(String serialNo) {
		List<MaterialCategory> materialCategorys = materialCategoryRepository.findMaterialCategoryBySerialNo(serialNo);
		return materialCategorys != null && materialCategorys.size() > 0;
	}

	public List<MaterialCategory> findMaterialCategorybyUseCategory(String useCategory) {
		// TODO Auto-generated method stub
		return this.materialCategoryRepository.findMaterialCategorybyUseCategory(useCategory);
	}

	
	
	public void recoverMaterialCategoryById(String[] ids){
		for(String id : ids){
			MaterialCategory materialCategory = this.materialCategoryRepository.findOne(id);
			if(materialCategory!=null){
				materialCategory.setDelFlag(materialCategory.getDelFlag()==Constants.DIC_YES ? Constants.DIC_YES : Constants.DIC_NO);;
				this.materialCategoryRepository.save(materialCategory);
			}
		}
	}
}
