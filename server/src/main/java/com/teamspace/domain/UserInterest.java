package com.teamspace.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_interest")
@Builder
public class UserInterest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_detail_category_id")
    private InterestDetailCategory interestDetailCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static UserInterest createUserInterest(InterestDetailCategory interestDetailCategory, User user){
        return  UserInterest.builder()
                .interestDetailCategory(interestDetailCategory)
                .user(user)
                .build();
    }

    @Override
    public String toString() {
        return "UserInterest{" +
                "id=" + id +
                ", interestDetailCategory=" + interestDetailCategory +
                '}';
    }
}
