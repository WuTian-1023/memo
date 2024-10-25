package com.wlq.memoapi.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlq.memoapi.api.domain.TBorrowRecord;
import com.wlq.memoapi.api.mapper.TBorrowRecordMapper;
import com.wlq.memoapi.api.service.TBorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
* @author wuliuqi
* @description 针对表【t_borrow_record】的数据库操作Service实现
* @createDate 2024-10-23 11:30:50
*/
@Service
public class TBorrowRecordServiceImpl extends ServiceImpl<TBorrowRecordMapper, TBorrowRecord>
    implements TBorrowRecordService {

    @Autowired
    private TBorrowRecordMapper borrowRecordMapper;

    @Override
    public IPage<TBorrowRecord> getBorrowRecords(Page<TBorrowRecord> page,String status, boolean isAsc) {
        // 使用 MyBatis-Plus 的分页查询
        QueryWrapper<TBorrowRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TBorrowRecord::getStatus, status);
        if ("02".equals(status)){
            queryWrapper.orderBy(true, isAsc, "return_time");  // 按 return_time 排序 isAsc：false-倒序
        }else if ("01".equals(status)){
            queryWrapper.orderBy(true, isAsc, "files_borrow_time");  // 按 filesBorrowTime 排序
        }
        return borrowRecordMapper.selectPage(page, queryWrapper);
    }

    /**
     * 查询今日登记情况
     *
     * @return 包含登记状态和数量的 Map
     */
    @Override
    public Map<String, String> getRegistrationStatusToday() {
        return borrowRecordMapper.getRegistrationStatusToday();
    }

    @Override
    public boolean save(TBorrowRecord entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(TBorrowRecord entity) {
        return super.removeById(entity);
    }

    @Override
    public boolean updateById(TBorrowRecord entity) {
        return super.updateById(entity);
    }

    @Override
    public TBorrowRecord getById(Serializable id) {
        return borrowRecordMapper.selectById(id);
    }

    @Override
    public TBorrowRecord getOne(Wrapper<TBorrowRecord> queryWrapper) {
        return super.getOne(queryWrapper);
    }

    @Override
    public List<TBorrowRecord> list(IPage<TBorrowRecord> page, Wrapper<TBorrowRecord> queryWrapper) {
        return super.list(page, queryWrapper);
    }

    @Override
    public void restitution(String serialNo) {
        borrowRecordMapper.restitution(serialNo);
    }
}




