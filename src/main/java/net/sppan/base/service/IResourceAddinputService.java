package net.sppan.base.service;

import net.sppan.base.entity.AddedResource;
import net.sppan.base.entity.AddedinputResource;
import net.sppan.base.service.support.IBaseService;
import net.sppan.base.vo.ZtreeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * <p>
 * 资源服务类
 * </p>

 */
public interface IResourceAddinputService extends IBaseService<AddedinputResource, Integer> {

	/**
	 * 获取角色的权限树
	 * @param roleId
	 * @return
	 */
	List<ZtreeView> tree(int roleId);

	/**
	 * 修改或者新增资源
	 * @param resource
	 */
	void saveOrUpdate(AddedinputResource resource);

	/**
	 * 关键字分页
	 * @param searchText
	 * @param pageRequest
	 * @return
	 */
	Page<AddedinputResource> findAllByLike(String searchText, PageRequest pageRequest);


}
