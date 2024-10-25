package com.wlq.memoapi.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlq.memoapi.api.domain.TUser;
import com.wlq.memoapi.api.mapper.TUserMapper;
import com.wlq.memoapi.api.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

/**
* @author wuliuqi
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2024-10-23 11:30:50
*/
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser>
    implements TUserService {

    @Autowired
    private TUserMapper userMapper;

    @Override
    public TUser getUserFindById(int userid) {
        return userMapper.getUserFindById(userid);
    }

    @Override
    public TUser getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public Optional<TUser> getOptById(Serializable id) {
        return super.getOptById(id);
    }

    @Override
    public TUser getOne(Wrapper<TUser> queryWrapper) {
        return super.getOne(queryWrapper);
    }
}




