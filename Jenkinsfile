pipeline {

    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    environment {
        SELENIUM_REMOTE_URL = 'http://host.docker.internal:4444'
        COMPOSE_FILE = 'docker/compose/docker-compose.yml'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Baixando o código-fonte...'
                checkout scm
            }
        }

        stage('Iniciar Selenium Grid') {
            steps {
                echo 'Iniciando Selenium Hub, Chrome e Firefox...'

                sh '''
                    docker compose -f ${COMPOSE_FILE} up -d selenium-hub chrome firefox
                '''
            }
        }

        stage('Aguardar Selenium Grid') {
            steps {
                echo 'Aguardando o Selenium Grid ficar disponível...'

                sh '''
                    for tentativa in $(seq 1 20); do
                        if curl --silent --fail http://host.docker.internal:4444/status > /dev/null; then
                            echo "Selenium Grid disponível."
                            exit 0
                        fi

                        echo "Tentativa ${tentativa}/20..."
                        sleep 5
                    done

                    echo "O Selenium Grid não ficou disponível no tempo esperado."
                    exit 1
                '''
            }
        }

        stage('Executar Testes') {
            steps {
                echo 'Executando testes automatizados...'

                sh '''
                    mvn test
                '''
            }
        }

        stage('Publicar Resultados') {
            steps {
                echo 'Publicando resultados do TestNG/Surefire...'

                junit allowEmptyResults: true,
                      testResults: 'target/surefire-reports/*.xml'
            }
        }

        stage('Arquivar Evidências') {
            steps {
                echo 'Arquivando relatórios e screenshots...'

                archiveArtifacts(
                    artifacts: 'target/reports/**/*, target/screenshots/**/*, target/surefire-reports/**/*',
                    allowEmptyArchive: true,
                    fingerprint: true
                )
            }
        }
    }

    post {

        always {
            echo 'Encerrando o Selenium Grid...'

            sh '''
                docker compose -f ${COMPOSE_FILE} down --remove-orphans || true
            '''
        }

        success {
            echo 'Pipeline executada com sucesso.'
        }

        failure {
            echo 'Pipeline finalizada com falhas. Consulte os logs e as evidências.'
        }

        cleanup {
            cleanWs()
        }
    }
}