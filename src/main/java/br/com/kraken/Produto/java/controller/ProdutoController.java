package br.com.kraken.Produto.java.controller;

import br.com.kraken.Produto.java.dto.AcessoDTO;
import br.com.kraken.Produto.java.dto.ProdutoModelDTO;
import br.com.kraken.Produto.java.model.AcessoModel;
import br.com.kraken.Produto.java.model.ProdutoModel;
import br.com.kraken.Produto.java.service.AcessoService;
import br.com.kraken.Produto.java.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final AcessoService acessoService;

    public ProdutoController (ProdutoService produtoService, AcessoService acessoService) {
        this.produtoService = produtoService;
        this.acessoService = acessoService;
    }

    @PostMapping("/auth/registry")
    public ResponseEntity<Object> clienteEstoque (@RequestBody @Valid AcessoDTO acessoDTO) {
        var acessoModel = new AcessoModel();
        BeanUtils.copyProperties(acessoDTO, acessoModel);
        acessoModel.setDataCadastro(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.CREATED).body(acessoService.getRepository().save(acessoModel));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> cadastrar(@RequestParam(value = "nomeProduto") String nomeProduto,
                                            @RequestParam(value = "descricao") String descricao,
                                            @RequestParam(value = "categoriaId") UUID categoriaId,
                                            @RequestParam(value = "valorUnitario") float valorUnitario,
                                            @RequestParam(value = "estoqueId") UUID estoqueId,
                                            @RequestParam(value = "clienteId") UUID clienteId,
                                            @RequestParam(value = "quantidadeProduto") int quantidadeProduto,
                                            @RequestPart(value = "image")MultipartFile image) {
        ProdutoModel produtoModel = new ProdutoModel();
        try {
            if(image.isEmpty())
                return ResponseEntity.badRequest().body("Not Found image");
            String imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());
            produtoModel.setImage(imageBase64);
            produtoModel.setNomeProduto(nomeProduto);
            produtoModel.setQuantidadeProduto(quantidadeProduto);
            produtoModel.setCategoriaId(categoriaId);
            produtoModel.setEstoqueId(estoqueId);
            produtoModel.setClienteId(clienteId);
            produtoModel.setDescricao(descricao);
            produtoModel.setValorUnitario(valorUnitario);
            produtoModel.setDataModificacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error Register Product");
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.getProdutoRepository().save(produtoModel));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ProdutoModel>> listar () {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @GetMapping ("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Object> localizar(@PathVariable (value = "id") UUID id){
        Optional<ProdutoModel> produtoModelOptional = produtoService.getProdutoRepository().findById(id);
        return produtoModelOptional.<ResponseEntity<Object>>map(
                produtoModel -> ResponseEntity.status(HttpStatus.OK).body(produtoModel))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Product!!")
                );
    }

    @DeleteMapping("/{identificador}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deletar(@PathVariable (value = "identificador") UUID identificador){
        Optional<ProdutoModel> produtoModelOptional = produtoService.getProdutoRepository().findById(identificador);
        if (!produtoModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Product!!");
        produtoService.getProdutoRepository().delete(produtoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Delete Success!!!");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Object> alterar(@PathVariable (value = "id") UUID id, @RequestBody @Valid ProdutoModelDTO produtoModelDTO){
        Optional<ProdutoModel> produtoModelOptional = produtoService.getProdutoRepository().findById(id);
        if (!produtoModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Product!!");
        produtoModelOptional.get().setNomeProduto(produtoModelDTO.nomeProduto());
        produtoModelOptional.get().setDescricao(produtoModelDTO.descricao());
        produtoModelOptional.get().setQuantidadeProduto(produtoModelDTO.quantidadeProduto());
        produtoModelOptional.get().setDataModificacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        produtoModelOptional.get().setEstoqueId(produtoModelDTO.estoqueId());
        produtoModelOptional.get().setClienteId(produtoModelDTO.clienteId());
        produtoModelOptional.get().setCategoriaId(produtoModelDTO.categoriaId());
        produtoModelOptional.get().setValorUnitario(produtoModelDTO.valorUnitario());
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.getProdutoRepository().save(produtoModelOptional.get()));
    }

}
