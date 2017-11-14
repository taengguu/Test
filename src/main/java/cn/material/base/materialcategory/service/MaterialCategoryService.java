package cn.material.base.materialcategory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.materialcategory.entity.MaterialCategory;
import cn.material.common.base.BaseService;

/**
 * 具体分类的service
 * @author xjl
 *
 */
public interface MaterialCategoryService extends BaseService<MaterialCategory, String>{
	
	/**
	 * 分页查询
	 * @param pageable 分页请求
	 * @param materialCategory 查询的内容
	 * @param delFlag  删除标记
	 * @return
	 */
	public Page<MaterialCategory> findAllMaterialCategorys(Pageable pageable,MaterialCategory materialCategory, boolean delFlag);

	/**
	 * 更新具体分类的信息
	 * @param materialCategory  具体分类信息载体
	 */
	public void  updateMaterialCategory(MaterialCategory materialCategory);
	
	/**
	 * 伪删一组数据
	 * @param ids  一组id
	 */
	public void updateMaterialCategory4isDelete(String[] ids);
	
	/**
	 * 通过编号检查具体分类是否存在
	 * @param serialNo  具体分类的编号
	 * @return
	 */
	public boolean isExistMaterialCategory(String serialNo);
	
	/**
	 * 通过用途分类查询具体分类信息
	 * @param useCategory 用途分类
	 * @return
	 */
	public List<MaterialCategory> findMaterialCategorybyUseCategory(String useCategory);

	/**
	 * 恢复一组被删的信息
	 * @param ids 一组id
	 */
	public void recoverMaterialCategoryById(String[] ids);
}
