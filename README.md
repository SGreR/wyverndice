# Wyvern Dice

Este projeto é uma API para gerenciar a encomenda de conjuntos de dados de dados de RPG personalizados. Ele fornece endpoints para criar, ler, atualizar e deletar conjuntos de dados. O projeto é construído usando Spring Boot.

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- Java 17 ou superior
- Maven 3.8.1 ou superior

## Configuração do Projeto

1. Clone o repositório para sua máquina local:
2. Compile o projeto usando Maven:

## Endpoints disponíveis

- GET /diceset -> Obtém todos os conjuntos de dados disponíveis

Parâmetros Opcionais:

`name`: Filtra os conjuntos de dados pelo nome

GET /diceset/{id} -> Localiza um cojunto específico de dados por id

POST /diceset -> Cria um novo conjunto de dados

Exemplo de body (JSON) a ser enviado:
```
{
  "type": "premade",
  "name": "Test Set",
  "colors": {
    "Main": "Clear",
    "Secondary": "Purple"
  },
  "numberColors": "Silver",
  "style": "SMOKE",
  "numberOfDice": "TEN"
}
```

PUT /diceset/{id} -> Atualiza as informações de um cojunto de dados por id

Exemplo de body (JSON) a ser enviado:
```
{
  "type": "premade",
  "id": 1,
  "name": "Smoky Set",
  "colors": {
    "Main": "Clear",
    "Secondary": "Green"
  },
  "numberColors": "Gold",
  "style": "SMOKE",
  "numberOfDice": "TEN"
}
```

DELETE /diceset/{id} -> Remove um conjunto de dados por id
