# Controle de estacionamento - Parque Api

## Rodar projeto localmente:
Instale as dependecias do projeto:
```
    mvn clean install
```

## Crie um container para seu Banco de Dados:
```
    podman run --name mysql-demo -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=demo_Park -p 3306:3306 -d mysql:latest
```

## Crie sua tabela no Banco de Dados
```
    podman exec -it mysql-demo mysql -u root -proot -D demo_Park -e "
    CREATE TABLE usuarios (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(100) NOT NULL UNIQUE,
        password VARCHAR(200) NOT NULL,
        role ENUM('ROLE_ADMIN', 'ROLE_CLIENTE') NOT NULL,
        data_criacao DATETIME,
        data_modificacao DATETIME,
        criado_por VARCHAR(255),
        modificado_por VARCHAR(255)
    );"
```