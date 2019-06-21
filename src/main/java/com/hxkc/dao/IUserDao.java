package com.hxkc.dao;

import com.hxkc.dao.support.IBaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.hxkc.entity.User;

@Repository
public interface IUserDao extends IBaseDao<User, Integer> {

	User findByUserName(String username);

	Page<User> findAllByNickNameContaining(String searchText, Pageable pageable);

}
