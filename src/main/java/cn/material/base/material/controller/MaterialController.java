package cn.material.base.material.controller;

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
import cn.material.base.material.vo.MaterialVo;
import cn.material.base.materialcategory.entity.MaterialCategory;
import cn.material.base.materialcategory.service.MaterialCategoryService;
import cn.material.base.supplier.entity.Supplier;
import cn.material.base.supplier.service.SupplierService;
import cn.material.base.warehouse.entity.Warehouse;
import cn.material.base.warehouse.service.WarehouseService;
import cn.material.common.page.JqGridModel;
import cn.material.common.page.PageUtils;
import cn.material.common.response.BatchResponseModel;
import cn.material.common.response.ResponseModel;
import cn.material.common.response.SimpleResponseModel;
  
/**
 * 材料基本信息的controller
 * @author xjl
 *
 */

@Controller
@RequestMapping(value="/base")
public class MaterialController {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(Supplier.class);


	@Resource
	private MaterialService materialService;

	@Resource
	private WarehouseService warehouseService;
	@Resource
	private SupplierService supplierService;
	@Resource
	private MaterialCategoryService materialCategoryService;
	
	
	/**
	 * 跳转到材料基本信息管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/material/page")
	public String  MaterialPage (Model model){
		List<MaterialCategory> materialCategorys=materialCategoryService.findAll();
		model.addAttribute("materialCategorys", materialCategorys);
		List<Supplier> suppliers =supplierService.findAll(); 
		model.addAttribute("suppliers", suppliers);
		List<Warehouse> warehouses =warehouseService.findAll(); 
		model.addAttribute("warehouses", warehouses);
		
		return "base/material/material";
	}
	
	/**
	 * 
	 * jqgrid插件方式查询材料基本信息
	 */
	@ResponseBody
	@RequestMapping(value="/material",method=RequestMethod.GET)
	public JqGridModel<MaterialVo> findMaterials(HttpServletRequest request, MaterialVo materialVo){
		PageRequest pageRequest = PageUtils.getPage4JqGrid(request);
		Page<MaterialVo> page= this.materialService.findAllMaterials(pageRequest, materialVo, false);
		JqGridModel<MaterialVo> model = PageUtils.pageConvertJqGrid(page);
		return model;
	}
	
	
	/**
	 * 新建保存材料信息
	 * @param materialVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/material",method=RequestMethod.POST)
	public ResponseModel<String> saveMaterial(MaterialVo materialVo){
		ResponseModel<String> model= new SimpleResponseModel<String>();

		
		try{
			materialService.saveMaterialInfo(materialVo);
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
	@RequestMapping(value="/material/{materialId}",method=RequestMethod.GET)
	public ResponseModel<Object> findMaterialById(@PathVariable("materialId") String id){
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			Material material = materialService.findOne(id);
		if(material!=null){
			
				MaterialVo materialvo = new MaterialVo();
				PropertyUtils.copyProperties(materialvo, material);
				materialvo.setWarehouseName(material.getWarehouse().getWarehouseName());
				materialvo.setWarehouseId(material.getWarehouse().getId());
				materialvo.setSupplierName(material.getSupplier().getSupplierName());
				materialvo.setSupplierId(material.getSupplier().getId());
				materialvo.setCategoryId(material.getMaterialCategory().getId());
				materialvo.setUseCategory(material.getMaterialCategory().getUseCategory());
				materialvo.setSpecificCategory(material.getMaterialCategory().getSpecificCategory());
				model.setData(materialvo);
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
	@RequestMapping(value="/material",method=RequestMethod.PUT)
	public ResponseModel<String> updateMaterial(MaterialVo materialVo){
		ResponseModel<String> model = new SimpleResponseModel<String>();
		
		try{
			if(materialVo!=null){
				materialService.updaMaterialInfo(materialVo);
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
	@RequestMapping(value="/material",method=RequestMethod.PATCH)
	public ResponseModel<Object> updateMaterial4isDelete(String[] ids){
	
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			materialService.updateMaterial4isDelete(ids);
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
	@RequestMapping(value = "/material/validation/{serialNo}", method = RequestMethod.GET)
	@ResponseBody
	public boolean isExistSupplier(@PathVariable String serialNo) {

		boolean passFlag = false; // 校验通过标记。 true：通过。
		try {
			passFlag = !this.materialService.isExistMaterial(serialNo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			passFlag = false;
		}
		return passFlag;
	}
	
	/**
	 * 通过用途分类来查询具体分类
	 * @param useCategory
	 * @return
	 */
	@RequestMapping(value = "/material/category", method = RequestMethod.GET)
	@ResponseBody
	public ResponseModel<List> findspecificCategory(@RequestParam("useCategory") String useCategory){
		ResponseModel<List> model = new BatchResponseModel();
		
		List list =this.materialCategoryService.findMaterialCategorybyUseCategory(useCategory);
		model.setData(list);
		return model;
	}
	
	/**
	 * 跳转到被伪删的材料信息页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/material/deletepage",method=RequestMethod.GET)
	public String toDeleteMaterialPage(Model model){
		List<MaterialCategory> materialCategorys=materialCategoryService.findAll();
		model.addAttribute("materialCategorys", materialCategorys);
		List<Supplier> suppliers =supplierService.findAll(); 
		model.addAttribute("suppliers", suppliers);
		List<Warehouse> warehouses =warehouseService.findAll(); 
		model.addAttribute("warehouses", warehouses);
		return "base/material/delete_material";
		
	}
	
	/**
	 * 分页查询被伪删的材料信息
	 * @param request
	 * @param materialVo
	 * @return
	 */
	@RequestMapping(value="/finddeletematerials", method=RequestMethod.GET)
	@ResponseBody
	public JqGridModel<MaterialVo> findMaterialIsDelete(HttpServletRequest request,MaterialVo materialVo){
		PageRequest pageRequest = PageUtils.getPage4JqGrid(request);
		Page<MaterialVo> page = this.materialService.findAllMaterials(pageRequest, materialVo, true);
		JqGridModel<MaterialVo> model=PageUtils.pageConvertJqGrid(page);
		return model;
		
	}
	/**
	 * 恢复一组被伪删的材料信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/material/{materialId}",method=RequestMethod.PATCH)
	@ResponseBody
	public ResponseModel<Object> recoverMaterial(@PathVariable("materialId")String[] ids){
		
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			this.materialService.recoverMaterialById(ids);
			model.success();
			
		}catch(Exception e){
			model.error();
			LOGGER.error(e.getMessage(),e);
		}
		return model;
	}
	
	/**
	 * 真正删除材料信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/material/{materialId}",method=RequestMethod.DELETE)
	 public ResponseModel<Object> deleteMaterial(@PathVariable("materialId") String id){
	        ResponseModel<Object> model = new SimpleResponseModel<Object>() ;

	        try{
	        	if(id != null){
	        		this.materialService.delete(id);
	        	}
	        	model.success("删除成功！") ;
	        }catch(DataIntegrityViolationException e){
	        	model.error("删除失败,先删除跟材料相关的数据!");
	        }catch(Exception e){
	        	model.error("删除失败!");
	        }
	        return model;
	 }
}
