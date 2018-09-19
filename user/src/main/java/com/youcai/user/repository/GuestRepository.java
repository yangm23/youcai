package com.youcai.user.repository;



import com.youcai.user.dataobject.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, String> {
    Guest findByPhone(String phone);
}
