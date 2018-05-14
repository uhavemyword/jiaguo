package com.cp.jiaguo.webapi.dao;

import com.cp.jiaguo.webapi.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends EntityDao<User> {
}
