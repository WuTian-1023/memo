package com.wlq.memoapi.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wlq.memoapi.api.domain.TBorrowRecord;

import java.util.Map;

/**
* @author wuliuqi
* @description 针对表【t_borrow_record】的数据库操作Service
* @createDate 2024-10-23 11:30:50
*/
public interface TBorrowRecordService extends IService<TBorrowRecord> {
    /**
     * 归还
     * @param serialNo
     */
    void restitution(String serialNo);


    /**
     * 分页查询借阅记录
     *
     * @param page 当前页信息
     * @return 分页结果
     */
    IPage<TBorrowRecord> getBorrowRecords(Page<TBorrowRecord> page,String status, boolean isAsc);


    /**
     * 查询今日登记情况
     * @return 包含登记状态和数量的 Map
     */
    Map<String, String> getRegistrationStatusToday();
}
