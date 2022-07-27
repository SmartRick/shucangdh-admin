package cn.smartrick.metaverse.common.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author hu
 * @version 1.0
 * @description: TODO
 * @date 2021/9/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDetail implements UserDetails, Serializable {
    private Long id;
    private String password;
    private String username;
    private Integer state;
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;

    @TableField(exist = false)
    private boolean accountNonExpired;
    @TableField(exist = false)
    private boolean accountNonLocked;
    @TableField(exist = false)
    private boolean credentialsNonExpired;
    @TableField(exist = false)
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.state != null && this.state == 200;
    }

}
