
package com.rashid.hotelReservation.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.rashid.hotelReservation.Service.UserService;

@Component
public class customAuthenticationProvider implements AuthenticationProvider
{
	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	private static final Logger logger = Logger.getLogger(customAuthenticationProvider.class.getName());
	
		@SuppressWarnings("serial")
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException
		{
				String userName = authentication.getName();
//				
				String password = authentication.getCredentials().toString();

				int access_condition=userService.authorizedUser(userName, password);
				if (access_condition==1)
				{
					List<GrantedAuthority> grantedAuthsub = new ArrayList<>();
					String access=userService.getAuthority(userName);
					String arraym[]= access.split(",");
					for (final String tempmodule: arraym){
					
					
						grantedAuthsub.add(new GrantedAuthority() {
						@Override
						public String getAuthority() {return tempmodule;}
					});
					}
	Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, grantedAuthsub);
					System.out.println(auth);
					logger.info("ALL ACCESS -----------------------------------"+grantedAuthsub);
					Object details = authentication.getDetails();
						return auth;
				}
				

				else if(access_condition==2)
				{
					logger.warning("----------------------------------------invalid credentials!--------------------------------------");
						throw new AuthenticationCredentialsNotFoundException("invalid credentials!");
				
				}
				else if(access_condition==3)
				{
				
					logger.warning("----------------------------------------user doesnot exists --------------------------------------");
						throw new AuthenticationCredentialsNotFoundException("user doesnot exists ");
				}
				else 
				{
					logger.warning("----------------------------------------Error in server,plelase contact to administrator--------------------------------------");
					throw new AuthenticationCredentialsNotFoundException("Error in server,plelase contact to administrator");
				}
		}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
		
		