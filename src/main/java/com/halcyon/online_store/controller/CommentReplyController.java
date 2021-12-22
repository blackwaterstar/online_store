package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.Comment;
import com.halcyon.online_store.entity.CommentReply;
import com.halcyon.online_store.service.CommentReplyService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@RestController
@RequestMapping("//commentReply")
public class CommentReplyController {
    @Resource
    private CommentReplyService commentReplyService;

    @RequestMapping("addCommentReply")
    public int addCommentReply(CommentReply commentReply){
        return commentReplyService.addCommentReply(commentReply);
    }

    @RequestMapping("deleteCommentReply")
    public int deleteCommentReply(String replyId){
        return commentReplyService.deleteCommentReply(replyId);
    }

    @RequestMapping("deleteCommentReplybyList")
    public int deleteCommentReplybyList(String commentId,String userId,String replyUserId){
        return commentReplyService.deleteCommentReplybyList(commentId,userId,replyUserId);
    }

    @RequestMapping("selectCommentReply")
    public CommentReply updateCommentReply(String commentId){
        return commentReplyService.selectCommentReply(commentId);
    }

    @RequestMapping("listCommentReply")
    public List<CommentReply> listCommentReply(){
        return commentReplyService.listCommentReply();
    }

    @RequestMapping("selectListReplyComment")
    public List<CommentReply> selectListReplyComment(String commentId){
        return commentReplyService.selectListReplyComment(commentId);
    }

    @RequestMapping("likesCommentReply")
    public int likesCommentReply(String replyId){
        return commentReplyService.likesCommentReply(replyId);
    }

}

