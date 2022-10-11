package com.github.sorabh86.uigo.admin.returns;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.sorabh86.uigo.entity.ReturnOrder;

public interface ReturnRepo extends JpaRepository<ReturnOrder, Integer> {

}
