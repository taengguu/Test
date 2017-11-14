package cn.material.base.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.material.base.member.entity.Member;
import cn.material.base.member.repository.MemberRepository;
import cn.material.base.member.service.MemberService;
import cn.material.base.supplier.entity.Supplier;
import cn.material.common.base.BaseRepository;
import cn.material.common.base.BaseServiceImpl;
import cn.material.common.constants.Constants;
import cn.material.common.utils.CopyPropertiesUtils;

@Service
public class MemberServiceImpl extends BaseServiceImpl<Member, String> implements MemberService{

	@Resource
	private MemberRepository memberRepository;
	
	@Override
	protected BaseRepository<Member, String> getRepository() {
		// TODO Auto-generated method stub
		return this.memberRepository;
	}

	public Page<Member> findAllMembers(Pageable pageable, Member member, boolean delFlag) {
		return memberRepository.findMembers(pageable,member,delFlag);
	}
	
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateMember(Member member) {
		Member member1 = this.findOne(member.getId());
		CopyPropertiesUtils.copyPropertiesIgnoreNull(member1, member);
		
		this.save(member1);
		
	}
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateMember4isDelete(String[] ids) {
		for (String id : ids) {
			Member member = this.findOne(id);
			if (member != null) {
				member.setDelFlag(member.getDelFlag() == Constants.DIC_YES ? Constants.DIC_NO : Constants.DIC_YES);
				this.save(member);
			}
		}
		
	}

	public boolean isExistMember(String serialNo) {
		List<Member> members = memberRepository.findMemberBySerialNo(serialNo);
		return members != null && members.size() > 0;

	}
	
	
}
