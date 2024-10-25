package com.wlq.memoapi.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlq.memoapi.api.domain.AjaxResult;
import com.wlq.memoapi.api.domain.TBorrowRecord;
import com.wlq.memoapi.api.service.TBorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: tjm
 * @date: 2024-10-23 023 15:42
 * @Description: tips: 代码不规范 同事两行泪
 */
// 登录校验：只有登录之后才能进入该方法
@SaCheckLogin
@RestController
@RequestMapping("/borrowrecord")
public class TBorrowRecordController {

    @Autowired
    private TBorrowRecordService borrowRecordService;

    /**
     * 保存登记
     * @param record
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    public AjaxResult saveOrUpdate(@RequestBody TBorrowRecord record) {
        record.setStatus("01");
        record.setFilesBorrowTime(new Date());
        boolean save = borrowRecordService.save(record);
        return AjaxResult.success("记录保存成功！", save);
    }


    /**
     * 分页查询借阅记录
     *
     * @param current 当前页码
     * @param size 每页显示的记录数
     * @param status 状态：00-归还，01-外接
     * @return 分页结果
     */
    @RequestMapping("/page")
    public IPage<TBorrowRecord> getBorrowRecords(
            @RequestParam(defaultValue = "1") int current,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "00",name = "status") String status,
            @RequestParam(defaultValue = "false") boolean isAsc) {
        // 构建分页对象
        Page<TBorrowRecord> page = new Page<>(current, size);
        IPage<TBorrowRecord> borrowRecords = borrowRecordService.getBorrowRecords(page, status, isAsc);
        return borrowRecords;
    }




    /**
     * 今日登记情况
     */
    @RequestMapping("/RegistrationStatusToday")
    public AjaxResult RegistrationStatusToday() {
        Map<String, String> registrationStatusToday = borrowRecordService.getRegistrationStatusToday();
        return AjaxResult.success("统计汇总",registrationStatusToday);
    }



    /**
     * 归还
     *
     * @param serialNo
     * @return
     */
    @RequestMapping("/restitution")
    public AjaxResult restitution(String serialNo) {
        borrowRecordService.restitution(serialNo);
        return AjaxResult.success("确定归还");
    }


}
