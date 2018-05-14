package com.cp.jiaguo.webapi.service.impl;

import com.cp.jiaguo.webapi.dao.ContactDao;
import com.cp.jiaguo.webapi.dao.UserDao;
import com.cp.jiaguo.webapi.model.Contact;
import com.cp.jiaguo.webapi.model.User;
import com.cp.jiaguo.webapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends EntityServiceImpl<User, UserDao> implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ContactDao contactDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(User user) {
        userDao.insert(user);
        Contact contact = new Contact();
        contact.setPhone("1234567890");
        contact.setAddress("fake address 1");
        contact.setUser(user);
        contactDao.insert(contact);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        contactDao.deleteByUserId(id);
        userDao.deleteById(id);
    }
}
