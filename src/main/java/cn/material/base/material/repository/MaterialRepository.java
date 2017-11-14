package cn.material.base.material.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.material.base.material.entity.Material;
import cn.material.base.material.vo.MaterialVo;
import cn.material.common.base.BaseRepository;

public interface MaterialRepository extends BaseRepository<Material, String>{
	 public Page<MaterialVo> findMaterials(Pageable pageable,MaterialVo materialVo, boolean delFlag);

	  @Query(value="select m from Material m where m.serialNo =?1")
	  public List<Material> findMaterialBySerialNo(String serialNo);

}
