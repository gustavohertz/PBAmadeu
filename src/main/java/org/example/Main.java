package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Esta anotação é MÁGICA: ela diz "Isso é um servidor web, suba o Tomcat na porta 8080"
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // Inicia o servidor e aguarda requisições do Frontend
        SpringApplication.run(Main.class, args);

        System.out.println("🚀 Servidor rodando em: http://localhost:8080/funcionarios");
    }
}