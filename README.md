# produto_kraken
A API do Sistema Kraken responsável pelo gerenciamento do Produto

## serviço tem Com função:
Cria, alterar, buscar e deletar os produtos cadastrado no banco de dado.

## ferramentas usado:
foi criado em Spring Boot e conteirizado com o Docker

## o que depende?
A sua utilização depende do cadastro no serviço cliente_kraken como Vendedor que dará a permissão ao uso

## como é feito a permissão?
Permissão vem de um token JWT gerado da API cliente_kraken que dará permissão ao serviço do produto_kraken
