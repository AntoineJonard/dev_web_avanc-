package com.example.demo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonneRepository extends CrudRepository<Personne, Integer> {
    List<Personne> findByAgeOrPrenomOrNom(Integer age, String prenom, String nom);

    @Query("update Personne p set p.nom = :nom, p.prenom = :prenom, p.age = :age where p.id = :id")
    @Modifying
    public int updatePersonne(@Param("nom") String nom, @Param("prenom") String prenom, @Param("age") Integer age, @Param("id") Integer id);
}
