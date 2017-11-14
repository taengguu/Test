package cn.material.base.sale.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.material.base.material.entity.Material;
import cn.material.base.material.service.MaterialService;
import cn.material.base.member.entity.Member;
import cn.material.base.member.service.MemberService;
import cn.material.base.sale.entity.Sale;
import cn.material.base.sale.service.SaleService;
import cn.material.base.sale.vo.SaleVo;
import cn.material.common.page.JqGridModel;
import cn.material.common.page.PageUtils;
import cn.material.common.response.BatchResponseModel;
import cn.material.common.response.ResponseModel;
import cn.material.common.response.SimpleResponseModel;

@Controller
@RequestMapping(value="/base")
public class SaleController {
	private static final Logger LOGGER= LoggerFactory.getLogger(Sale.class);


	@Resource
	private SaleService saleService;

	@Resource
	private MaterialService materialService;
	@Resource
	private MemberService memberService;
	
	
	/**
	 * 跳转到材料销售管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/sale/page")
	public String  SalePage (Model model){
		List<Material> materials=materialService.findAll();
		model.addAttribute("materials", materials);
		List<Member> members =memberService.findAll(); 
		model.addAttribute("members", members);
		
		return "base/sale/sale";
	}
	
	/**
	 * 
	 * jqgrid插件方式查询材料基本信息
	 */
	@ResponseBody
	@RequestMapping(value="/sale",method=RequestMethod.GET)
	public JqGridModel<SaleVo> findSales(HttpServletRequest request, SaleVo saleVo){
		PageRequest pageRequest = PageUtils.getPage4JqGrid(request);
		Page<SaleVo> page= this.saleService.findAllSales(pageRequest, saleVo, false);
		JqGridModel<SaleVo> model = PageUtils.pageConvertJqGrid(page);
		return model;
	}
	
	
	/**
	 * 新建保存材料信息
	 * @param materialVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sale",method=RequestMethod.POST)
	public ResponseModel<String> saveSale(SaleVo saleVo){
		ResponseModel<String> model= new SimpleResponseModel<String>();

		
		try{
			saleService.saveSaleInfo(saleVo);
			model.success();
		}catch(Exception e){
			LOGGER.error("添加仓库发生异常", e);
			model.error();
		}
	
		
		return model;
	}
	
	/**
	 * 返回要将要修改的材料基本信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sale/{saleId}",method=RequestMethod.GET)
	public ResponseModel<Object> findSaleById(@PathVariable("saleId") String id){
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			Sale sale = saleService.findOne(id);
		if(sale!=null){
			
				SaleVo salevo = new SaleVo();
				PropertyUtils.copyProperties(salevo, sale);
				salevo.setMaterialId(sale.getMaterial().getId());
				salevo.setMaterialName(sale.getMaterial().getMaterialName());
				salevo.setMemberId(sale.getMember().getId());
				salevo.setMemberName(sale.getMember().getName());
				model.setData(salevo);
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
	@RequestMapping(value="/sale",method=RequestMethod.PUT)
	public ResponseModel<String> updateSale(SaleVo saleVo){
		ResponseModel<String> model = new SimpleResponseModel<String>();
		
		try{
			if(saleVo!=null){
				saleService.updaSaleInfo(saleVo);
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
	@RequestMapping(value="/sale",method=RequestMethod.PATCH)
	public ResponseModel<Object> updateSale4isDelete(String[] ids){
	
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			saleService.updateSale4isDelete(ids);
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
	
	/**
	 * 检验材料信息是否存在
	 * @param serialNo
	 * @return
	 */
	@RequestMapping(value = "/sale/validation/{serialNo}", method = RequestMethod.GET)
	@ResponseBody
	public boolean isExistSale(@PathVariable String serialNo) {

		boolean passFlag = false; // 校验通过标记。 true：通过。
		try {
			passFlag = !this.saleService.isExistSale(serialNo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			passFlag = false;
		}
		return passFlag;
	}
	

	


}
