package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        carregarPropriedades();
    }

    private ConfigReader() {
    }

    private static void carregarPropriedades() {
        try (InputStream arquivo = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (arquivo == null) {
                throw new RuntimeException(
                        "Arquivo config.properties não encontrado em src/main/resources."
                );
            }

            properties.load(arquivo);

        } catch (IOException erro) {
            throw new RuntimeException(
                    "Erro ao carregar o arquivo config.properties.",
                    erro
            );
        }
    }

    public static String getProperty(String chave) {
        String valor = properties.getProperty(chave);

        if (valor == null || valor.isBlank()) {
            throw new RuntimeException(
                    "A propriedade '" + chave + "' não foi encontrada ou está vazia."
            );
        }

        return valor;
    }

    public static int getIntProperty(String chave) {
        return Integer.parseInt(getProperty(chave));
    }
}