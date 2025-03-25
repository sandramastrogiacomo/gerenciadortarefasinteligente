Gerenciador de Tarefas Inteligente - Projeto Integrador Ada Tech/Meli
Ada Tech
Meli
Java
Spring Boot

📌 Contexto do Projeto
Este projeto foi desenvolvido como Projeto Integrador do módulo Técnicas de Programação do curso Desenvolva + Meli da Ada Tech em parceria com o Mercado Livre.

🎯 Objetivos de Aprendizado
Aplicar conceitos avançados de Java e Spring Boot

Implementar boas práticas de programação

Desenvolver habilidades de arquitetura de software

Praticar trabalho em equipe (Git, documentação)

Integrar banco de dados relacionais (MySQL)

🔍 Requisitos do Módulo Atendidos
Técnica Aplicada	Onde foi Implementada
Programação Orientada a Objetos	Modelagem das classes e relações
Tratamento de Exceções	Validações e handlers de erro
Coleções e Streams	Operações com listas e filtros
Banco de Dados	Integração com MySQL
Testes Unitários	Suite de testes automatizados
Documentação	README e JavaDoc
🛠️ Tecnologias Utilizadas
mermaid
Copy
pie
    title Stack Tecnológica
    "Java 17" : 35
    "Spring Boot" : 30
    "MySQL" : 20
    "Insomnia" : 10
    "Maven" : 5
📚 Estrutura do Código
Copy
src/
├── main/
│   ├── java/com/ada/smarttasks/
│   │   ├── config/       # Configurações do Spring
│   │   ├── controller/   # Endpoints REST
│   │   ├── dto/          # Objetos de transferência
│   │   ├── exceptions/   # Tratamento de erros
│   │   ├── model/        # Entidades JPA
│   │   ├── repository/   # Interfaces Spring Data
│   │   ├── service/      # Lógica de negócio
│   │   └── utils/        # Utilitários
├── test/                 # Testes unitários
└── resources/            # Configurações
🚀 Como Executar o Projeto
Configurar MySQL:

sql
Copy
CREATE DATABASE ada_tarefas;
CREATE USER 'ada_user'@'localhost' IDENTIFIED BY 'ada123';
GRANT ALL PRIVILEGES ON ada_tarefas.* TO 'ada_user'@'localhost';
Clonar e executar:

bash
Copy
git clone https://github.com/seu-user/gerenciador-tarefas-ada.git
cd gerenciador-tarefas-ada
mvn spring-boot:run
🔗 Links Úteis
Documentação Ada Tech

Material do Curso

Referência Spring Boot

👨‍💻 Autora
[Sandra Mastrogiacomo] - [sandramastrogiacomo@hotmail.com]



Projeto desenvolvido como parte do programa de formação Ada Tech + Mercado Livre
