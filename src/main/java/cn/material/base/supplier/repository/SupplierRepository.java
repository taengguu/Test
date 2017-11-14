package cn.material.base.supplier.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.material.base.supplier.entity.Supplier;
import cn.material.common.base.BaseRepository;

public interface SupplierRepository extends BaseRepository<Supplier, String>{
	 public Page<Supplier> findSuppliers(Pageable pageable,Supplier supplier, boolean delFlag);

	  @Query(value="select s from Supplier s where s.serialNo =?1")
	  public List<Supplier> findSupplierBySerialNo(String serialNo);
}
