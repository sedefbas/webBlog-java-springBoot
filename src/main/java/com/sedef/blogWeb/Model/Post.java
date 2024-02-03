package com.sedef.blogWeb.Model;

import com.sedef.blogWeb.enums.StatusActive;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 150) // VARCHAR(150) olarak sınırlama
    private String title;
    @Lob // Büyük metin tipi olarak işaretleme
    @Column(columnDefinition = "TEXT")
    private String text;
    private StatusActive statusActive;
    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;
    private Date date;

 //   @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Comment> commentList;
}
