package com.sedef.blogWeb.Request;
import com.sedef.blogWeb.enums.StatusConfirmation;
import lombok.Data;

@Data
public class CommentRequest {
    private String text;
    private StatusConfirmation statusConfirmation;
    private int postId;
    private int userId;

    public CommentRequest(String text, int postId, int userId) {
        this.text = text;
        this.statusConfirmation = StatusConfirmation.NOT_CONFIRMATION;
        this.postId = postId;
        this.userId = userId;
    }

}
