package com.halcyon.online_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halcyon.online_store.entity.Comment;
import com.halcyon.online_store.entity.CommentReply;
import com.halcyon.online_store.mapper.CommentReplyMapper;
import com.halcyon.online_store.service.CommentReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@Service
public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyMapper, CommentReply> implements CommentReplyService {

    @Resource
    private CommentReplyMapper commentReplyMapper;

    @Override
    public int addCommentReply(CommentReply commentReply) {
        return commentReplyMapper.insert(commentReply);
    }

    @Override
    public int deleteCommentReply(String replyId) {
        return commentReplyMapper.deleteById(replyId);
    }

    @Override
    public int deleteCommentReplybyList(String commentId, String userId, String replyUserId) {
        QueryWrapper<CommentReply> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_id", commentId).eq("user_id", userId).eq("replyUser_id", replyUserId);
        return commentReplyMapper.delete(wrapper);
    }

    @Override
    public CommentReply selectCommentReply(String replyId) {
        return commentReplyMapper.selectById(replyId);
    }

    @Override
    public List<CommentReply> listCommentReply() {
        return commentReplyMapper.selectList(null);
    }

    @Override
    public List<CommentReply> selectListReplyComment(String commentId) {
        return commentReplyMapper.selectList(new QueryWrapper<CommentReply>().eq("comment_id",commentId).orderByAsc(
                "reply_id"));
    }

    @Override
    public int likesCommentReply(String replyId) {
        CommentReply commentReply = new CommentReply();
        int a=commentReply.getLikes()+1;
        commentReply.setLikes(a);
        return commentReplyMapper.updateById(commentReply);
    }
}
