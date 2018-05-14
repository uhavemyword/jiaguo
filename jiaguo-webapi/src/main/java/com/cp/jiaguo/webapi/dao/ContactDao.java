package com.cp.jiaguo.webapi.dao;

import com.cp.jiaguo.webapi.model.Contact;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContactDao extends com.cp.jiaguo.webapi.dao.EntityDao<Contact> {
    void deleteByUserId(Integer id);
}
