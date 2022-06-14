package com.halcyon.online_store.mapper;

import com.halcyon.online_store.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface CommentMapper extends BaseMapper<Comment> {
    int getallmonthcomment(int num);
}
