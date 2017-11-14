package cn.material.base.member.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.member.entity.Member;
import cn.material.common.base.BaseRepositoryImpl;
import cn.material.common.constants.Constants;

public class MemberRepositoryImpl extends BaseRepositoryImpl{
	
	public Page<Member> findMembers(Pageable pageable,Member member, boolean delFlag){
		String xsql="select m.id as id, m.serial_no as serialNo, m.name as name, m.sex as sex, m.password as password, m.mobile_phone as mobilePhone, m.address as address, m.E_mail as eMail, m.integrl as integrl,"
				+ " m.last_purchase_time as lastPurchaseTime from base_member m "
				+ "where m.del_flag =?0 "
				+ "/~ and m.serial_no like '%[serialNo]%'~/"

				+ "order by m.serial_no asc";
		Map<String, Object> filter = new HashMap<String, Object>();
		if (member != null) {
			filter.put("serialNo", member.getSerialNo());

		}
		String sql = this.xsqlConvertSql(xsql, filter);
		System.out.println("sql---->" + sql);
		
		if(delFlag){
			return this.findBySql(pageable, sql, new Object[]{Constants.DIC_YES}, Member.class);

		}
		return this.findBySql(pageable, sql, new Object[]{Constants.DIC_NO}, Member.class);

	}

}
