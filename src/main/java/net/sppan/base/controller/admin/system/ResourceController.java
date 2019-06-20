package net.sppan.base.controller.admin.system;

import java.util.List;

import net.sppan.base.entity.AddedResource;
import net.sppan.base.service.IResourceAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Resource;
import net.sppan.base.service.IResourceService;
import net.sppan.base.vo.ZtreeView;

@Controller
@RequestMapping("/admin/resource")
public class ResourceController extends BaseController {
	@Autowired
	private IResourceService resourceService;

	@Autowired
	private IResourceAddService resourceAddService;


	@RequestMapping("/tree/{resourceId}")
	@ResponseBody
	public List<ZtreeView> tree(@PathVariable Integer resourceId){
		List<ZtreeView> list = resourceService.tree(resourceId);
		return list;
	}
	
	@RequestMapping("/index")
	public String index() {
		return "admin/resource/index";
	}
	@RequestMapping("/output")
	public String output() {
		return "admin/resource/output";
	}
	@RequestMapping("/input")
	public String input() {
		return "admin/resource/input";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Page<Resource> list(
			@RequestParam(value="searchText",required=false) String searchText
			) {
//		SimpleSpecificationBuilder<Resource> builder = new SimpleSpecificationBuilder<Resource>();
//		String searchText = request.getParameter("searchText");
//		if(StringUtils.isNotBlank(searchText)){
//			builder.add("name", Operator.likeAll.name(), searchText);
//		}
		Page<Resource> page = resourceService.findAllByLike(searchText,getPageRequest());
		return page;
	}
	@RequestMapping("/addReourcelist")
	@ResponseBody
	public Page<AddedResource> list1(
			@RequestParam(value="searchText",required=false) String searchText
	) {
//		SimpleSpecificationBuilder<Resource> builder = new SimpleSpecificationBuilder<Resource>();
//		String searchText = request.getParameter("searchText");
//		if(StringUtils.isNotBlank(searchText)){
//			builder.add("name", Operator.likeAll.name(), searchText);
//		}
		Page<AddedResource> page = resourceAddService.findAllByLike(searchText,getPageRequest());
		return page;
	}

	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		List<Resource> list = resourceService.findAll();
		map.put("list", list);
		return "admin/resource/form";
	}

	@RequestMapping(value = "/addnewResource", method = RequestMethod.GET)
	public String addnewResource(ModelMap map) {
		List<AddedResource> list = resourceAddService.findAll();
		map.put("list", list);
		return "admin/resource/outputform";
	}
	

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Resource resource = resourceService.find(id);
		map.put("resource", resource);
		
		List<Resource> list = resourceService.findAll();
		map.put("list", list);
		return "admin/resource/form";
	}
	
	@RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Resource resource,ModelMap map){
		try {
			resourceService.saveOrUpdate(resource);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	@RequestMapping(value= {"/editnewAdd"}, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult editandnewAddResource(AddedResource resource,ModelMap map){
		try {
			resourceAddService.saveOrUpdate(resource);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id,ModelMap map) {
		try {
			resourceService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
}
