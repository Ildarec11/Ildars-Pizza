package security.details;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

import java.util.Optional;

@Service("customUserDetailsImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDetailsImpl(user);
        } else {
            throw new SecurityException("User with email <" + email + "> not found");
        }
    }
}
