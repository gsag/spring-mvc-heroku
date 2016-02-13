package entity.user;

import org.springframework.security.core.GrantedAuthority;
import security.AuthorityType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/*
 * Created by guilherme on 07/01/16.
 */
@Entity
@Table(name = "AUTHORITY")
public class Authority implements GrantedAuthority, Serializable{
    private static final long serialVersionUID = -5551195578445678669L;

    @NotNull
    @Size(min = 0, max = 50)
    @Id
    @Column(length = 50, nullable = false)
    private String credential;

    @Deprecated
    public Authority() {}

    public Authority(String credential){
        this.credential = credential;
    }

    public Authority(AuthorityType credentialType){
        this.credential = credentialType.name();
    }

    @Override
    public String getAuthority() {
        return credential;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return Boolean.TRUE;
        if (o == null || getClass() != o.getClass()) return Boolean.FALSE;

        Authority authority = (Authority) o;

        return credential.equals(authority.credential);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((credential == null) ? 0 : credential.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return credential;
    }
}