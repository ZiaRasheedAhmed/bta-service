package com.zia.btaservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zia.btaservice.Model.BookTracker;

public interface IBookRepository extends JpaRepository<BookTracker, Long> {
    
}
