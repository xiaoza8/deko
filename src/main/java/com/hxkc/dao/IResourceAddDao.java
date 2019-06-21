package com.hxkc.dao;

import com.hxkc.dao.support.IBaseDao;
import com.hxkc.entity.AddedResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResourceAddDao extends IBaseDao<AddedResource, Integer> {

	@Modifying
	@Query(nativeQuery = true,value = "DELETE FROM tb_role_resource WHERE resource_id = :id")
	void deleteGrant(@Param("id") Integer id);

	Page<AddedResource> findAllByNameContaining(String searchText, Pageable pageable);

	List<AddedResource> findAllByOrderByIdAsc();

}
