package com.tomeespring.helloworld.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="salutation")
@NamedQueries({
        @NamedQuery(name ="JPASalutationEntity.findAll", query = "SELECT s FROM JPASalutationEntity s")
})
public class SpringJPASalutationEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String salutation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    @Override
    public String toString() {
        return "SpringJPASalutationEntity{" +
                "salutation='" + salutation + '\'' +
                '}';
    }
}
