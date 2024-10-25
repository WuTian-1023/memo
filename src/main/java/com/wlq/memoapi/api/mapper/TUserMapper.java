package com.wlq.memoapi.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlq.memoapi.api.domain.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author wuliuqi
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2024-10-23 11:30:50
* @Entity api.domain.TUser
*/
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {
    TUser getUserFindById (@Param("userid") int userid);
}




