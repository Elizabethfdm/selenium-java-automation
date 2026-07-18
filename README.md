# 🚀 Selenium Java Automation Framework

<p align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Selenium](https://img.shields.io/badge/Selenium-4.x-43B02A?style=for-the-badge&logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-Framework-red?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker)
![Jenkins](https://img.shields.io/badge/Jenkins-CI-D24939?style=for-the-badge&logo=jenkins)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-CI-2088FF?style=for-the-badge&logo=githubactions)

</p>

<p align="center">

Framework moderno de automação Web utilizando Selenium, Java, TestNG, Docker, Selenium Grid, GitHub Actions e Jenkins.

</p>

---

# 📖 Sobre o projeto

Este projeto foi desenvolvido com o objetivo de demonstrar a construção de um framework de automação de testes seguindo boas práticas utilizadas em ambientes corporativos.

A arquitetura foi construída visando:

- organização do código;
- reutilização de componentes;
- facilidade de manutenção;
- integração contínua (CI);
- execução local e remota;
- escalabilidade.

O projeto utiliza **Page Object Model**, **DriverFactory**, **Docker**, **Selenium Grid**, **GitHub Actions** e **Jenkins** para demonstrar um fluxo completo de automação.

---

# 🏗 Arquitetura

```
                        Selenium Java Framework

                +-------------------------------+
                |          TestNG               |
                +---------------+---------------+
                                |
                        Page Object Model
                                |
                     DriverFactory / BasePage
                                |
                +---------------+---------------+
                |                               |
         Execução Local                 Selenium Grid
                |                               |
                +---------------+---------------+
                                |
                             Docker
                                |
               +----------------+----------------+
               |                                 |
        GitHub Actions                    Jenkins Pipeline
```

---

# 📂 Estrutura do projeto

```
.
├── docker
│   ├── compose
│   ├── images
│   └── jenkins
│
├── docs
│   └── evidencias
│
├── src
│   ├── main
│   └── test
│
├── target
│
├── Jenkinsfile
├── pom.xml
├── README.md
└── .github
```

---

# 🛠 Tecnologias

| Tecnologia | Finalidade |
|------------|------------|
| Java 17 | Linguagem principal |
| Selenium WebDriver | Automação Web |
| TestNG | Framework de testes |
| Maven | Build e gerenciamento de dependências |
| Docker | Conteinerização |
| Selenium Grid | Execução remota |
| Jenkins | Integração Contínua |
| GitHub Actions | Pipeline CI |
| IntelliJ IDEA | IDE utilizada |

---

# ✨ Funcionalidades implementadas

✅ DriverFactory inteligente

✅ Configuração por arquivo Properties

✅ Page Object Model

✅ BasePage reutilizável

✅ Execução Local

✅ Execução Headless

✅ RemoteWebDriver

✅ Selenium Grid

✅ Docker Compose

✅ Jenkins Pipeline

✅ GitHub Actions

✅ Relatórios HTML

✅ TestNG Reports

✅ Maven Surefire Reports

---

# ▶ Como executar

## Execução local

```bash
mvn clean test
```

---

## Execução Headless

No arquivo `config.properties`:

```properties
headless=true
```

---

## Selenium Grid

```bash
docker compose -f docker/compose/docker-compose.yml up -d selenium-hub chrome firefox
```

---

## Jenkins

```bash
docker compose -f docker/jenkins/docker-compose.jenkins.yml up -d --build
```

Acesse:

```
http://localhost:8080
```

---

# 📊 Relatórios

Após a execução são gerados automaticamente:

- HTML Report
- TestNG Report
- Maven Surefire Report
- XML Reports
- Screenshots

---

# 📸 Evidências

## GitHub Actions

![GitHub Actions](docs/evidencias/github-actions-success.png)

---

## Jenkins

![Jenkins Pipeline](docs/evidencias/jenkins-pipeline-success.png)

---

## Selenium Grid

![Selenium Grid](docs/evidencias/selenium-grid.png)

---

## Docker

![Docker](docs/evidencias/docker-build-success.png)

---

## Relatórios

![Relatórios](docs/evidencias/relatorio-testes.png)

---

# 🔄 Fluxo de execução

```
GitHub

↓

GitHub Actions

↓

Jenkins

↓

Docker

↓

Selenium Grid

↓

Chrome / Firefox

↓

TestNG

↓

Relatórios
```

---

# 🚀 Próximos passos

O projeto continuará evoluindo com novas funcionalidades:

- Integração com Azure DevOps Pipelines
- Publicação automática de relatórios
- Execução paralela
- Novos cenários automatizados
- Quality Gates

---

# 👩‍💻 Autora

**Elizabeth França de Moura**

QA Especialista | Automação de Testes | Qualidade de Software

### GitHub

https://github.com/Elizabethfdm

### LinkedIn

> Adicione aqui o link do seu LinkedIn.

---

## ⭐ Se este projeto foi útil para você, deixe uma estrela no repositório!
