package cn.material.base.purchase.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.material.base.material.entity.Material;
import cn.material.base.material.service.MaterialService;
import cn.material.base.material.vo.MaterialVo;
import cn.material.base.purchase.entity.PurchaseOrder;
import cn.material.base.purchase.service.PurchaseOrderService;
import cn.material.base.purchase.vo.PurchaseOrderVo;
import cn.material.base.supplier.entity.Supplier;
import cn.material.base.warehouse.entity.Warehouse;
import cn.material.common.page.JqGridModel;
import cn.material.common.page.PageUtils;
import cn.material.common.response.ResponseModel;
import cn.material.common.response.SimpleResponseModel;

@Controller
@RequestMapping(value="/base")
public class PurchaseOrderController {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(PurchaseOrder.class);

	
	@Resource
	private PurchaseOrderService purchaseService;
	@Resource
	private MaterialService materialService;
	

	@RequestMapping(value="/purchase/page")
	public String  PurchaseOrderPage (Model model){
		List<Material> materials =materialService.findAll(); 
		model.addAttribute("materials", materials);
	
		return "base/purchase/purchase";
	}
	
	/**
	 * 
	 * jqgrid插件方式查询供应商信息
	 */
	@ResponseBody
	@RequestMapping(value="/purchase",method=RequestMethod.GET)
	public JqGridModel<PurchaseOrderVo> findPurchaseOrder(HttpServletRequest request, PurchaseOrderVo purchaseVo){
		PageRequest pageRequest = PageUtils.getPage4JqGrid(request);
		Page<PurchaseOrderVo> page= this.purchaseService.findAllPurchaseOrders(pageRequest, purchaseVo, false);
		JqGridModel<PurchaseOrderVo> model = PageUtils.pageConvertJqGrid(page);
		return model;
	}
	
	
	/**
	 * 新建保存
	 * @param supplier
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/purchase",method=RequestMethod.POST)
	public ResponseModel<String> savePurchaseOrder(PurchaseOrderVo purchaseVo){
		ResponseModel<String> model= new SimpleResponseModel<String>();

		
		try{
			purchaseService.savePurchaseOrderInfo(purchaseVo);
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
	@RequestMapping(value="/purchase/{purchaseId}",method=RequestMethod.GET)
	public ResponseModel<Object> findPurchaseOrderById(@PathVariable("purchaseId") String id){
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			PurchaseOrder purchaseOrder = purchaseService.findOne(id);
		if(purchaseOrder!=null){
			
			    PurchaseOrderVo purchaseOrderVo = new PurchaseOrderVo();
				PropertyUtils.copyProperties(purchaseOrderVo, purchaseOrder);
				purchaseOrderVo.setMaterialId(purchaseOrder.getMaterial().getId());
				purchaseOrderVo.setMaterialModel(purchaseOrder.getMaterial().getMaterialModel());
				purchaseOrderVo.setMaterialName(purchaseOrder.getMaterial().getMaterialName());
				purchaseOrderVo.setCategoryId(purchaseOrder.getMaterial().getMaterialCategory().getId());
				purchaseOrderVo.setUseCategory(purchaseOrder.getMaterial().getMaterialCategory().getUseCategory());
				purchaseOrderVo.setSpecificCategory(purchaseOrder.getMaterial().getMaterialCategory().getSpecificCategory());
				purchaseOrderVo.setMaterialUnit(purchaseOrder.getMaterial().getMaterialUnit());
				purchaseOrderVo.setMaterialWholesalePrice(purchaseOrder.getMaterial().getWholesalePrice());
				model.setData(purchaseOrderVo);
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
	@RequestMapping(value="/purchase",method=RequestMethod.PUT)
	public ResponseModel<String> updatePurchaseOrder(PurchaseOrderVo purchaseOrderVo){
		ResponseModel<String> model = new SimpleResponseModel<String>();
		
		try{
			if(purchaseOrderVo!=null){
				purchaseService.updaPurchaseOrderInfo(purchaseOrderVo);
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
	@RequestMapping(value="/purchase",method=RequestMethod.PATCH)
	public ResponseModel<Object> updatePurchaseOrder4isDelete(String[] ids){
	
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			purchaseService.updatePurchaseOrder4isDelete(ids);
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
	
	@RequestMapping(value = "/purchase/validation/{serialNo}", method = RequestMethod.GET)
	@ResponseBody
	public boolean isExistPurchaseOrder(@PathVariable String serialNo) {

		boolean passFlag = false; // 校验通过标记。 true：通过。
		try {
			passFlag = !this.purchaseService.isExistPurchaseOrder(serialNo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			passFlag = false;
		}
		return passFlag;
	}


}
