package entity;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * Created by guilherme on 07/01/16.
 */
@Entity
@Table(name = "user_details")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idUser;

    @Email
    @Column(name = "username")
    private String username;

    @Column(length = 60, name = "password")
    private String password;

    @Column(length = 70, name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, name = "gender", nullable = false)
    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public User() {
        this.roles = new ArrayList<>();
    }

    public Long getIdUser() {
        return idUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean isAccountNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE;
    }

    @Override
    public String toString() {
        return  idUser + " ; " + name + " ; " + username;
    }
}