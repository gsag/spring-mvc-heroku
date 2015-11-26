package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by guilherme on 25/11/15.
 * This is a entity example class
 */
@Entity
@Table(name = "logaccess")
public class LogAccess implements Serializable{
    private static final long serialVersionUID = 7538274026540646861L;

    @Id
    @SequenceGenerator(name = "log_seq_gen", sequenceName = "logaccess_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_seq_gen")
    private Long idLogAcess;

    @Temporal(TemporalType.DATE)
    private LocalDate accessDate;

    public LogAccess() {
    }

    public LogAccess(LocalDate accessDate) {
        this.accessDate = accessDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LocalDate getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(LocalDate accessDate) {
        this.accessDate = accessDate;
    }

    public Long getIdLogAcess() {
        return idLogAcess;
    }

    public void setIdLogAcess(Long idLogAcess) {
        this.idLogAcess = idLogAcess;
    }

    @Override
    public String toString() {
        return "LogAccess{" +
                "idLogAcess=" + idLogAcess +
                ", accessDate=" + accessDate +
                '}';
    }
}
