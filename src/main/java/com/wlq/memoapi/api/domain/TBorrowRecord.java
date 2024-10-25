package com.wlq.memoapi.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wlq.memoapi.MemoApiApplication;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_borrow_record
 */
@TableName(value ="t_borrow_record")
@Data
public class TBorrowRecord extends MemoApiApplication implements Serializable {

    /**
     * uuid
     */
    @TableField(value = "uuid")
    public String uuid;

    /**
     * 档案名
     */
    @TableField(value = "archives_name")
    private String archivesName;

    /**
     * 档案外借人员姓名
     */
    @TableField(value = "files_borrow_username")
    private String filesBorrowUsername;

    /**
     * 外借时间
     */
    @TableField(value = "files_borrow_time")
    private Date filesBorrowTime;

    /**
     * 归还时间
     */
    @TableField(value = "return_time")
    private Date returnTime;

    // 档案日期
    /**
     * 档案日期
     */
    @TableField(value = "archives_date")
    private Date archivesDate;
    /**
     * 状态：00-在库，01-外接 ，02-已归还
     */
    @TableField(value = "status")
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}