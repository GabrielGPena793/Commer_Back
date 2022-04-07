# Commerce_Back

Requisitos Back-end:

Você deverá fazer o back-end de nosso e-commerce em Java com o Framework Spring no modelo MVC.

O banco de dados deverá ser o MySQL. 

Modelo de dados: O banco de dados terá duas entidades principais, products e categories. Onde cada produto tem apenas uma categoria e cada categoria pode ter vários ou nenhum produto.  Segue o modelo para o banco de dados:
	
![Untitled](https://user-images.githubusercontent.com/86542760/162210640-407ed3d0-2733-41c6-904f-55ddb61e348b.png)


API - O projeto deverá disponibilizar uma API com quatro end-points: 

Método POST - 'https://ctdcommerce.com/products': este end-point deverá efetuar o cadastro de novos produtos. Para isso será necessário o envio das seguintes informações via corpo da requisição. Por exemplo:

    {
      “title”: “Bolsa”,
      “price”: 350.50,
      “category”: 1,
      “description”: “Produto novo.”,
      “image”: ”https://a-static.mlcdn.com.br/618x463/bolsa-feminina-dhaffy-bege-divisorias-alca-de-mao-e-transversal-dhaffy-bolsas/dhaffybolsas/5703861364/1a5bdaa8a82b22de3dc2468583981810.jpg”
    }
---

Método GET - 'https://ctdcommerce.com/products': 
- este end-point deverá disponibilizar os dados de todos os produtos cadastrados em um JSON com o formato que segue:
---
Método GET - https://ctdcommerce.com/products/1: 
- este end-point deverá disponibilizar os dados de um produto específico em um JSON  com o formato que segue:
---
Método GET - https://ctdcommerce.com/products/categories: 
- este end-point deverá disponibilizar uma lista de categorias cadastradas em um JSON  com o formato que segue:
---
Método PUT- 'https://ctdcommerce.com/products': 
- este end-point também será utilizado para atualizar produtos. Para isso, será necessário o envio das informações via corpo da requisição, alterando as informações desejadas e mantendo as anteriores. Por exemplo:

      {
        "id": 1,
        “title”: “Bolsa”,
        “price”: 400.00,
        “category”: 1,
        “description”: “Produto novo.”,
        “image”: ”https://a-static.mlcdn.com.br/618x463/bolsa-feminina-dhaffy-bege-divisorias-alca-de-mao-e-transversal-dhaffy-bolsas/dhaffybolsas/5703861364/1a5bdaa8a82b22de3dc2468583981810.jpg”
      }
---
Método DELETE - 'https://ctdcommerce.com/products/1': 
- este end-point será responsável por excluir um recurso da API Rest. Para isso, basta enviar por parâmetro, o id do produto.
