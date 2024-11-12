// → Código Java:

// Classe Usuário:

import java.util.List;


public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;  
    private String tipo;  


    // Construtor
   
    public Usuario(int id, String nome, String email, String senha, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }


    // Getters e Setters


    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getTipo() { return tipo; }


    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setTipo(String tipo) { this.tipo = tipo; }


    // Login 


    public boolean login(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }


    // Denúncias feitas por este usuário


    public List<Denuncia> visualizarDenuncias(List<Denuncia> denuncias) {
        return denuncias.stream()
                        .filter(d -> d.getUsuario().getId() == this.id)
                        .toList();
    }
}




// Classe Denúncia: 

import java.util.List;


public class Denuncia {
    private int id;
    private Usuario usuario;
    private String tipoProblema;
    private String descricao;
    private String localizacao;
    private String status;  
    private String dataCriacao;
    private String dataSolucao;


    // Construtor
    public Denuncia(int id, Usuario usuario, String tipoProblema, String descricao, String localizacao, String status, String dataCriacao) {
        this.id = id;
        this.usuario = usuario;
        this.tipoProblema = tipoProblema;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataSolucao = null;
    }


    // Getters e Setters
    public int getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public String getTipoProblema() { return tipoProblema; }
    public String getDescricao() { return descricao; }
    public String getLocalizacao() { return localizacao; }
    public String getStatus() { return status; }
    public String getDataCriacao() { return dataCriacao; }
    public String getDataSolucao() { return dataSolucao; }


    public void setStatus(String status) { this.status = status; }
    public void setDataSolucao(String dataSolucao) { this.dataSolucao = dataSolucao; }


    // Cadastrar a denúncia
    public static void cadastrarDenuncia(List<Denuncia> denuncias, Denuncia denuncia) {
        denuncias.add(denuncia);
    }


    // Visualizar todas as denúncias
    public static List<Denuncia> visualizarDenuncias(List<Denuncia> denuncias) {
        return denuncias;
    }


    // Fechar a denúncia
    public void fecharDenuncia() {
        this.status = "fechada";
        this.dataSolucao = "data_atual";
    }
}


// Classe Comentário:


public class Comentario {
    private int id;
    private Denuncia denuncia;
    private Usuario usuario;
    private String comentario;
    private String dataComentario;


    // Construtor
    public Comentario(int id, Denuncia denuncia, Usuario usuario, String comentario, String dataComentario) {
        this.id = id;
        this.denuncia = denuncia;
        this.usuario = usuario;
        this.comentario = comentario;
        this.dataComentario = dataComentario;
    }


    // Getters e Setters
    public int getId() { return id; }
    public Denuncia getDenuncia() { return denuncia; }
    public Usuario getUsuario() { return usuario; }
    public String getComentario() { return comentario; }
    public String getDataComentario() { return dataComentario; }


    public void setComentario(String comentario) { this.comentario = comentario; }


    // Adicionar um comentário
    public static void adicionarComentario(List<Comentario> comentarios, Comentario comentario) {
        comentarios.add(comentario);
    }


    // Comentários de uma denúncia
    public static List<Comentario> visualizarComentarios(List<Comentario> comentarios) {
        return comentarios;
    }
}




// Classe Curtida:



public class Curtida {
    private int id;
    private Denuncia denuncia;
    private Usuario usuario;
    private String dataCurtida;


    // Construtor
    public Curtida(int id, Denuncia denuncia, Usuario usuario, String dataCurtida) {
        this.id = id;
        this.denuncia = denuncia;
        this.usuario = usuario;
        this.dataCurtida = dataCurtida;
    }


    // Getters e Setters
    public int getId() { return id; }
    public Denuncia getDenuncia() { return denuncia; }
    public Usuario getUsuario() { return usuario; }
    public String getDataCurtida() { return dataCurtida; }


    public void setDataCurtida(String dataCurtida) { this.dataCurtida = dataCurtida; }


    // Adicionar uma curtida
    public static void adicionarCurtida(List<Curtida> curtidas, Curtida curtida) {
        curtidas.add(curtida);
    }


    // Remover uma curtida
    public static void removerCurtida(List<Curtida> curtidas, Curtida curtida) {
        curtidas.remove(curtida);
    }
}


// Classe Dashboard: 



import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Dashboard {


    // Abertas e fechadas por mês


    public void gerarRelatorioMensal(List<Denuncia> denuncias) {
        Map<String, Long> report = denuncias.stream()
                .collect(Collectors.groupingBy(Denuncia::getStatus, Collectors.counting()));
       
        System.out.println("Relatório Mensal:");
        System.out.println("Denúncias Abertas: " + report.getOrDefault("aberta", 0L));
        System.out.println("Denúncias Fechadas: " + report.getOrDefault("fechada", 0L));
    }


    // Tipo de problema
   
    public void contarDenunciasPorTipo(List<Denuncia> denuncias) {
        Map<String, Long> countByTipo = denuncias.stream()
                .collect(Collectors.groupingBy(Denuncia::getTipoProblema, Collectors.counting()));


        System.out.println("Denúncias por Tipo de Problema:");
        countByTipo.forEach((tipo, count) -> System.out.println(tipo + ": " + count));
    }
}
