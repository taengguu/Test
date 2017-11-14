package cn.material.base.member.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.material.base.member.entity.Member;
import cn.material.common.base.BaseService;

public interface MemberService extends BaseService<Member, String> {

	public Page<Member> findAllMembers(Pageable pageable,Member member, boolean delFlag);

	public void  updateMember(Member member);
	
	public void updateMember4isDelete(String[] ids);
	public boolean isExistMember(String serialNo);
	
}
