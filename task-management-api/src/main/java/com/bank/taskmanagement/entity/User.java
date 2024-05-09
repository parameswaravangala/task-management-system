package com.bank.taskmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends AbstractEntity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

   // @Size(max = 255)
    //@NotNull
    @Column(name = "email", nullable = false)
    private String email;

    //@NotNull
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotNull
    @Column(name = "mobile", nullable = false)
    private String mobile;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany
    private Set<UserNotificationPreference> userNotificationPreferences;// = new LinkedHashSet<>();

    @Override
    public Integer getId() {
        return 0;
    }
}