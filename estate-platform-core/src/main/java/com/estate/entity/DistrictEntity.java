package com.estate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "district")
public class DistrictEntity extends BaseEntity {

    private static final long serialVersionUID = -7758709700975343037L;

    @Column
    private String name;

    @Column(unique = true)
    private  String code;

    @OneToMany(mappedBy="district", fetch = FetchType.EAGER)
    private List<BuildingEntity> buildings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<BuildingEntity> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingEntity> buildings) {
        this.buildings = buildings;
    }
}
