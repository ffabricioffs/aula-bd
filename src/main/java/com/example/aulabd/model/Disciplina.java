
package com.example.aulabd.model;

public class Disciplina {
    private String id, nome, sigla;

    //Constrtuor para a pagina do formulario
    public Disciplina(){
        
    }

    //Construtor bom para Select
    public Disciplina(String sigla, String id, String nome) {
        this.sigla = sigla;
        this.id = id;
        this.nome = nome;
    }

    //Construtor bom para insert
    public Disciplina(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public static Disciplina converter(Map<String,Object> registro){
        String nome = (String) registro.get("nome");
        UUID id = (UUID) registro.get("id");
        String sigla = (String) registro.get("sigla");
        return new Disciplina(sigla,id.toString(),nome);
    }

    public static ArrayList<Disciplina> converterTodos(List<Map<String,Object>> registros){
        ArrayList<Disciplina> aux = new ArrayList<>();
        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }

}
