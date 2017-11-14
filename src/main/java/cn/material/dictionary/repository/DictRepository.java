package cn.material.dictionary.repository;

import cn.material.common.base.BaseRepository;
import cn.material.dictionary.entity.DictType;
import cn.material.dictionary.entity.Dictionary;

import java.util.List;

/**
 * 字典持久层
 * 
 * @author KAM
 *
 */
public interface DictRepository extends BaseRepository<Dictionary, String> {
	/**
	 * 根据字典类型查询所有字典
	 * 
	 * @param dictType
	 * @return
	 */
	public List<Dictionary> findByDictType(DictType dictType);
}
