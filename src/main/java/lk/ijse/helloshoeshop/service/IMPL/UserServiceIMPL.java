package lk.ijse.helloshoeshop.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.repository.UserServiceDAO;
import lk.ijse.helloshoeshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    final private UserServiceDAO userDao;
    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userDao.findByEmail(username).
                        orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }
}
