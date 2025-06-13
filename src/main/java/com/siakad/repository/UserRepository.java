package com.siakad.repository;

import com.siakad.entity.MUser;

import java.util.Optional;

public interface UserRepository extends MasterRepository<MUser, Long> {

    Optional<MUser> findByEmail(String email);
}
