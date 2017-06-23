package com.cngc.pm.security;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.cngc.pm.service.LoginAttemptService;

public class PasswordAuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired  
    private LoginAttemptService loginAttemptService;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
	            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	        Object salt = null;

	        if (this.getSaltSource() != null) {
	            salt = this.getSaltSource().getSalt(userDetails);
	        }

	        if (authentication.getCredentials() == null) {
	            logger.debug("Authentication failed: no credentials provided");

	            throw new BadCredentialsException(messages.getMessage(
	                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
	        }

	        String presentedPassword = authentication.getCredentials().toString();

	        if (!getPasswordEncoder().isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
	            logger.debug("Authentication failed: password does not match stored value");

	            try {
					throw new BadCredentialsException(messages.getMessage(
					        "AbstractUserDetailsAuthenticationProvider.badCredentialsWithAttempts", new Object[] { loginAttemptService.getAttempts(userDetails.getUsername())}), userDetails);
				} catch (NoSuchMessageException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
}
