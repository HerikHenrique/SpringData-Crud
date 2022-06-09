package com.example.bancodedados.regesc.service;

import com.example.bancodedados.regesc.orm.Professor;
import com.example.bancodedados.regesc.repository.ProfessorRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudProfessorService {
    private ProfessorRepository professorRepository;// Dependência da classe CrudProfessorService

    //O Spring automaticamente cria um objeto com a interface "ProfessorRepository"
    // E o injeta para nos no construtor na classe atual ==> Injeção de Dependência
    public CrudProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner sc){

        Boolean isTrue = true;

        while(isTrue){
            System.out.println("Qual ação você deseja executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo professor");
            System.out.println("2 - Atualizar um Professor");

            int opcao = sc.nextInt();

            switch (opcao){
                case 1:
                    this.cadastrar(sc);
                    break;
                case 2:
                    this.atualizar(sc);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner sc){
        System.out.println("Digite o nome do professor");
        String nome = sc.next(); // Le a Próxima String até achar um enter ou espaço

        System.out.println("Digite o prontuário do professor: ");
        String prontuario = sc.next();

        Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor Salvo no Banco!!!\n");
    }


    private void atualizar(Scanner sc){
        System.out.println("Digite o Id do Professor a ser atualizado: ");
        Long id = sc.nextLong();

        Optional<Professor> optional = this.professorRepository.findById(id);

        //Se o Hibernate encontrar um registro/tupla na tabela de professores com o Id igual ao passado pelo usuário
        if(optional.isPresent()){

            System.out.println("Digite o nome do professor");
            String nome = sc.next(); // Le a Próxima String até achar um enter ou espaço

            System.out.println("Digite o prontuário do professor: ");
            String prontuario = sc.next();

            Professor professor = optional.get();
            professor.setNome(nome);
            professor.setProntuario(prontuario);

            professorRepository.save(professor);//Atualiza ou persiste o objeto/registro ou tupla no Banco de Dados
            System.out.println("Professor Atualizado com sucesso!!!\n");
        }
        else{
            System.out.println("O id do professor informado: " + id + "é invalido\n");
        }
    }
}
