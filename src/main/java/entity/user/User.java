package entity.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import security.AuthorityType;
import util.BooleanConverter;
import util.GenderConverter;
import util.LocalDateConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/*
 * Created by guilherme on 07/01/16.
 * A User.
 */
@Entity
@Table(name = "USER_DETAILS")
public class User implements UserDetails, Serializable {
    private static final long serialVersionUID = 613393136989349826L;

    @Id
    @SequenceGenerator(name = "user_details_gen", sequenceName = "USER_DETAILS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_details_gen")
    @Column(name = "id")
    private Long id;

    @Email
    @NotEmpty
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(min = 6, max = 60)
    @Column(length = 60, name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @NotEmpty
    @Size(min = 3, max = 70)
    @Column(length = 70, name = "first_name")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 70)
    @Column(length = 70, name = "last_name")
    private String lastName;

    @Column(length = 1, name = "gender", nullable = false)
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(name = "register_date", nullable = false)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate registerDate;

    @Column(name = "activated", nullable = false, length = 1)
    @Convert(converter = BooleanConverter.class)
    private boolean activated;

    @Column(name = "activate_key", nullable = false, length = 36)
    private String activateKey;

    @Size(min = 2, max = 5)
    @Column(name = "lang_key", length = 5)
    private String langKey;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_DETAILS_AUTHORITY",
            joinColumns = {@JoinColumn(name = "user_details_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_credential", referencedColumnName = "credential")})
    private Set<Authority> authorities;

    {
        setActivated(Boolean.FALSE);
    }

    public User() {
        this.authorities = new HashSet<>();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getActivateKey() {
        return activateKey;
    }

    public void setActivateKey(String activateKey) {
        this.activateKey = activateKey;
    }

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setNewCredentialInAuthorities(AuthorityType credentialType) {
        this.authorities.add(new Authority(credentialType));
    }

    @Override
    public boolean isAccountNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActivated();
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
        return "[ " + id + " , " + username + " , " + authorities + " ]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        return getUsername() != null ? getUsername().equals(user.getUsername()) : user.getUsername() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        return result;
    }
}