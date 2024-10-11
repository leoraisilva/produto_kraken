package br.com.kraken.Produto.java.controller;

import br.com.kraken.Produto.java.dto.ProdutoModelDTO;
import br.com.kraken.Produto.java.model.ProdutoModel;
import br.com.kraken.Produto.java.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController (ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid ProdutoModelDTO produtoModelDTO) {
        ProdutoModel produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoModelDTO, produtoModel);
        produtoModel.setDataRecebimento(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.getProdutoRepository().save(produtoModel));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listar () {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @GetMapping ("/{identificador}")
    public ResponseEntity<Object> localizar(@PathVariable (value = "identificador") UUID identificador){
        Optional<ProdutoModel> produtoModelOptional = produtoService.getProdutoRepository().findById(identificador);
        return produtoModelOptional.<ResponseEntity<Object>>map(
                produtoModel -> ResponseEntity.status(HttpStatus.OK).body(produtoModel))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Product!!")
                );
    }

    @DeleteMapping("/{identificador}")
    public ResponseEntity<Object> deletar(@PathVariable (value = "identificador") UUID identificador){
        Optional<ProdutoModel> produtoModelOptional = produtoService.getProdutoRepository().findById(identificador);
        if (!produtoModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Product!!");
        produtoService.getProdutoRepository().delete(produtoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Delete Success!!!");
    }

    @PutMapping("/{identificador}")
    public ResponseEntity<Object> alterar(@PathVariable (value = "identificador") UUID identificador, @RequestBody @Valid ProdutoModelDTO produtoModelDTO){
        Optional<ProdutoModel> produtoModelOptional = produtoService.getProdutoRepository().findById(identificador);
        if (!produtoModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Product!!");
        produtoModelOptional.get().setNome(produtoModelDTO.nome());
        produtoModelOptional.get().setDescricao(produtoModelDTO.descricao());
        produtoModelOptional.get().setTipo(produtoModelDTO.tipo());
        produtoModelOptional.get().setDataRecebimento(produtoModelDTO.dataRecebimento());
        produtoModelOptional.get().setValorUnitario(produtoModelDTO.valorUnitario());
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.getProdutoRepository().save(produtoModelOptional.get()));
    }

}
