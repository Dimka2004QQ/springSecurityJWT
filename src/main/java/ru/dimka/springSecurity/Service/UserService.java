package ru.dimka.springSecurity.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dimka.springSecurity.Entityes.User;
import ru.dimka.springSecurity.Repositories.RoleRepositoryes;
import ru.dimka.springSecurity.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //сгенерить для всех финал конструктор
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepositoryes roleRepositoryes;


    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
          String.format("Пользоваетль '%s' не найден",username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public void createUser(User user){
        user.setRoles(List.of(roleRepositoryes.findByName("ROLE_USER").get()));
        userRepository.save(user);
    }













}
