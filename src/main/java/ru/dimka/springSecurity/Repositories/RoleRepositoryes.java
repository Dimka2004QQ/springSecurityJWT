package ru.dimka.springSecurity.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dimka.springSecurity.Entityes.Role;

import java.util.Optional;

@Repository
public interface RoleRepositoryes extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(String name);

}
