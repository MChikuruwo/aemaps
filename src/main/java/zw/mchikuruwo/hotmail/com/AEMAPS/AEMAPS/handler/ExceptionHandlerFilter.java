package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.handler;


import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.exceptions.InvalidTokenException;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.api.ApiResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);

        } catch (InvalidTokenException e){
            setErrorResponse(HttpStatus.UNAUTHORIZED, e.getLocalizedMessage(), httpServletResponse);
            e.printStackTrace();

        } catch (RuntimeException e){
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), httpServletResponse);

        }

    }

    private void setErrorResponse(HttpStatus status, String message, HttpServletResponse response) {
        response.setStatus(status.value());
        response.setContentType("application/json");
        ApiResponse apiResponse = new ApiResponse(status.value(), message);
        Gson gson = new Gson();
        String json = gson.toJson(apiResponse);
        System.out.println(json);
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
