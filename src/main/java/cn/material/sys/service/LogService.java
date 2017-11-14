package cn.material.sys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.common.base.BaseService;
import cn.material.sys.entity.Log;
import cn.material.sys.vo.LogVo;

/**
 * Created by Mr.wang on 2016/12/13.
 */
public interface LogService extends BaseService<Log, String> {
	
	
	/**
	 * 分页查询日志
	 * @param pageable
	 * @param log
	 * @return
	 */
	public Page<LogVo> findLogs(Pageable pageable, LogVo logVo) ;
	
	/**
	 * 删除一组日志信息
	 * @param ids
	 */
	public void deleteLogInfo(String ids[]);
}
