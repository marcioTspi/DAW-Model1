/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Especialidade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexv
 */
public class TestePersistirEspecialidade {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirEspecialidade() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("DAW-Modelo-1-PU");
        em = emf.createEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void teste(){
        boolean exception = false;
        try {
            Especialidade es = new Especialidade();
            es.setNome("Inglês");
            em.getTransaction().begin();
            em.persist(es);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
        }
        
        // verifica o valor do atributo exception continua false
        Assert.assertEquals(false, exception); 
    }
    
}
