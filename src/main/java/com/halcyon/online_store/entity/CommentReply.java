package com.halcyon.online_store.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentReply implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer replyId;

    private Long commentId;

    private Integer userId;

    private Integer replyUserId;

    private String content;

    private int likes;

    private String createtime;


}
