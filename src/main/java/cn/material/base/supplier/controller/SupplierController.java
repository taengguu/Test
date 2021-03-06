package cn.material.base.supplier.controller;

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

import cn.material.base.supplier.entity.Supplier;
import cn.material.base.supplier.service.SupplierService;

import cn.material.common.page.JqGridModel;
import cn.material.common.page.PageUtils;
import cn.material.common.response.ResponseModel;
import cn.material.common.response.SimpleResponseModel;

@Controller
@RequestMapping(value="/base")
public class SupplierController {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(Supplier.class);

	@Resource
	public SupplierService supplierService;
	
	
	@RequestMapping(value="/supplier/page")
	public String  SupplierPage (Model model){
		
		
		return "base/supplier/supplier";
	}
	
	/**
	 * 
	 * jqgrid插件方式查询供应商信息
	 */
	@ResponseBody
	@RequestMapping(value="/supplier",method=RequestMethod.GET)
	public JqGridModel<Supplier> findWarehouses(HttpServletRequest request, Supplier supplier){
		PageRequest pageRequest = PageUtils.getPage4JqGrid(request);
		Page<Supplier> page= this.supplierService.findAllSuppliers(pageRequest, supplier, false);
		JqGridModel<Supplier> model = PageUtils.pageConvertJqGrid(page);
		return model;
	}
	
	
	/**
	 * 新建保存
	 * @param supplier
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/supplier",method=RequestMethod.POST)
	public ResponseModel<String> saveWarehouse(Supplier supplier){
		ResponseModel<String> model= new SimpleResponseModel<String>();

		
		try{
			supplierService.save(supplier);
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
	@RequestMapping(value="/supplier/{supplierId}",method=RequestMethod.GET)
	public ResponseModel<Object> findWarehouseById(@PathVariable("supplierId") String id){
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			Supplier supplier = supplierService.findOne(id);
		if(supplier!=null){
			model.setData(supplier);
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
	@RequestMapping(value="/supplier",method=RequestMethod.PUT)
	public ResponseModel<String> updateWarehouse(Supplier supplier){
		ResponseModel<String> model = new SimpleResponseModel<String>();
		
		try{
			if(supplier!=null){
				supplierService.updateSupplier(supplier);
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
	@RequestMapping(value="/supplier",method=RequestMethod.PATCH)
	public ResponseModel<Object> updateWarehouse4isDelete(String[] ids){
	
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			supplierService.updateSupplier4isDelete(ids);
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
	
	@RequestMapping(value = "/supplier/validation/{serialNo}", method = RequestMethod.GET)
	@ResponseBody
	public boolean isExistSupplier(@PathVariable String serialNo) {

		boolean passFlag = false; // 校验通过标记。 true：通过。
		try {
			passFlag = !this.supplierService.isExistSupplier(serialNo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			passFlag = false;
		}
		return passFlag;
	}

}
