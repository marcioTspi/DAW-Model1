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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Inheritance(strategy =InheritanceType.JOINED )
@Table(name = "curso")
public class Curso implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_curso", sequenceName = "seq_curso_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_curso", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 60, message = "O nome não pode ter maix que {max} caracteres")
    @Column(name = "nome", length = 60, nullable = false)
    private String nome;
    
    @NotNull(message = "A sigla não pode ser nulo")
    @NotBlank(message = "A sigla não pode ser em branco")
     @Length(max = 4, message = "A sigla não pode ter maix que {max} caracteres")
    @Column(name = "sigla", length = 4, nullable = false)
    private String sigla;
    
    @NotNull(message = "A descrição não pode ser nulo")
    @NotBlank(message = "A descrição não pode ser em branco")
    @Column(name = "descricao", columnDefinition = "text", nullable = false)
    private String descricao;
    
    @NotNull(message = "Ativo não pode ser nulo")
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "inicioAtividades", nullable = false)
    private Calendar inicioAtividades;
    
    @NotNull(message = "A instituição não pode ser nula")
    @ManyToOne
    @JoinColumn(name="instituicao", referencedColumnName = "id",nullable = false)
    @ForeignKey(name="fk_instituicao_id")
    private Instituicao instituicao;
    
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY) //qual atributo aponta para essa classe
    private List<Disciplina> disciplinas = new ArrayList<>();
    
    public Curso(){
        
    }
    
    public void adicionarDisciplina(Disciplina obj){
        obj.setCurso(this);
        this.disciplinas.add(obj);
    }

    public void removerDisciplina(int index){
        this.disciplinas.remove(index);
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Calendar getInicioAtividades() {
        return inicioAtividades;
    }

    public void setInicioAtividades(Calendar inicioAtividades) {
        this.inicioAtividades = inicioAtividades;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    } 
    
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Curso other = (Curso) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
