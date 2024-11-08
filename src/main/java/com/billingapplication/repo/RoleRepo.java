package com.billingapplication.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billingapplication.model.Erole;
import com.billingapplication.model.Role;

@Repository
public interface RoleRepo  extends JpaRepository<Role, Long>{

	Role findByName(Erole name);
}
