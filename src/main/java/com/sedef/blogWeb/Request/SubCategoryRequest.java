package com.sedef.blogWeb.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SubCategoryRequest {
    private String name;
    private int categoryId;
}


