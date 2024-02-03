package com.sedef.blogWeb.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;


import java.sql.Blob;
import java.util.Set;
@Data
@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45)
    private String firstName;
    @Column(length = 45)
    private String lastName;
    @Column(length = 45)
    private String username;
    private String password;
    private Blob photo;

    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;





    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // private List<Comment> commentList;
}
