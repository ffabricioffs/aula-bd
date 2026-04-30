CREATE TABLE IF NOT EXISTS aluno(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    cpf  VARCHAR(11)  NOT NULL 
);

CREATE TABLE IF NOT EXISTS disciplina(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    sigla  VARCHAR(7)  NOT NULL 
);

CREATE TABLE IF NOT EXISTS matricula (
    aluno_id UUID REFERENCES aluno(id) ON DELETE CASCADE,
    disciplina_id UUID REFERENCES disciplina(id) ON DELETE CASCADE,
    data_matricula DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (aluno_id, disciplina_id)
);
