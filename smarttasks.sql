USE db_smarttasks;

INSERT INTO tb_tarefas (descricao, status, titulo, data_atualizacao, data_limite)
VALUES (
    'Elaborar orçamento proposta ADS', 
    'PENDENTE', 
    'ADS', 
    current_timestamp(), 
    '2025-05-31 23:59:59'  
);
INSERT INTO tb_tarefas (descricao, status, titulo, data_atualizacao, data_limite)
VALUES (
    'Elaborar relatório redes sociais AEAS', 
    'PENDENTE', 
    'AEAS', 
    current_timestamp(), 
    '2025-05-31 23:59:59'  
);


SELECT * FROM tb_tarefas;