package com.aarshinkov.masters.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rolename"))
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<RecipeEntity> recipes;

    public String getFullName() {
        return lastName ==   null ? firstName : firstName + " " + lastName;
    }
}
