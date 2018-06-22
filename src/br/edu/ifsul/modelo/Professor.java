 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Inheritance(strategy =InheritanceType.JOINED )
@Table(name = "professor")
public class Professor extends Aluno implements Serializable{
    
    @NotNull(message = "A titulação não pode ser nulo")
    @NotBlank(message = "A titulação não pode ser em branco")
    @Length(max = 40, message = "A titulação não pode ter maix que {max} caracteres")
    @Column(name = "titulacao", length = 40, nullable = false)
    private String titulacao;
    
    @NotNull(message = "Os topicosInteresse não pode ser nulo")
    @NotBlank(message = "Os topicosInteresse não pode ser em branco")
    @Column(name = "topicosInteresse", columnDefinition="text", nullable = false)
    private String topicosInteresse;
    
    @NotNull(message = "A especialidade não pode ser nula")
    @ManyToOne
    @JoinColumn(name="especialidade", referencedColumnName = "id",nullable = false)
    @ForeignKey(name="fk_especialidade_id")
    private Especialidade especialidade;
    
    public Professor(){
        
    }
    
    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public String getTopicosInteresse() {
        return topicosInteresse;
    }

    public void setTopicosInteresse(String topicosInteresse) {
        this.topicosInteresse = topicosInteresse;
    }   

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
    
}
