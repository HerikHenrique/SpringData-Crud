package com.example.bancodedados.regesc.service;

import com.example.bancodedados.regesc.orm.Disciplina;
import com.example.bancodedados.regesc.orm.Professor;
import com.example.bancodedados.regesc.repository.DisciplinaRepository;
import com.example.bancodedados.regesc.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudDisciplinaService {
    private DisciplinaRepository disciplinaRepository;
    private ProfessorRepository professorRepository;// Dependência da classe CrudProfessorService

    //O Spring automaticamente cria um objeto com a interface "ProfessorRepository"
    // E o injeta para nos no construtor na classe atual ==> Injeção de Dependência
    public CrudDisciplinaService(DisciplinaRepository disciplinaRepository,
                                 ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner sc) {

        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("Qual ação você deseja executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar nova Disciplina");
            System.out.println("2 - Atualizar uma Disciplina");
            System.out.println("3 - Visualizar todas as Disciplinas");
            System.out.println("4 Deletar Disciplina");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    this.cadastrar(sc);
                    break;
                case 2:
                    this.atualizar(sc);
                    break;
                case 3:
                    this.visualizar();
                    break;
                case 4:
                    this.deletar(sc);
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner sc) {
        System.out.println("Nome da Disciplina: ");
        String nome = sc.next(); // Le a Próxima String até achar um enter ou espaço

        System.out.println("Semestre: ");
        Integer semestre = sc.nextInt();

        System.out.println("Professor ID: ");
        Long professorId = sc.nextLong();

        Optional<Professor> optional = professorRepository.findById(professorId);
        if (optional.isPresent()) {
            Professor professor = optional.get();
            Disciplina disciplina = new Disciplina(nome, semestre, professor);
            disciplinaRepository.save(disciplina);
            System.out.println("Salvo!\n");
        } else {
            System.out.println("Professor ID " + professorId + "inválido");
        }
    }


    private void atualizar(Scanner sc) {
        System.out.println("Digite o Id da Disciplina a ser atualizada: ");
        Long id = sc.nextLong();

        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

        //Se o Hibernate encontrar um registro/tupla na tabela de professores com o Id igual ao passado pelo usuário
        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();

            System.out.println("Digite da Disciplina: ");
            String nome = sc.next();

            System.out.println("Semestre: ");
            Integer semestre = sc.nextInt();

            System.out.println("professor ID: ");
            Long professorId = sc.nextLong();

//            Professor professor = optional.get();
//            professor.setNome(nome);
//            professor.setProntuario(prontuario);

            Optional<Professor> optionalProfessor = this.professorRepository.findById(professorId);
            if (optionalProfessor.isPresent()) {
                Professor professor = optionalProfessor.get();

                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);

                disciplinaRepository.save(disciplina);
                System.out.println("Atualizado com Sucesso!\n");
            } else {
                System.out.println("Professor ID " + professorId + "invalido");
            }
        } else {
            System.out.println("O ID da disciplina informada: " + id + "é invalido\n");
        }
    }


    private void visualizar() {
        Iterable<Disciplina> disciplinas = this.disciplinaRepository.findAll();
        for (Disciplina disciplina : disciplinas) {
            System.out.println(disciplina);
        }
        System.out.println();
    }


    private void deletar(Scanner sc) {
        System.out.println("ID: ");
        Long id = sc.nextLong();
        this.disciplinaRepository.deleteById(id);
        System.out.println("Disciplina Deletada!\n");

    }
}

