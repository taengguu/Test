package cn.material.base.warehouse.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

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

import cn.material.base.warehouse.entity.Warehouse;
import cn.material.base.warehouse.service.WarehouseService;
import cn.material.common.constants.Constants;
import cn.material.common.page.JqGridModel;
import cn.material.common.page.PageUtils;
import cn.material.common.response.ResponseModel;
import cn.material.common.response.SimpleResponseModel;
import cn.material.common.utils.CopyPropertiesUtils;
import groovyjarjarasm.asm.commons.Method;

@Controller
@RequestMapping("/base")
public class WarehouseController {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(WarehouseController.class);

	@Resource
	public WarehouseService warehouseService;
	
	
	@RequestMapping(value="/warehouse/page")
	public String  WarehousePage (Model model){
		
		
		return "base/warehouse/warehouse";
	}
	
	/**
	 * 
	 * jqgrid插件方式查询客房信息
	 */
	@ResponseBody
	@RequestMapping(value="/warehouse",method=RequestMethod.GET)
	public JqGridModel<Warehouse> findWarehouses(HttpServletRequest request, Warehouse warehouse){
		PageRequest pageRequest = PageUtils.getPage4JqGrid(request);
		Page<Warehouse> page= this.warehouseService.findAllWarehouses(pageRequest, warehouse, false);
		JqGridModel<Warehouse> model = PageUtils.pageConvertJqGrid(page);
		return model;
	}
	/**
	 * 获得生成serialNo的值
	 * @return
	 */
	@RequestMapping(value="/warehouse/getSerialNo", method =RequestMethod.GET)
	@ResponseBody
	public int getSerialNo(){
		return this.warehouseService.createSerialNo();
	}
	
	/**
	 * 获得生成floorNo的值
	 * @return
	 */
	@RequestMapping(value="/warehouse/getWarehouseNo", method =RequestMethod.GET)
	@ResponseBody
	public int getWarehouseNo(){
		return this.warehouseService.createWarehouseNo();
	}
	
	/**
	 * 新建保存
	 * @param warehouse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/warehouse",method=RequestMethod.POST)
	public ResponseModel<String> saveWarehouse(Warehouse warehouse){
		ResponseModel<String> model= new SimpleResponseModel<String>();

		
		try{
			warehouseService.save(warehouse);
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
	@RequestMapping(value="/warehouse/{warehouseId}",method=RequestMethod.GET)
	public ResponseModel<Object> findWarehouseById(@PathVariable("warehouseId") String id){
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
		Warehouse warehouse = warehouseService.findOne(id);
		if(warehouse!=null){
			model.setData(warehouse);
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
	@RequestMapping(value="/warehouse",method=RequestMethod.PUT)
	public ResponseModel<String> updateWarehouse(Warehouse warehouse){
		ResponseModel<String> model = new SimpleResponseModel<String>();
		
		try{
			if(warehouse!=null){
		warehouseService.updateWarehouse(warehouse);
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
	@RequestMapping(value="/warehouse",method=RequestMethod.PATCH)
	public ResponseModel<Object> updateWarehouse4isDelete(String[] ids){
	
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
            warehouseService.updateWarehouse4isDelete(ids);
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
	 * 查询仓库编号是否存在
	 * @param serialNo
	 * @return
	 */
	@RequestMapping(value = "/warehouse/validation/{serialNo}", method = RequestMethod.GET)
	@ResponseBody
	public boolean isExistWarehouse(@PathVariable String serialNo) {

		boolean passFlag = false; // 校验通过标记。 true：通过。
		try {
			passFlag = !this.warehouseService.isExistWarehouse(serialNo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			passFlag = false;
		}
		return passFlag;
	}

	/**
	 * 查询仓库序号是否存在
	 * @param serialNo
	 * @return
	 */
	@RequestMapping(value = "/warehouse/validationo/{warehouseNo}", method = RequestMethod.GET)
	@ResponseBody
	public boolean isExistWarehouseByWarehouseNo(@PathVariable String warehouseNo) {

		boolean passFlag = false; // 校验通过标记。 true：通过。
		try {
			passFlag = !this.warehouseService.isExistwarehouseNo(warehouseNo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			passFlag = false;
		}
		return passFlag;
	}
	
}
