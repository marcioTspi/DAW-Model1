/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util.relatorios;

import br.edu.ifsul.modelo.Curso;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Instituicao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class FabricaObjetos {
     public static List<Curso> carregaCursos() {
        List<Curso> lista = new ArrayList<>();
        Instituicao i = new Instituicao();
        Disciplina d = new Disciplina();
        Curso c = new Curso();
        
        i.setNome("UPF");
        i.setAnoFundacao(Calendar.getInstance());
        
        c.setNome("Economia");
        c.setSigla("EC");
        c.setDescricao("Bacharel em Economia");
        c.setAtivo(true);
        c.setInicioAtividades(Calendar.getInstance());
        c.setInstituicao(i);
        
        d.setNome("Contabilidade e Análise de Balanços");
        d.setDescricao("Teste 123");
        d.setCargaHoraria(80.00);
        d.setConhecimentosMinimos("História Econômica Geral");
        d.setCurso(c);
        c.adicionarDisciplina(d);
        lista.add(c);
        return lista;
    }
}
