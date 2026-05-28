package org.example.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.community.entity.Permission;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}