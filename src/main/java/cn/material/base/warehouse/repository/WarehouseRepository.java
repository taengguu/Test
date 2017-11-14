package cn.material.base.warehouse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.material.base.warehouse.entity.Warehouse;
import cn.material.common.base.BaseRepository;



/**
 * 
 * @author xjl
 *
 */

public interface WarehouseRepository extends BaseRepository<Warehouse, String>{
	
	
	//分页查询，由于是复杂查询，需要实现类
	
	  public Page<Warehouse> findWarehouses(Pageable pageable,Warehouse warehouse, boolean delFlag);
	  
	  @Query(value="from Warehouse w where w.serialNo =?1")
	  public List<Warehouse> findWarehouseBySerialNo(String serialNo);
	  
	  @Query(value="from Warehouse w where w.warehouseNo =?1")
	  public List<Warehouse> findWarehouseByWarehouseNo(int warehouseNo);
	  
	  
	  /**
	   * 获取warehouseNo的最大值
	   * @return
	   */
	  @Query(value="select max(w.warehouseNo) from Warehouse w")
	  public Integer getMaxWarehouseNo();
	  /**
	   * 获取serialNo的最大值
	   * @return
	   */
	  @Query(value="select max(w.serialNo) from Warehouse w")
	  public Integer getMaxSerialNo();
	  
	  
	  /**
	   * 查询所有楼层且根据序号排序
	   * 
	   * @return
	   */
	  @Query(value="select w from Warehouse w order by w.warehouseNo")
	  public List<Warehouse> findAndSrot();
	  
}
