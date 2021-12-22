package com.halcyon.online_store.service;

import com.halcyon.online_store.entity.CommentReply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface CommentReplyService extends IService<CommentReply> {

    int addCommentReply(CommentReply commentReply);

    int deleteCommentReply(String replyId);
    int deleteCommentReplybyList(String commentId, String userId, String replyUserId);

    CommentReply selectCommentReply(String commentId);

    List<CommentReply> listCommentReply();

    List<CommentReply> selectListReplyComment(String commentId);

    int likesCommentReply(String replyId);
}


