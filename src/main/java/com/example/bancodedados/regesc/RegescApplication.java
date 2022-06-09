package com.example.bancodedados.regesc;

import com.example.bancodedados.regesc.orm.Professor;
import com.example.bancodedados.regesc.repository.ProfessorRepository;
import com.example.bancodedados.regesc.service.CrudProfessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RegescApplication implements CommandLineRunner {
	private CrudProfessorService professorService;

    //Objetos passados por parâmetro são injetados automaticamente pelo Spring
    //pq suas classes possuem a anotação @Service
	public RegescApplication(CrudProfessorService professorService){
        this.professorService = professorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Boolean isTrue = true;

        Scanner sc = new Scanner(System.in);

        while(isTrue){
            System.out.println("\nQual Entidade você deseja interagir?");
            System.out.println("0 - Sair");
            System.out.println("1 - Interagir com o Professor");

            int opcao = sc.nextInt();

            switch(opcao){
                case 1:
                    this.professorService.menu(sc);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
	}
}
