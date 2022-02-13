package com.halcyon.online_store.entity.dto;

import com.halcyon.online_store.entity.Comment;
import lombok.Data;

/**
 * @author Halcyon
 * @date 2022/1/25
 * @apiNote
 */
@Data
public class CommentDto {
    private Comment comment;
    private  String pname;

}
