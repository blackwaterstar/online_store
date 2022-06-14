package com.halcyon.online_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halcyon.online_store.entity.Comment;
import com.halcyon.online_store.entity.Orderinfo;
import com.halcyon.online_store.entity.ProductInfo;
import com.halcyon.online_store.entity.dto.CommentDto;
import com.halcyon.online_store.mapper.CommentMapper;
import com.halcyon.online_store.mapper.OrderinfoMapper;
import com.halcyon.online_store.mapper.ProductInfoMapper;
import com.halcyon.online_store.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Resource
    private ProductInfoMapper productInfoMapper;


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
    public List<Comment> selectListComment(String ppid) {
        return commentMapper.selectList(new QueryWrapper<Comment>().eq("ppid",ppid).orderByAsc("comment_id"));
    }

    @Override
    public int likesComment(String commentId) {
        Comment comment = commentMapper.selectById(commentId);
        int a = comment.getLikes()+1;
        comment.setLikes(a);
        return commentMapper.updateById(comment);
    }



    @Override
    public List<CommentDto> selectCommentById(Long ppid) {
        List<CommentDto> commentDtos = new ArrayList<CommentDto>();
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>().eq("ppid", ppid));
        comments.forEach(comment -> {
            ProductInfo productInfo = productInfoMapper.selectOne(new QueryWrapper<ProductInfo>().eq("ppid",
                    comment.getPpid()));
            CommentDto commentDto = new CommentDto();
            commentDto.setComment(comment);
            commentDto.setPname(productInfo.getPname());
            commentDtos.add(commentDto);
        });
        return  commentDtos;

    }

    @Override
    public List<CommentDto> selectListCommentByOrderId(String orderId) {
        List<CommentDto> commentDtos = new ArrayList<CommentDto>();
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>().eq("order_id", orderId));
        comments.forEach(comment -> {
            ProductInfo productInfo = productInfoMapper.selectOne(new QueryWrapper<ProductInfo>().eq("ppid",
                    comment.getPpid()));
            CommentDto commentDto = new CommentDto();
            commentDto.setComment(comment);
            commentDto.setPname(productInfo.getPname());
            commentDtos.add(commentDto);
        });
        return commentDtos;
    }
}
