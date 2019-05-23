package com.example.demo.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "basic_dept")
@KeySequence(value = "BASIC_DEPT_SEQ")
public class Dpt {
    @TableId(value = "dept_id", type = IdType.INPUT)
    private Long deptId;
    private String memberCode;
    private String deptCode;
    private String deptName;
    private String orgCode;
    private String deptManager;
    private String deptRemark;
    private Integer deptIsleaf;
    private String deptParent;
    private String deptNodecode;
    private Integer basicShare;
}
