package dio.smartfinancevoiceassistant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GeminiAIChatModelIT {

    @Autowired
    private FinanceAssistantService assistantService;

    @Test
    void should_processFinancialVoiceCommandsSuccessfully() {
        // 1. Testa consulta de saldo inicial
        String saldoResponse = assistantService.processVoiceOrTextMessage("Qual é o meu saldo?");
        System.out.println("Comando 1: " + saldoResponse);
        assertTrue(saldoResponse.contains("5000"));

        // 2. Testa comando de voz para registrar despesa
        String gastoResponse = assistantService.processVoiceOrTextMessage("Gastei 150 no mercado hoje");
        System.out.println("Comando 2: " + gastoResponse);
        assertEquals(new BigDecimal("4850.00"), assistantService.getBalance());

        // 3. Testa comando de extrato
        String extratoResponse = assistantService.processVoiceOrTextMessage("Quero ver meu extrato");
        System.out.println("\n--- RELATÓRIO DO ASSISTENTE ---\n" + extratoResponse);

        // 4. Testa a melhoria de resumo por categoria
        String categoriaResponse = assistantService.processVoiceOrTextMessage("Mostrar resumo por categoria");
        System.out.println("Comando 4: " + categoriaResponse);
    }
}