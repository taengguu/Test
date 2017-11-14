package cn.material.base.material.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.material.entity.Material;
import cn.material.base.material.vo.MaterialVo;
import cn.material.common.base.BaseService;

public interface MaterialService extends BaseService<Material, String>{
	
	public Page<MaterialVo> findAllMaterials(Pageable pageable,MaterialVo materialVO, boolean delFlag);

	public void  updateMaterial(Material material);
	public void saveMaterialInfo(MaterialVo materialVo);
	public void updaMaterialInfo(MaterialVo materialVo);
	public void updateMaterial4isDelete(String[] ids);
	public boolean isExistMaterial(String serialNo);
	public void recoverMaterialById(String[] ids);

}
