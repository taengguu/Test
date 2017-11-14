package cn.material.base.materialcategory.controller;

import java.util.List;

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

import cn.material.base.material.vo.MaterialVo;
import cn.material.base.materialcategory.entity.MaterialCategory;
import cn.material.base.materialcategory.service.MaterialCategoryService;
import cn.material.base.supplier.entity.Supplier;
import cn.material.base.warehouse.entity.Warehouse;
import cn.material.common.page.JqGridModel;
import cn.material.common.page.PageUtils;
import cn.material.common.response.ResponseModel;
import cn.material.common.response.SimpleResponseModel;
import cn.material.dictionary.entity.Dictionary;
import cn.material.dictionary.service.DictService;

/**
 * 分类信息的controller
 * 
 * @author xjl
 *
 */
@RequestMapping(value = "/base")
@Controller
public class MaterialCategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MaterialCategory.class);

	@Resource
	private MaterialCategoryService materialCategoryService;
	@Resource
	private DictService dictService;
	

	@RequestMapping(value = "/materialcategory/page")
	public String materialCategoryPage(Model model) {
		return "base/materialcategory/materialcategory";
	}

	/**
	 * 
	 * jqgrid插件方式查询供应商信息
	 */
	@ResponseBody
	@RequestMapping(value = "/materialcategory", method = RequestMethod.GET)
	public JqGridModel<MaterialCategory> findMaterialCategorys(HttpServletRequest request,
			MaterialCategory materialCategory) {
		PageRequest pageRequest = PageUtils.getPage4JqGrid(request);
		Page<MaterialCategory> page = this.materialCategoryService.findAllMaterialCategorys(pageRequest,
				materialCategory, false);
		JqGridModel<MaterialCategory> model = PageUtils.pageConvertJqGrid(page);
		return model;
	}

	/**
	 * 新建保存
	 * 
	 * @param supplier
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/materialcategory", method = RequestMethod.POST)
	public ResponseModel<String> saveMaterialCategory(MaterialCategory materialCategory) {
		ResponseModel<String> model = new SimpleResponseModel<String>();
		try {
			materialCategoryService.save(materialCategory);
			model.success();
		} catch (Exception e) {
			LOGGER.error("添加仓库发生异常", e);
			model.error();
		}
		return model;
	}

	/**
	 * 通过id查询，回显信息
	 */
	@ResponseBody
	@RequestMapping(value = "/materialcategory/{materialcategoryId}", method = RequestMethod.GET)
	public ResponseModel<Object> findMaterialCategoryById(@PathVariable("materialcategoryId") String id) {
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try {
			MaterialCategory materialCategory = materialCategoryService.findOne(id);
			if (materialCategory != null) {
				model.setData(materialCategory);
			}
			model.success();
		} catch (Exception e) {
			LOGGER.error("查询出错", e);
		}
		return model;
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/materialcategory", method = RequestMethod.PUT)
	public ResponseModel<String> updateMaterialCategory(MaterialCategory materialCategory) {
		ResponseModel<String> model = new SimpleResponseModel<String>();

		try {
			if (materialCategory != null) {
				materialCategoryService.updateMaterialCategory(materialCategory);
				model.success();
			} else {
				model.error("更新失败");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			model.error("更新失败");
		}

		return model;
	}

	/**
	 * 进行伪删除操作
	 * 
	 * @param ids  一组id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/materialcategory", method = RequestMethod.PATCH)
	public ResponseModel<Object> updateMaterialCategory4isDelete(String[] ids) {

		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try {
			materialCategoryService.updateMaterialCategory4isDelete(ids);
			model.success();
		} catch (DataIntegrityViolationException e) {
			model.error();
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			model.error();
			LOGGER.error(e.getMessage(), e);
		}
		return model;
	}
	
	/**
	 * 查询该分类信息是否存在
	 * @param serialNo  查询条件(分类编号)
	 * @return
	 */
	@RequestMapping(value = "/materialcategory/validation/{serialNo}", method = RequestMethod.GET)
	@ResponseBody
	public boolean isExistMaterialCategory(@PathVariable String serialNo) {

		boolean passFlag = false; // 校验通过标记。 true：通过。
		try {
			passFlag = !this.materialCategoryService.isExistMaterialCategory(serialNo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			passFlag = false;
		}
		return passFlag;
	}
	/**
	 * 跳转到被伪删的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/materialcategory/deletepage",method=RequestMethod.GET)
	public String toDeleteMaterialPage(Model model){
		List<Dictionary> dictionarys =dictService.dictList("useCategory"); 
		model.addAttribute("dictionarys", dictionarys);
		return "base/materialcategory/delete_materialcategory";
		
	}
	

	/**
	 * 分页查询被伪删的分类信息
	 * @param request  
	 * @param materialCategory  查询的载体
	 * @return
	 */
	@RequestMapping(value="/finddeletecategorys", method=RequestMethod.GET)
	@ResponseBody
	public JqGridModel<MaterialCategory> findMaterialCategoryIsDelete(HttpServletRequest request,MaterialCategory materialCategory){
		PageRequest pageRequest = PageUtils.getPage4JqGrid(request);
		Page<MaterialCategory> page = this.materialCategoryService.findAllMaterialCategorys(pageRequest, materialCategory, true);
		JqGridModel<MaterialCategory> model=PageUtils.pageConvertJqGrid(page);
		
		return model;
		
	}
	/**
	 * 恢复一组被伪删的分类信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/materialcategory/{materialcategoryId}",method=RequestMethod.PATCH)
	@ResponseBody
	public ResponseModel<Object> recoverMaterialCategory(@PathVariable("materialcategoryId")String[] ids){
		
		ResponseModel<Object> model = new SimpleResponseModel<Object>();
		try{
			this.materialCategoryService.recoverMaterialCategoryById(ids);
			model.success();
		}catch(Exception e){
			model.error();
			LOGGER.error(e.getMessage(),e);
		}
		return model;
	}
	
	/**
	 * 真正删除分类信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/materialcategory/{materialcategoryId}",method=RequestMethod.DELETE)
	 public ResponseModel<Object> deleteMaterialCategory(@PathVariable("materialcategoryId") String id){
	        ResponseModel<Object> model = new SimpleResponseModel<Object>() ;
	        try{
	        	if(id != null){
	        		this.materialCategoryService.delete(id);
	        	}
	        	model.success("删除成功！") ;
	        }catch(DataIntegrityViolationException e){
	        	model.error("删除失败,先删除跟分类相关的数据!");
	        }catch(Exception e){
	        	model.error("删除失败!");
	        }
	        return model;
	 }

}
