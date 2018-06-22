/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.modelo.Professor;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class TestePersistirProfessor {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirProfessor() {
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
            Professor p = new Professor();
            Especialidade es = em.find(Especialidade.class, 3);
            p.setEspecialidade(es);
            p.setTopicosInteresse("teses");
            p.setTitulacao("wqe");
            p.setNome("qewqe");
            p.setEmail("mqweqw@hotmail.com");
            p.setNascimento(new GregorianCalendar(1982, Calendar.MARCH, 15));
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
        }
        
        // verifica o valor do atributo exception continua false
        Assert.assertEquals(false, exception); 
    }
    
}
