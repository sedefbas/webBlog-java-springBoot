package com.sedef.blogWeb.Request;


import com.sedef.blogWeb.enums.StatusActive;
import lombok.Data;

@Data
public class PostRequest {
    private String title;
    private String text;
    private StatusActive statusActive;
    private int subCategoryId;

    //aktif olarak ayarladım
    public PostRequest(String title, String text, int subCategoryId) {
        this.title = title;
        this.text = text;
        this.statusActive = StatusActive.AKTIF;
        this.subCategoryId = subCategoryId;
    }

}
