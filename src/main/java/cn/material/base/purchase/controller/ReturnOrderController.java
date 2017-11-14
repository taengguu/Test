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
import cn.material.base.purchase.entity.PurchaseOrder;
import cn.material.base.purchase.entity.ReturnOrder;
import cn.material.base.purchase.service.PurchaseOrderService;
import cn.material.base.purchase.service.ReturnOrderService;
import cn.material.base.purchase.vo.ReturnOrderVo;
import cn.material.common.page.JqGridModel;
import cn.material.common.page.PageUtils;
import cn.material.common.response.ResponseModel;
import cn.material.common.response.SimpleResponseModel;


@RequestMapping(value="/base")
@Controller
public class ReturnOrderController {

	private static final Logger LOGGER= LoggerFactory.getLogger(ReturnOrder.class);

	
	@Resource
	private PurchaseOrderService purchaseOrderService;
	@Resource
	private ReturnOrderService returnOrderService;
	

	@RequestMapping(value="/return/page")
	public String  PurchaseOrderPage (Model model){
		List<PurchaseOrder> purchaseOrders =purchaseOrderService.findAll(); 
		model.addAttribute("purchaseOrders", purchaseOrders);
		System.out.println(purchaseOrders.size());
	
		
	
		return "base/purchase/return";
	}
	
	/**
	 * 
	 * jqgrid插件方式查询供应商信息
	 */
	@ResponseBody
	@RequestMapping(value="/return",method=RequestMethod.GET)
	public JqGridModel<ReturnOrderVo> findReturnOrder(HttpServletRequest request, ReturnOrderVo returnOrderVo){
		PageRequest pageRequest = PageUtils.getPage4JqGrid(request);
		Page<ReturnOrderVo> page= this.returnOrderService.findAllReturnOrders(pageRequest, returnOrderVo, false);
		JqGridModel<ReturnOrderVo> model = PageUtils.pageConvertJqGrid(page);
		return model;
	}
	
	
	/**
	 * 新建保存
	 * @param supplier
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/return",method=RequestMethod.POST)
	public ResponseModel<String> saveReturnOrder(ReturnOrderVo returnOrderVo){
		ResponseModel<String> model= new SimpleResponseModel<String>();

		
		try{
			returnOrderService.saveReturnOrderInfo(returnOrderVo);
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
	@RequestMapping(value="/return/{returnId}",method=RequestMethod.GET)
	public ResponseModel<Object> findReturnOrderById(@PathVariable("purchaseId") String id){
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			ReturnOrder returnOrder = returnOrderService.findOne(id);
		if(returnOrder!=null){
			
				    ReturnOrderVo returnOrderVo = new ReturnOrderVo();
					PropertyUtils.copyProperties(returnOrderVo, returnOrder);
					returnOrderVo.setPurchaseOrderId(returnOrder.getPurchaseOrder().getId());
					returnOrderVo.setPurchaseOrderSerialNo(returnOrder.getPurchaseOrder().getSerialNo());
					model.setData(returnOrderVo);
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
	@RequestMapping(value="/return",method=RequestMethod.PUT)
	public ResponseModel<String> updateReturnOrder(ReturnOrderVo returnOrderVo){
		ResponseModel<String> model = new SimpleResponseModel<String>();
		
		try{
			if(returnOrderVo!=null){
				returnOrderService.updaReturnOrderInfo(returnOrderVo);
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
	@RequestMapping(value="/return",method=RequestMethod.PATCH)
	public ResponseModel<Object> updateReturnOrder4isDelete(String[] ids){
	
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			returnOrderService.updateReturnOrder4isDelete(ids);
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
	
	@RequestMapping(value = "/return/validation/{serialNo}", method = RequestMethod.GET)
	@ResponseBody
	public boolean isExistPurchaseOrder(@PathVariable String serialNo) {

		boolean passFlag = false; // 校验通过标记。 true：通过。
		try {
			passFlag = !this.returnOrderService.isExistReturnOrder(serialNo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			passFlag = false;
		}
		return passFlag;
	}


}
