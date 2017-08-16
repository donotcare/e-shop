package ru.otus.eshop.system;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UserRepository extends CrudRepository<UserDetails, Long> {
    @RestResource(exported = false)
    UserDetails findByUsername(String username);

    @RestResource(exported = false)
    <S extends UserDetails> S save(S s);

    @RestResource(exported = false)
    <S extends UserDetails> Iterable<S> save(Iterable<S> iterable);

    @RestResource(exported = false)
    UserDetails findOne(Long aLong);

    @RestResource(exported = false)
    boolean exists(Long aLong);

    @RestResource(exported = false)
    long count();

    @RestResource(exported = false)
    void delete(Long aLong);

    @RestResource(exported = false)
    void delete(UserDetails userInformation);

    @RestResource(exported = false)
    void delete(Iterable<? extends UserDetails> iterable);

    @RestResource(exported = false)
    void deleteAll();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RestResource(exported = true)
    @Override
    Iterable<UserDetails> findAll();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RestResource(exported = true)
    @Override
    Iterable<UserDetails> findAll(Iterable<Long> longs);
}
