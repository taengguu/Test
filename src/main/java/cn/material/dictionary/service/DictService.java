package cn.material.dictionary.service;

import cn.material.common.base.BaseService;
import cn.material.dictionary.entity.DictType;
import cn.material.dictionary.entity.Dictionary;

import java.util.List;

/**
 * 字典服务
 * 
 * @author KAM
 *
 */
public interface DictService extends BaseService<Dictionary, String> {
	/**
	 * 获得缓存字典
	 */
	public void getCacheDic();

	/**
	 * 根据字典类型查询字典
	 * 
	 * @param dictType
	 * @return
	 */
	public List<Dictionary> getByDictType(DictType dictType);

	/**
	 * 根据字典标识取值
	 * 
	 * @param mark
	 * @return
	 */
	public String dict(String mark);

	/**
	 * 根据字典类型标识取一组同类型的字典
	 * 
	 * @param typeMark
	 * @return
	 */
	public List<Dictionary> dictList(String typeMark);

}
