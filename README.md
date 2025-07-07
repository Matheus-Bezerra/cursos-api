# Cursos - API (DDD)💻

<p align="center">
 <a href="#technologies">Tecnologias</a> • 
 <a href="#started">Rodar o projeto</a> • 
 <a href="#routes">API Endpoints</a> •
 <a href="#colab">Colaboradores</a> •
 <a href="#contribute">Contribuição</a>
</p>

<p align="center">
    <b>API REST para cadastro, atualização, listagem e exclusão de cursos. Projeto desenvolvido em Java com Spring Boot seguindo boas práticas de DDD.</b>
</p>

## 💻 Tecnologias

- Java 17
- Spring Boot
- Liquibase
- Maven
- H2 Database (pode ser alterado para outro banco relacional)
- Testes unitários com JUnit & Mockito
- Docker (opcional)

## 🚀 Rodar o projeto

### Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)
- (Opcional) [Docker](https://www.docker.com/)

### Clonando o projeto

```bash
git clone https://github.com/Matheus-Bezerra/cursos-api.git
```

### Rodando localmente

```bash
cd cursos-api
./mvnw spring-boot:run
```
Ou, se preferir usar Docker:
```bash
docker-compose up
```

## 📍 API Endpoints

| Rota                       | Descrição                                 |
|----------------------------|-------------------------------------------|
| `GET /cursos`              | Lista todos os cursos                     |
| `GET /cursos/{id}`         | Busca um curso pelo ID                    |
| `POST /cursos`             | Cria um novo curso                        |
| `PUT /cursos/{id}`         | Atualiza um curso existente               |
| `DELETE /cursos/{id}`      | Remove um curso                           |

**Exemplo de requisição para criar curso:**
```json
{
  "name": "Java",
  "category": "BACKEND",
  "active": true
}
```

**Exemplo de resposta:**
```json
{
  "id": 1,
  "name": "Java",
  "category": "BACKEND",
  "active": true,
  "createdAt": "2025-07-07T12:00:00",
  "updatedAt": "2025-07-07T12:00:00"
}
```

## 🤝 Colaboradores

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/Matheus-Bezerra">
        <img src="https://avatars.githubusercontent.com/u/78742961?s=400&u=c88bb8c0c4246cfed678cc8c5a2bbf043a20ed0e&v=4" width="100px;" alt="Matheus Bezerra"/><br>
        <sub>
          <b>Matheus Bezerra</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

## 📫 Contribuição

1. `git clone https://github.com/Matheus-Bezerra/cursos-api.git`
2. `git checkout -b feature/NOME_DA_FEATURE`
3. Siga o padrão de commits
4. Abra um Pull Request explicando a feature ou correção realizada. Se possível, anexe prints das alterações visuais e aguarde a revisão!

### Documentações úteis

- [📝 Como criar um Pull Request](https://docs.github.com/pt/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request)
- [💾 Padrão de commits (iuricode)](https://github.com/iuricode/padroes-de-commits)
