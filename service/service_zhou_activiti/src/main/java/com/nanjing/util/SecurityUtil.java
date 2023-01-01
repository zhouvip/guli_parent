package com.nanjing.util;

/**
 * @author ：楼兰
 * @date ：Created in 2021/4/8
 * @description:
 **/

/*
@Component
public class SecurityUtil {
    private Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    @Autowired
    @Qualifier("myUserDetailsService")
    private UserDetailsService userDetailsService;

    public void logInAs(String username) {
        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (user == null) {
            throw new IllegalStateException("User " + username + " doesn't exist, please provide a valid user");
        }
        logger.info("> Logged in as: " + username);

        SecurityContextHolder.setContext(
                new SecurityContextImpl(
                        new Authentication() {
                            @Override
                            public Collection<? extends GrantedAuthority> getAuthorities() {
                                return user.getAuthorities();
                            }
                            @Override
                            public Object getCredentials() {
                                return user.getPassword();
                            }
                            @Override
                            public Object getDetails() {
                                return user;
                            }
                            @Override
                            public Object getPrincipal() {
                                return user;
                            }
                            @Override
                            public boolean isAuthenticated() {
                                return true;
                            }
                            @Override
                            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException { }
                            @Override
                            public String getName() {
                                return user.getUsername();
                            }
                        }));
        org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(username);
    }
}
*/
