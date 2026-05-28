package org.example.community.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("permission")
public class Permission {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String permissionName;

    private String permissionKey;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableLogic
    private Integer isDeleted;
}