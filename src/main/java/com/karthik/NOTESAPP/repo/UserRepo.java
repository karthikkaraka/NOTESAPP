package com.karthik.NOTESAPP.repo;

import com.karthik.NOTESAPP.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
  public User findByUserName(String username);
}
