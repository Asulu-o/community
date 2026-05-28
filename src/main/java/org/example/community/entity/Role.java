package org.example.community.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("role")
public class Role {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String roleName;

    private String roleKey;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableLogic
    private Integer isDeleted;
}