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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Inheritance(strategy =InheritanceType.JOINED )
@Table(name = "nota")
public class Nota implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_nota", sequenceName = "seq_nota_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_nota", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "nota01", columnDefinition = "Numeric(10,2)", nullable = false)
    @NotNull(message = "A nota 01 não pode ser nula")
    @Min(value = 0, message = "A nota 1 não pode ser menor que {value}")
    private Double nota01;
    
    @Column(name = "nota02", columnDefinition = "Numeric(10,2)", nullable = false)
    @NotNull(message = "A nota 02 não pode ser nula")
    @Min(value = 0, message = "A nota 2 não pode ser menor que {value}")
    private Double nota02;
    
    @Column(name = "media", columnDefinition = "Numeric(10,2)", nullable = false)
    @NotNull(message = "A media não pode ser nula")
    @Min(value = 0, message = "A media não pode ser menor que {value}")
    private Double media;
    
    @NotNull(message = "O aluno não pode ser nula")
    @ManyToOne
    @JoinColumn(name="aluno", referencedColumnName = "id",nullable = false)
    @ForeignKey(name="fk_aluno_id")
    private Aluno aluno;
    
    @NotNull(message = "A disciplina não pode ser nula")
    @ManyToOne
    @JoinColumn(name="disciplina", referencedColumnName = "id", nullable = false)
    @ForeignKey(name="fk_disciplina_id")
    private Disciplina disciplina;
    
    public Nota(){
        this.media = 0.0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNota01() {
        return nota01;
    }

    public void setNota01(Double nota01) {
        this.nota01 = nota01;
    }

    public Double getNota02() {
        return nota02;
    }

    public void setNota02(Double nota02) {
        this.nota02 = nota02;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nota other = (Nota) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
