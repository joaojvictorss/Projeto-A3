CREATE DATABASE banco;

USE banco;

CREATE TABLE usuarios (
    usuario_id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(150) NOT NULL UNIQUE,
    suspeito BOOLEAN DEFAULT FALSE
);

CREATE TABLE contas (
    conta_id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    numero_conta VARCHAR(6) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    saldo DECIMAL(12,2) DEFAULT 0,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    suspeito BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id)
);

CREATE TABLE transacoes (
    transacao_id INT AUTO_INCREMENT PRIMARY KEY,
    conta_origem INT,    -- Pode ser nulo em operações como depósito
    conta_destino INT,   -- Pode ser nulo em operações como saque
    valor DECIMAL(12,2) NOT NULL CHECK (valor > 0),
    data_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(20) NOT NULL,  -- Exemplo: 'deposito', 'saque', 'transferencia', 'pix'
    suspeito BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (conta_origem) REFERENCES contas(conta_id),
    FOREIGN KEY (conta_destino) REFERENCES contas(conta_id)
);

CREATE TABLE chave_email (
    chave_email_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(150) NOT NULL UNIQUE,
    conta_id INT NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    suspeito BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (conta_id) REFERENCES contas(conta_id)
);

-- 10 usuários
INSERT INTO usuarios (nome, cpf, data_nascimento, telefone, email) VALUES
('João da Silva',   '111.111.111-11', '1980-05-15', '11987654321', 'joao.silva@gmail.com'),
('Maria Oliveira',   '222.222.222-22', '1985-04-20', '11912345678', 'maria.oliveira@outlook.com'),
('Carlos Souza',     '333.333.333-33', '1990-03-25', '11923456789', 'carlos.souza@hotmail.com'),
('Ana Pereira',      '444.444.444-44', '1995-02-10', '11934567890', 'ana.pereira@yahoo.com'),
('Pedro Gomes',      '555.555.555-55', '1988-11-30', '11945678901', 'pedro.gomes@icloud.com'),
('Luciana Castro',   '666.666.666-66', '1979-07-14', '11956789012', 'luciana.castro@me.com'),
('Marcos Lima',      '777.777.777-77', '1992-08-22', '11967890123', 'marcos.lima@mac.com'),
('Fernanda Costa',   '888.888.888-88', '1983-06-17', '11978901234', 'fernanda.costa@gmail.com'),
('Rafael Almeida',   '999.999.999-99', '1975-01-05', '11989012345', 'rafael.almeida@hotmail.com'),
('Beatriz Martins',  '000.000.000-00', '1998-12-12', '11990123456', 'beatriz.martins@yahoo.com');

-- 10 contas
-- senha criptografada
INSERT INTO contas (usuario_id, numero_conta, senha, saldo) VALUES
(1, '000001', SHA2('senha123', 256), 1500.00),
(2, '000002', SHA2('minhaSenha', 256), 2000.00),
(3, '000003', SHA2('segura2024', 256), 3500.00),
(4, '000004', SHA2('senhaForte!', 256), 500.00),
(5, '000005', SHA2('12345678', 256), 750.00),
(6, '000006', SHA2('senhaBanco', 256), 1200.00),
(7, '000007', SHA2('confidencial', 256), 2200.00),
(8, '000008', SHA2('acessoSeguro', 256), 3100.00),
(9, '000009', SHA2('senhaTop', 256), 1800.00),
(10, '000010', SHA2('senhaUltima', 256), 900.00);

-- 10 chaves de email
INSERT INTO chave_email (email, conta_id) VALUES
('joao.silva@gmail.com',    1),
('maria.oliveira@outlook.com', 2),
('carlos.souza@hotmail.com',   3),
('ana.pereira@yahoo.com',    4),
('pedro.gomes@icloud.com',    5),
('luciana.castro@me.com', 6),
('marcos.lima@mac.com',    7),
('fernanda.costa@gmail.com', 8),
('rafael.almeida@hotmail.com', 9),
('beatriz.martins@yahoo.com', 10);

-- Exemplo: marcar uma chave como suspeita
UPDATE chave_email SET suspeito = TRUE WHERE email = 'carlos.souza@hotmail.com';
UPDATE chave_email SET suspeito = TRUE WHERE email = 'beatriz.martins@yahoo.com';
UPDATE chave_email SET suspeito = TRUE WHERE email = 'rafael.almeida@hotmail.com';
UPDATE chave_email SET suspeito = TRUE WHERE email = 'marcos.lima@mac.com';
UPDATE chave_email SET suspeito = TRUE WHERE email = 'luciana.castro@me.com';