package com.wlq.memoapi.api.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wlq.memoapi.api.domain.TUser;
import org.apache.catalina.User;

/**
* @author wuliuqi
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-10-23 11:30:50
*/
public interface TUserService extends IService<TUser> {

    TUser getUserFindById (int userid);

}
