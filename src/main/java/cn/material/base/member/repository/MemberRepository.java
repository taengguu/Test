package cn.material.base.member.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.material.base.member.entity.Member;
import cn.material.common.base.BaseRepository;


public interface MemberRepository extends BaseRepository<Member, String>{
	
	 public Page<Member> findMembers(Pageable pageable,Member member, boolean delFlag);

	  @Query(value="select m from Member m where m.serialNo =?1")
	  public List<Member> findMemberBySerialNo(String serialNo);

}
