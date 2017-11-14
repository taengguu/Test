package cn.material.base.materialcategory.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.material.base.materialcategory.entity.MaterialCategory;
import cn.material.common.base.BaseRepository;

/**
 * 具体分类的持久层接口
 * 
 * @author xjl
 *
 */
public interface MaterialCategoryRepository extends BaseRepository<MaterialCategory, String> {
	/**
	 * 分页查询分类信息
	 * 
	 * @param pageable
	 *            分页请求
	 * @param materialCategory
	 *            查询参数
	 * @param delFlag
	 *            删除标记
	 * @return
	 */
	public Page<MaterialCategory> findMaterialCategorys(Pageable pageable, MaterialCategory materialCategory,
			boolean delFlag);

	/**
	 * 通过编号查询分类信息
	 * @param serialNo  查询条件 编号
	 * @return
	 */
	@Query(value = "select mc from MaterialCategory mc where mc.serialNo =?1")
	public List<MaterialCategory> findMaterialCategoryBySerialNo(String serialNo);

	/**
	 * 通过用途分类查询分类信息
	 * @param useCategory  查询条件 用途分类
	 * @return
	 */
	@Query(value = "select mc from MaterialCategory mc where mc.useCategory =?1")
	public List<MaterialCategory> findMaterialCategorybyUseCategory(String useCategory);
}
