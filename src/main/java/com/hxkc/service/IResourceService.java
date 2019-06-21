package com.hxkc.service;

import java.util.List;

import com.hxkc.service.support.IBaseService;
import com.hxkc.vo.ZtreeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hxkc.entity.Resource;

/**
 * <p>
 * 资源服务类
 * </p>

 */
public interface IResourceService extends IBaseService<Resource, Integer> {

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
	void saveOrUpdate(Resource resource);

	/**
	 * 关键字分页
	 * @param searchText
	 * @param pageRequest
	 * @return
	 */
	Page<Resource> findAllByLike(String searchText, PageRequest pageRequest);

}
