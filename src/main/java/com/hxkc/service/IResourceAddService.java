package com.hxkc.service;

import com.hxkc.service.support.IBaseService;
import com.hxkc.vo.ZtreeView;
import com.hxkc.entity.AddedResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * <p>
 * 资源服务类
 * </p>

 */
public interface IResourceAddService extends IBaseService<AddedResource, Integer> {

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
	void saveOrUpdate(AddedResource resource);

	/**
	 * 关键字分页
	 * @param searchText
	 * @param pageRequest
	 * @return
	 */
	Page<AddedResource> findAllByLike(String searchText, PageRequest pageRequest);


}
