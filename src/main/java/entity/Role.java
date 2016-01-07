package entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/*
 * Created by guilherme on 07/01/16.
 */
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority, Serializable{
    private static final long serialVersionUID = -5551195578445678669L;

    @Id
    private String credential;

    @Deprecated
    public Role() {}

    private Role(String credential){
        this.credential = credential;
    }

    @Override
    public String getAuthority() {
        return credential;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return Boolean.TRUE;
        if (o == null || getClass() != o.getClass()) return Boolean.FALSE;

        Role role = (Role) o;

        return credential.equals(role.credential);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((credential == null) ? 0 : credential.hashCode());
        return result;
    }
}