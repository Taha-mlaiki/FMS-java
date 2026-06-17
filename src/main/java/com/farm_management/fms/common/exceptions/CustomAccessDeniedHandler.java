package com.farm_management.fms.common.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        // Change to SC_UNAUTHORIZED (401) if you want 401 instead of 403
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        // Construct your custom JSON error payload
        String jsonResponse = String.format(
                "{ \"error\": \"Forbidden\", \"message\": \"You do not have the required permissions/roles to access this resource.\", \"path\": \"%s\" }",
                request.getRequestURI()
        );

        response.getWriter().write(jsonResponse);
    }
}
