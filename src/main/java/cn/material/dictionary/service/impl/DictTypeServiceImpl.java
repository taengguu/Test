package cn.material.dictionary.service.impl;

import cn.material.common.base.BaseRepository;
import cn.material.common.base.BaseServiceImpl;
import cn.material.dictionary.entity.DictType;
import cn.material.dictionary.repository.DictTypeRepository;
import cn.material.dictionary.service.DictTypeService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 数据字典服务实现
 * 
 * @author KAM
 *
 */
@Service
public class DictTypeServiceImpl extends BaseServiceImpl<DictType, String> implements DictTypeService {

	@Resource
	private DictTypeRepository dictTypeRepository;

	@Override
	protected BaseRepository<DictType, String> getRepository() {
		return this.dictTypeRepository;
	}

}
