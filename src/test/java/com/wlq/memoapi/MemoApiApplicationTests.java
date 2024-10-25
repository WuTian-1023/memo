package com.wlq.memoapi;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlq.memoapi.api.domain.TBorrowRecord;
import com.wlq.memoapi.api.domain.TUser;
import com.wlq.memoapi.api.mapper.TBorrowRecordMapper;
import com.wlq.memoapi.api.mapper.TUserMapper;
import com.wlq.memoapi.api.service.TBorrowRecordService;
import com.wlq.memoapi.api.service.TUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MemoApiApplicationTests {

    @Autowired
    private TUserService tUserService;

    @Autowired
    private TBorrowRecordService borrowRecordService;


    @Test
    void contextLoads() {
    }

    @Test
    void getUser() {
        TUser one = tUserService.getUserFindById(545001); // 直接调用 getOne 方法
        System.out.println(one); // 打印结果
    }

    @Test
    void getById() {
        TUser one = tUserService.getById(545001); // 直接调用 getOne 方法
        System.out.println(one); // 打印结果
    }

    @Test
    void count() {
        long count = tUserService.count();
        System.out.println(count); // 打印结果
    }

    @Test
    void borrowRecordSave() {
        TBorrowRecord tBorrowRecord = new TBorrowRecord();
        tBorrowRecord.setStatus("01");
        tBorrowRecord.setFilesBorrowTime(new Date());
        tBorrowRecord.setArchivesName("档案3");
        tBorrowRecord.setFilesBorrowUsername("田明明");
        boolean save = borrowRecordService.save(tBorrowRecord);
        System.out.println(save); // 打印结果
    }


    @Test
    void getBorrowRecord() {
        // "3f3dd25c-9116-11ef-8ff3-00ff6296320c"
        QueryWrapper<TBorrowRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TBorrowRecord::getUuid, "3f3dd25c-9116-11ef-8ff3-00ff6296320c");
        TBorrowRecord one = borrowRecordService.getOne(queryWrapper);
        System.out.println(one); // 打印结果
    }
}
