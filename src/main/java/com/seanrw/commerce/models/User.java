package com.seanrw.commerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Column(name = "username")
    @Getter
    @Setter
    private String username;

    @Column(name = "role")
    @Getter
    private String role;

    @Column(name = "name")
    @Getter
    @Setter
    private String preferredName;

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @Column(name = "password")
    @Getter
    @Setter
    private String password;

    public User(
        String username, String role, String preferredName, String email, String password
    ) {
//        super();
        this.username = username;
        this.role = role;
        this.preferredName = preferredName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", preferredName='" + preferredName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
