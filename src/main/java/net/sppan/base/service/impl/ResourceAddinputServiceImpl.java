package net.sppan.base.service.impl;

import net.sppan.base.common.Constats;
import net.sppan.base.dao.IResourceAddDao;
import net.sppan.base.dao.IResourceDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.AddedResource;
import net.sppan.base.entity.Resource;
import net.sppan.base.entity.Role;
import net.sppan.base.service.IResourceAddService;
import net.sppan.base.service.IResourceService;
import net.sppan.base.service.IRoleService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import net.sppan.base.vo.ZtreeView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *

 */
@Service
public class ResourceAddinputServiceImpl extends BaseServiceImpl<AddedResource, Integer>
		implements IResourceAddService {
	//新增的库存dao
	@Autowired
	private IResourceAddDao resourceAddDao;

	//总库表
	@Autowired
	private IResourceDao resourceDao;

	@Autowired
	private IResourceService resourceService;

	@Autowired
	private IRoleService roleService;

	@Override
	public IBaseDao<AddedResource, Integer> getBaseDao() {
		return this.resourceAddDao;
	}

	@Override
	@Cacheable(value=Constats.RESOURCECACHENAME,key="'tree_' + #roleId")
	public List<ZtreeView> tree(int roleId) {
		List<ZtreeView> resulTreeNodes = new ArrayList<ZtreeView>();
		Role role = roleService.find(roleId);
		Set<Resource> roleResources = role.getResources();
		resulTreeNodes.add(new ZtreeView(0L, null, "系统菜单", true));
		ZtreeView node;
		//List<Resource> all = resourceDao.findAllByOrderByParentAscIdAscSortAsc();
		List<AddedResource> all = resourceAddDao.findAllByOrderByIdAsc();
		for (AddedResource resource : all) {
			node = new ZtreeView();
			node.setId(Long.valueOf(resource.getId()));
		//	if (resource.getParent() == null) {
				node.setpId(0L);
		//	} else {
			//	node.setpId(Long.valueOf(resource.getParent().getId()));
			//}
			node.setName(resource.getName());
			if (roleResources != null && roleResources.contains(resource)) {
				node.setChecked(true);
			}
			resulTreeNodes.add(node);
		}
		return resulTreeNodes;
	}

	@Override
	@Transactional
	public void saveOrUpdate(AddedResource resource) {


		Resource resourcetotal = resourceDao.findByName(resource.getName()).get(0);
		//新增数量

		int num = resource.getNum() + resourcetotal.getNum();

		resourcetotal.setNum(num);
		resourceService.saveOrUpdate(resourcetotal);


		//


//			AddedResource dbResource = find(resource.getId());
//			dbResource.setUpdateTime(new Date());
//			dbResource.setName(resource.getName());
//			dbResource.setPrice(resource.getPrice());
//			dbResource.setType(resource.getType());
//
//			dbResource.setIsHide(resource.getIsHide());
//			//dbResource.setIcon(resource.getIcon());
//			dbResource.setDescription(resource.getDescription());
//			dbResource.setUpdateTime(new Date());
//
//			update(dbResource);
//		}else{
		resource.setCreateTime(new Date());
		resource.setUpdateTime(new Date());
		save(resource);
     //	}
	}

	@Override
	public void delete(Integer id) {
		resourceAddDao.deleteGrant(id);
		super.delete(id);
	}

	@Override
	public Page<AddedResource> findAllByLike(String searchText, PageRequest pageRequest) {
		if(StringUtils.isBlank(searchText)){
			searchText = "";
		}
		return resourceAddDao.findAllByNameContaining(searchText, pageRequest);
	}



}
