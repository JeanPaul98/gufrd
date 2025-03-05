package com.acl.mswauth.playload;

import com.acl.mswauth.dto.formalite.FormaliteStatistiqueDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author kol on 12/24/24
 * @project msw-auth
 */
@Getter
@Setter
@NoArgsConstructor
public class ApiResponseFormalite {

    private String status;

    private String message;

    private List<FormaliteStatistiqueDto> result;

    public ApiResponseFormalite(String status, String message, List<FormaliteStatistiqueDto> result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}
