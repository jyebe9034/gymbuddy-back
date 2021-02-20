package com.gymbuddy.backgymbuddy.admin.user.domain;

import com.gymbuddy.backgymbuddy.admin.base.Address;
import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.cart.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseDomain implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    /**
     * 아이디
     */
    @Column(length = 20, nullable = false, unique = true)
    private String identity;

    /**
     * 이메일
     */
    @Column(length = 100, nullable = false, unique = true)
    private String email;

    /**
     * 비밀번호
     */
    @Column(length = 200, nullable = false)
    private String password;

    /**
     * 이름
     */
    @Column(length = 30, nullable = false)
    private String name;

    /**
     * 전화번호
     */
    @Column(length = 15, nullable = false)
    private String phone;

    /**
     * 회원등급
     */
    @Column(length = 10, nullable = false)
    private String grade;

    /**
     * 주소
     */
    @Embedded
    private Address address;

    /**
     * 장바구니
     */
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Cart> cartList = new ArrayList<>();

    /**
    * 광고 및 뉴스레터 수신동의
     */
    @Column(length = 1, nullable = false)
    private String agreeYn;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    /**
     * 계정 만료에 대한 정책
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠금에 대한 정책
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 인증 정보 만료에 대한 정책
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정 활성상태에 관한 정책
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
