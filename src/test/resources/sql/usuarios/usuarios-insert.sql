CREATE TABLE IF NOT EXISTS USUARIOS (
    id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);


INSERT INTO USUARIOS (id, username, password, role) VALUES (100, 'ana@email.com', '123456', 'ROLE_ADMIN');
INSERT INTO USUARIOS (id, username, password, role) VALUES (101, 'bia@email.com', '123456', 'ROLE_CLIENTE');
INSERT INTO USUARIOS (id, username, password, role) VALUES (102, 'bob@email.com', '123456', 'ROLE_CLIENTE');
