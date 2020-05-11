package jd.jeicam.trainingapp.security.user;

import lombok.NoArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@NoArgsConstructor
public class UserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(String.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        if (this.supportsParameter(parameter)) {
            Authentication auth  = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails)auth.getPrincipal();
            return userDetails.getUsername();
        }
        return WebArgumentResolver.UNRESOLVED;
    }
}
