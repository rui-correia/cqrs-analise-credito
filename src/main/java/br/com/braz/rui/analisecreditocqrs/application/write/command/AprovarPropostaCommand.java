package br.com.braz.rui.analisecreditocqrs.application.write.command;

import com.sun.istack.NotNull;

public class AprovarPropostaCommand {

    @NotNull
    private Long idUsuario;
    @NotNull
    private Long idProposta;

    public AprovarPropostaCommand(Long idUsuario, Long idProposta) {
        this.idUsuario = idUsuario;
        this.idProposta = idProposta;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
