package cn.material.sys.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.common.base.BaseRepository;
import cn.material.sys.entity.Log;
import cn.material.sys.vo.LogVo;

/**
 * Created by Mr.wang on 2016/12/13.
 */
public interface LogRepository extends BaseRepository<Log, String> {

	/**
	 * 分页查询日志
	 * @param pageable
	 * @param log
	 * @return
	 */
	public Page<LogVo> findLogInfoAll(Pageable pageable,LogVo log);
}
