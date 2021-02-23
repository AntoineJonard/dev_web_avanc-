package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/manager")
public class PersonneManager {

    @Autowired
    private PersonneRepository personneRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/jsonpersonne/{id}")
    public Personne getPersonne(@PathParam("id") int id){
        Optional<Personne> po = personneRepository.findById(id);
        if (po.isPresent())
            return po.get();
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/jsonpersonne")
    public List<Personne> getPersonnesWhere(@QueryParam("nom") String nom, @QueryParam("prenom") String prenom, @QueryParam("age") Integer age){
        return personneRepository.findByAgeOrPrenomOrNom(age, prenom, nom);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/jsonpersonne")
    public Personne addPersonne(@QueryParam("nom") String nom, @QueryParam("prenom") String prenom, @QueryParam("age") int age){
        Personne p = new Personne(nom, prenom, age);
        personneRepository.save(p);
        return p;
    }


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/jsonpersonne")
    public void deletePersonne(@QueryParam("id") int id){
        personneRepository.deleteById(id);
    }

    /*
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/jsonpersonne")
    public Personne updatePersonne(@QueryParam("nom") String nom, @QueryParam("prenom") String prenom, @QueryParam("age") Integer age,@QueryParam("id") Integer id){
        Personne toUpdate = null;
        for (Personne p : personnes){
            if (p.getId()==id)
                toUpdate = p;
        }
        if (toUpdate != null){
            if (age != null) toUpdate.setAge(age);
            if (nom != null) toUpdate.setNom(nom);
            if (prenom != null) toUpdate.setPrenom(prenom);
        }
        return toUpdate;
    }

     */

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/jsonpersonne")
    public void overwritePersonne(@QueryParam("nom") String nom, @QueryParam("prenom") String prenom, @QueryParam("age") int age,@QueryParam("id") int id){
        personneRepository.updatePersonne(nom,prenom,age,id);
    }

}
