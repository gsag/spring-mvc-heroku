package entity;

import util.LocalDateConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/*
 * Created by guilherme on 30/11/15.
 */
@Entity
@Table(name = "exemplo")
public class Exemplo implements Serializable {
    private static final long serialVersionUID = 613393136989349826L;

    //    Para gerar sequences, use o código abaixo
    //    @SequenceGenerator(name = "log_seq_gen", sequenceName = "exemplo_sequence")
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_seq_gen")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "accessDate")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate accessDate;

    public Exemplo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(LocalDate accessDate) {
        this.accessDate = accessDate;
    }

    @Override
    public String toString() {
        return "Exemplo{" +
                "id=" + id +
                ", accessDate=" + accessDate +
                '}';
    }
}
