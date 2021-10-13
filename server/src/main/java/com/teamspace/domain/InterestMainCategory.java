package com.teamspace.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "interest_main_category")
public class InterestMainCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "interestMainCategory")
    private List<InterestDetailCategory> interestDetailCategories = new ArrayList<>();

    @Override
    public String toString() {
        return "InterestMainCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
