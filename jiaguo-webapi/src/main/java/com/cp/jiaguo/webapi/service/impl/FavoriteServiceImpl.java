package com.cp.jiaguo.webapi.service.impl;

import com.cp.jiaguo.webapi.dao.FavoriteDao;
import com.cp.jiaguo.webapi.model.Favorite;
import com.cp.jiaguo.webapi.service.FavoriteService;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl extends EntityServiceImpl<Favorite, FavoriteDao> implements FavoriteService {
}
