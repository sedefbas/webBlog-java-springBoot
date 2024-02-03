package com.sedef.blogWeb.Request;
import com.sedef.blogWeb.enums.StatusConfirmation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRequest {
    private String text;
    private StatusConfirmation statusConfirmation;
    private int postId;
    private int userId;

    public CommentRequest(String text, int postId, int userId) {
        this(text, StatusConfirmation.NOT_CONFIRMATION, postId, userId);
    }

}
