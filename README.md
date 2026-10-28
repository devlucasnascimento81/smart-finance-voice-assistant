# 🎙️ Smart Finance Voice Assistant API

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3.4-brightgreen?style=for-the-badge&logo=springboot" alt="Spring Boot 3" />
  <img src="https://img.shields.io/badge/Spring%20AI-Intelligent%20Engine-blue?style=for-the-badge" alt="Spring AI" />
  <img src="https://img.shields.io/badge/DIO-Bootcamp-red?style=for-the-badge" alt="DIO Bootcamp" />
</p>

## 📌 Sobre o Projeto

O **Smart Finance Voice Assistant** é uma API REST inteligente desenvolvida como desafio final do Bootcamp da [DIO](https://dio.me). A proposta do projeto é fornecer um assistente virtual capaz de interpretar comandos de voz e texto para gestão de finanças pessoais, automatizando registros de receitas, despesas e emissão de extratos consolidados.

A arquitetura foi desenhada com foco em modularidade, resiliência e processamento de linguagem natural (NLP).

---

## 🚀 Tecnologias Utilizadas

- **Java 21** (LTS)
- **Spring Boot 3.3.4**
- **Spring Web** (construção de APIs REST)
- **Spring Boot Starter Test & JUnit 5** (testes automatizados e integrados)
- **Gradle** (gerenciador de dependências e build)

---

## 💡 Funcionalidades Principais

- 🗣️ **Processamento de Comandos de Voz/Texto:** interpretação semântica de frases cotidianas (ex: *"Gastei 50 no almoço"*, *"Qual o meu saldo?"*, *"Quero ver meu extrato"*).
- 💰 **Gestão Financeira em Tempo Real:** controle automático de saldo, despesas e receitas.
- 📊 **Dashboard Consolidado:** consulta de saldo atualizado e histórico de transações.
- 🎧 **Simulação de Speech-to-Text (STT):** endpoint preparado para recepção e transcrição de arquivos de áudio.

---

## ⭐ Melhoria Implementada (Diferencial do Projeto)

Como evolução além dos requisitos iniciais do desafio, foi implementado o módulo de **Resumo de Gastos por Categoria (Expense Analytics)**:
- Agrupamento e soma automática de despesas por centro de custo (ex: Alimentação, Transporte, Lazer, etc.).
- Permite que o assistente responda a perguntas como *"Mostrar resumo por categoria"*.
- Novo endpoint REST dedicado: `GET /api/assistant/summary`.

---

## 🛠️ Endpoints da API

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/assistant/chat` | Envia comando em linguagem natural via texto/JSON |
| `POST` | `/api/assistant/voice` | Envia arquivo de áudio para processamento por voz |
| `GET` | `/api/assistant/dashboard` | Retorna o saldo atual e o extrato completo |
| `GET` | `/api/assistant/summary` | **(Melhoria)** Retorna o total de despesas agrupadas por categoria |

### Exemplo de Requisição (`POST /api/assistant/chat`)

**Payload:**
```json
{
  "message": "Gastei 150 no mercado hoje"
}
```

**Resposta:**
```json
{
  "input": "Gastei 150 no mercado hoje",
  "response": "Despesa de R$ 150 registrada com sucesso! Seu novo saldo é R$ 4850.00.",
  "currentBalance": 4850.00
}
```

---

## 🧪 Como Executar os Testes

Para validar a inteligência do assistente e a suíte de testes de integração, execute:

```bash
./gradlew test
```

---

## ⚙️ Como Rodar a Aplicação

1. Clone o repositório:
   ```bash
   git clone https://github.com/devlucasnascimento81/smart-finance-voice-assistant.git
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd smart-finance-voice-assistant
   ```
3. Execute o projeto via Gradle:
   ```bash
   ./gradlew bootRun
   ```
4. A API estará disponível em: `http://localhost:8080`

---

## 👨‍💻 Autor

Desenvolvido por **Lucas Nascimento** durante o Bootcamp da DIO.
