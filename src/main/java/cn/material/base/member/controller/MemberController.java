package cn.material.base.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.material.base.member.entity.Member;
import cn.material.base.member.service.MemberService;
import cn.material.base.supplier.entity.Supplier;
import cn.material.common.page.JqGridModel;
import cn.material.common.page.PageUtils;
import cn.material.common.response.ResponseModel;
import cn.material.common.response.SimpleResponseModel;

@RequestMapping(value="/base")
@Controller
public class MemberController {
	private static final Logger LOGGER= LoggerFactory.getLogger(Member.class);
	@Resource
	private MemberService memberService;
	

	@RequestMapping(value="/member/page")
	public String  memberPage (Model model){
		
		
		return "base/member/member";
	}
	
	/**
	 * 
	 * jqgrid插件方式查询供应商信息
	 */
	@ResponseBody
	@RequestMapping(value="/member",method=RequestMethod.GET)
	public JqGridModel<Member> findMembers(HttpServletRequest request, Member member){
		PageRequest pageRequest = PageUtils.getPage4JqGrid(request);
		Page<Member> page= this.memberService.findAllMembers(pageRequest, member, false);
		JqGridModel<Member> model = PageUtils.pageConvertJqGrid(page);
		return model;
	}
	
	
	/**
	 * 新建保存
	 * @param supplier
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/member",method=RequestMethod.POST)
	public ResponseModel<String> saveMember(Member member){
		ResponseModel<String> model= new SimpleResponseModel<String>();

		
		try{
			memberService.save(member);
			model.success();
		}catch(Exception e){
			LOGGER.error("添加仓库发生异常", e);
			model.error();
		}
	
		
		return model;
	}
	
	/**
	 * 通过id查询，回显信息
	 */
	@ResponseBody
	@RequestMapping(value="/member/{memberId}",method=RequestMethod.GET)
	public ResponseModel<Object> findMemberById(@PathVariable("memberId") String id){
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			Member member = memberService.findOne(id);
		if(member!=null){
			model.setData(member);
		}
		model.success();
		}catch(Exception e){
			LOGGER.error("查询出错",e);
		}
		return model;
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/member",method=RequestMethod.PUT)
	public ResponseModel<String> updateMember(Member member){
		ResponseModel<String> model = new SimpleResponseModel<String>();
		
		try{
			if(member!=null){
				memberService.updateMember(member);
				model.success();
			}else{
				model.error("更新失败");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(),e);
			model.error("更新失败");
		}
		
		
		return model;
	}
	
	/**
	 * 进行伪删除操作
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/member",method=RequestMethod.PATCH)
	public ResponseModel<Object> updateMember4isDelete(String[] ids){
	
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			memberService.updateMember4isDelete(ids);
            model.success() ;
        }catch(DataIntegrityViolationException e){
        	model.error();
        	LOGGER.error(e.getMessage(),e) ;
        }
        catch (Exception e) {
            model.error() ;
            LOGGER.error(e.getMessage(),e) ;
        }
		
		return model;
		
	}
	
	@RequestMapping(value = "/member/validation/{serialNo}", method = RequestMethod.GET)
	@ResponseBody
	public boolean isExistMember(@PathVariable String serialNo) {

		boolean passFlag = false; // 校验通过标记。 true：通过。
		try {
			passFlag = !this.memberService.isExistMember(serialNo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			passFlag = false;
		}
		return passFlag;
	}
	
}
