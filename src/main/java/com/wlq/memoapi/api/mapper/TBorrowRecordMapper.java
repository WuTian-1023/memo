package com.wlq.memoapi.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlq.memoapi.api.domain.TBorrowRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author wuliuqi
* @description 针对表【t_borrow_record】的数据库操作Mapper
* @createDate 2024-10-23 11:30:50
* @Entity api.domain.TBorrowRecord
*/
@Mapper
public interface TBorrowRecordMapper extends BaseMapper<TBorrowRecord> {

    void restitution(@Param("serialNo") String serialNo);


    Map<String, String> getRegistrationStatusToday();
}



