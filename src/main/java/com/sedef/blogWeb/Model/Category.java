package com.sedef.blogWeb.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 150)
    @NotNull
    private String name;
    //baştaki one bulundugu sınıfı temsil eder.
    //cascadeType.All =ana nesne üzerinde yapılan değişikliklerin alt nesnelere de uygulanmasını sağlar
    //mappedBy = ilişkinin karşı tarafında neyle eşleneceğini
    //orphanRemoval=ture  = altcategroylerden biri subcategories listesinden silinirse, subcategory sınıfının veritabanındanda silinir.
  //  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  //  private List<SubCategory> subCategoryList;
}
