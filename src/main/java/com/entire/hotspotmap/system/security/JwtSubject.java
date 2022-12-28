package com.entire.hotspotmap.system.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtSubject {
    /**
     * 账号
     */
    private String username;
}
