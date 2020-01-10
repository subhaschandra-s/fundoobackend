package com.bridgelabz.fundoonotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.NotesInfo;

@Repository
public interface NoteRepository extends JpaRepository<NotesInfo,Long>
{

}
