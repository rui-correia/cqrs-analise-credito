package br.com.braz.rui.analisecreditocqrs.application.usecase;

import br.com.braz.rui.analisecreditocqrs.application.read.handler.BuscaUsuarioHandler;
import br.com.braz.rui.analisecreditocqrs.application.read.handler.ListarPropostaHandler;
import br.com.braz.rui.analisecreditocqrs.application.read.queries.PropostaQuery;
import br.com.braz.rui.analisecreditocqrs.application.write.command.AprovarPropostaCommand;
import br.com.braz.rui.analisecreditocqrs.application.write.command.NovaPropostaCommand;
import br.com.braz.rui.analisecreditocqrs.application.write.handler.AprovarPropostaHandler;
import br.com.braz.rui.analisecreditocqrs.application.write.handler.NovaPropostaHandler;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/proposta")
public class PropostaController {

    private NovaPropostaHandler novaPropostaHandler;
    private ListarPropostaHandler queryHandler;
    private BuscaUsuarioHandler buscaUsuarioHandler;
    private AprovarPropostaHandler aprovarPropostaHandler;


    public PropostaController(NovaPropostaHandler novaPropostaHandler, ListarPropostaHandler queryHandler, BuscaUsuarioHandler buscaUsuarioHandler, AprovarPropostaHandler aprovarPropostaHandler) {
        this.aprovarPropostaHandler = aprovarPropostaHandler;
        this.novaPropostaHandler = novaPropostaHandler;
        this.queryHandler = queryHandler;
        this.buscaUsuarioHandler = buscaUsuarioHandler;
    }

    @PostMapping
    public ResponseEntity<String> novaProposta(@RequestBody NovaPropostaCommand command) {
        novaPropostaHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body("Proposta gravada com sucesso.");
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Set<PropostaQuery>> listarPropostas(@Valid @PathVariable Long idUsuario) throws NotFoundException {

        Set<PropostaQuery> propostasEmAnalise = queryHandler.handle(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(propostasEmAnalise);
    }

    @PatchMapping
    public ResponseEntity<String> aprovarProposta(@RequestParam Long idUsuario, @RequestParam Long idProposta) throws NotFoundException {

        if (!buscaUsuarioHandler.isAnalista(idUsuario)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        AprovarPropostaCommand command = new AprovarPropostaCommand(idUsuario, idProposta);
        aprovarPropostaHandler.handle(command);
        return ResponseEntity.status(HttpStatus.OK).body("Proposta aprovada");
    }
}
