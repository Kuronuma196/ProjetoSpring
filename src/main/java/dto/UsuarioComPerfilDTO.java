package dto;

public class UsuarioComPerfilDTO extends UsuarioBaseDTO {

    public UsuarioComPerfilDTO() {}

    public UsuarioComPerfilDTO(String email, String senha, String tipo, String nome, String cargo,
                               String areaAtuacao, String competencias, String bio) {
        setEmail(email);
        setSenha(senha);
        setTipo(tipo);
        setNome(nome);
        setCargo(cargo);
        setAreaAtuacao(areaAtuacao);
        setCompetencias(competencias);
        setBio(bio);
    }
    private Long id;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}
}
