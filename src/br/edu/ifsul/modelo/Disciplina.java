/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "disciplina")
public class Disciplina implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_disciplina", sequenceName = "seq_disciplina_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_disciplina", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 40, message = "O nome não pode ter maix que {max} caracteres")
    @Column(name = "nome", length = 40, nullable = false)
    private String nome;

    @NotNull(message = "A descrição não pode ser nulo")
    @NotBlank(message = "A descrição não pode ser em branco")
    @Column(name = "descricao", columnDefinition = "text", nullable = false)
    private String descricao;

    @Column(name = "cargaHoraria", columnDefinition = "Numeric(10,2)", nullable = false)
    @NotNull(message = "A carga horaria não pode ser nulo")
    private Double cargaHoraria;
    
    @NotNull(message = "Os conhecimentos minimos não pode ser nulo")
    @NotBlank(message = "Os conhecimentos minimos não pode ser em branco")
    @Column(name = "conhecimentosMinimos", columnDefinition = "text", nullable = false)
    private String conhecimentosMinimos;
    
    @ManyToMany //cria a lista associativa de alunos
    @JoinTable(name = "alunosdaDisciplina",
            joinColumns = 
            @JoinColumn(name = "disciplina", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name = "aluno", referencedColumnName = "id", nullable = false))
    private List<Aluno> alunosdaDisciplina = new ArrayList<>();
    
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY) //qual atributo aponta para essa classe
    private List<Nota> nota = new ArrayList<>();
    
    @NotNull(message = "O curso não pode ser nulo")
    @ManyToOne
    @JoinColumn(name="curso", referencedColumnName = "id", nullable = false)
    @ForeignKey(name="fk_curso_id")
    private Curso curso;

    public Disciplina() {

    }
    
    public void AdicionaNota(Nota obj){
        obj.setDisciplina(this);
        this.getNota().add(obj);
    }
    
    public void removerNota(int index){
        this.getNota().remove(index);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getConhecimentosMinimos() {
        return conhecimentosMinimos;
    }

    public void setConhecimentosMinimos(String conhecimentosMinimos) {
        this.conhecimentosMinimos = conhecimentosMinimos;
    }

    public List<Aluno> getAlunosdaDisciplina() {
        return alunosdaDisciplina;
    }

    public void setAlunosdaDisciplina(List<Aluno> alunosdaDisciplina) {
        this.alunosdaDisciplina = alunosdaDisciplina;
    }
    
    public List<Nota> getNota() {
        return nota;
    }

    public void setNota(List<Nota> nota) {
        this.nota = nota;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Disciplina other = (Disciplina) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
}
