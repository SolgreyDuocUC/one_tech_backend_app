package com.duocuc.one_tech.repositories;

import com.duocuc.one_tech.models.ContactMessage
import org.springframework.data.jpa.repository.JpaRepository

interface ContactMessageRepository : JpaRepository<ContactMessage, Long>{
}



