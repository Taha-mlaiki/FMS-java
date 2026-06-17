package com.farm_management.fms.modules.users;


import com.farm_management.fms.common.enums.Role;
import com.farm_management.fms.modules.farms.Farm;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false,length = 100)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role  = Role.WORKER;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Farm> farms = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt ;

    @UpdateTimestamp
    private LocalDateTime updatedAt ;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        // This is where you return roles. For now, we will return an empty list.
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role));
    }

    public String getUsername(){
        return String.valueOf(this.id);
    }

}
