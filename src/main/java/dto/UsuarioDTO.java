package dto;

public class UsuarioDTO extends UsuarioBaseDTO {
    private Long id;

    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String email, String senha, String tipo) {
        this.id = id;
        setEmail(email);
        setSenha(senha);
        setTipo(tipo);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
