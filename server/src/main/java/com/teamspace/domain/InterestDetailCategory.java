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
@Table(name = "interest_detail_category")
public class InterestDetailCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_main_category_id")
    private InterestMainCategory interestMainCategory;

    @OneToMany(mappedBy = "interestDetailCategory")
    private List<UserInterest> userInterests = new ArrayList<>();
}
