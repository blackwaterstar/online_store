package com.halcyon.online_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halcyon.online_store.entity.Comment;
import com.halcyon.online_store.entity.Orderinfo;
import com.halcyon.online_store.mapper.CommentMapper;
import com.halcyon.online_store.mapper.OrderinfoMapper;
import com.halcyon.online_store.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private OrderinfoMapper orderinfoMapper;


    @Override
    public int addComment(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public int deleteComment(String commentId) {
        return commentMapper.deleteById(commentId);
    }

    @Override
    public Comment selectComment(String commentId) {
        return commentMapper.selectById(commentId);
    }

    @Override
    public List<Comment> selectListComment(String orderId) {
        return commentMapper.selectList(new QueryWrapper<Comment>().eq("order_id",orderId).orderByAsc("comment_id"));
    }

    @Override
    public int likesComment(String commentId) {
        Comment comment = commentMapper.selectById(commentId);
        int a = comment.getLikes()+1;
        comment.setLikes(a);
        return commentMapper.updateById(comment);
    }

    @Override
    public List<Comment> selectCommentById(Long ppid) {
        QueryWrapper<Orderinfo> wrapper = new QueryWrapper<>();
        wrapper.select("order_id").eq("ppid",ppid);
        List<Orderinfo> list = orderinfoMapper.selectList(wrapper);
        List<Long> list1 = new ArrayList<>();
        list.forEach(orderinfo -> list1.add(orderinfo.getOrderId()));
        return commentMapper.selectBatchIds(list1);

    }
}
