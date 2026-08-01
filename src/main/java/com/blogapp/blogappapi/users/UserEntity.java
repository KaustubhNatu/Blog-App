package com.blogapp.blogappapi.users;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;


@Entity(name="users")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable=false)
    private Long id;

    @Column(nullable=false)
    @NonNull
    private String username;

    @Column(nullable=false)
    @NonNull
    private String email;

    @Column(nullable=true)
    @Nullable
    private String bio;

    @Column(nullable=true)
    @Nullable
    private String image;

}
