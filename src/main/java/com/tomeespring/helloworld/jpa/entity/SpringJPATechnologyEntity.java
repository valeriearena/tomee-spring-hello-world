package com.tomeespring.helloworld.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by valerie on 1/11/16.
 */
@Entity
@Table(name="technology")
@NamedQueries({
        @NamedQuery(name ="JPATechnologyEntity.findAll", query = "SELECT t FROM JPATechnologyEntity t")
})
public class SpringJPATechnologyEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String technology;

    @Column
    private String description;

    @ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="salutation_id")
    private SpringJPASalutationEntity springJpaSalutationEntity = new SpringJPASalutationEntity();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technologyName) {
        this.technology = technologyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String technologyDescription) {
        this.description = technologyDescription;
    }

    public SpringJPASalutationEntity getSpringJpaSalutationEntity() {
        return springJpaSalutationEntity;
    }

    public void setSpringJpaSalutationEntity(SpringJPASalutationEntity springJpaSalutationEntity) {
        this.springJpaSalutationEntity = springJpaSalutationEntity;
    }

    @Override
    public String toString() {
        return "SpringJPATechnologyEntity{" +
                "id=" + id +
                ", technologyName='" + technology + '\'' +
                ", technologyDescription='" + description + '\'' +
                ", springJpaSalutationEntity=" + springJpaSalutationEntity +
                '}';
    }
}
