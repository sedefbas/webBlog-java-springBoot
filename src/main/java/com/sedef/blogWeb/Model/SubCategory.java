package com.sedef.blogWeb.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "sub_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 70)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    //@OnDelete(action = OnDeleteAction.CASCADE)  bir category silindiğinde altındaki subclasslarda silinir.
  //  @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL, orphanRemoval = true)
   // private List<Post> postList;
}
