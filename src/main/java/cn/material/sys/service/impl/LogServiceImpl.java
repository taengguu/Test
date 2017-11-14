package cn.material.sys.service.impl;

import cn.material.common.base.BaseRepository;
import cn.material.common.base.BaseServiceImpl;
import cn.material.sys.entity.Log;
import cn.material.sys.repository.LogRepository;
import cn.material.sys.service.LogService;
import cn.material.sys.vo.LogVo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Mr.wang on 2016/12/13.
 */
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log, String> implements LogService {

    @Resource
    private LogRepository logRepo ;

    @Override
    protected BaseRepository<Log, String> getRepository() {
        return this.logRepo ;
    }

	/**
     * 分页查询
     */
	
	public Page<LogVo> findLogs(Pageable pageable, LogVo logVo) {
		// TODO Auto-generated method stub
		return this.logRepo.findLogInfoAll(pageable, logVo);
	}

	/**
	 * 删除日志信息
	 */
	
	public void deleteLogInfo(String[] ids) {
		// TODO Auto-generated method stub
		for(String id : ids){
			Log log=this.findOne(id);
			if(log !=null){
				this.delete(log);
			}
		}
	}


}
