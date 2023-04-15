package com.example.javausersapi.config;

import com.example.javausersapi.model.Request;
import com.example.javausersapi.model.User;
import com.example.javausersapi.repository.RequestRepository;
import com.example.javausersapi.repository.UserRepository;
import com.example.javausersapi.service.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.HandlerInterceptor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ApiKeyInterceptor implements HandlerInterceptor {

    private UserRepository userRepository;
    private RequestRepository requestRepository;

    public ApiKeyInterceptor(UserRepository userRepository, RequestRepository requestRepository) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKeyRequest = request.getParameter("apikey");

        // Check if api key is null
        if (apiKeyRequest == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Missing API key");
            return false;
        }

        // Check if api key is in db
        if (!userRepository.existsByApikey(apiKeyRequest)){
            response.sendError(HttpStatus.UNAUTHORIZED.value(),"Invalid API key");
            return false;
        }

        // Get user
        User user = userRepository.getUserByApikey(apiKeyRequest).orElseThrow(() -> new UsernameNotFoundException("User Not Found with apikey: " + apiKeyRequest));

        //  Check if number of request today < 50
        List<Request> listRequestsUser = user.getRequests();
        int compteur = 0;
        String d = LocalDate.now().toString();
        for (Request r : listRequestsUser) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(r.getDate());
            if (d.equals(formattedDate)) {
                compteur++;
            }
        }
        if (compteur > 20){
            response.sendError(HttpStatus.UNAUTHORIZED.value(),"Max requests per day reached.");
            return false;
        }

        // add request for user in db
        Request req = new Request();
        req.setUser_id(user.getId());
        req.setDate(new Date());
        requestRepository.save(req);
        return true;
    }

}