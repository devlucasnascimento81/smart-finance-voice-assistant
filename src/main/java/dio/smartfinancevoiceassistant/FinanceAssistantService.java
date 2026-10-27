package dio.smartfinancevoiceassistant;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

@Service
public class FinanceAssistantService {

    public record Transaction(String description, BigDecimal amount, String type, String category) {}

    private final List<Transaction> transactions = new ArrayList<>();
    private BigDecimal balance = BigDecimal.ZERO;

    public FinanceAssistantService() {
        // Dados iniciais de exemplo
        registerIncome("Salário", new BigDecimal("5000.00"), "Renda");
    }

    public synchronized String processVoiceOrTextMessage(String input) {
        if (input == null || input.isBlank()) {
            return "Por favor, diga ou envie um comando financeiro.";
        }

        String lower = input.toLowerCase();

        // 1. Consulta de saldo
        if (lower.contains("saldo") || lower.contains("quanto tenho") || lower.contains("dinheiro")) {
            return "Seu saldo atual é de R$ " + balance + ".";
        }

        // 2. Consulta de extrato / gastos
        if (lower.contains("extrato") || lower.contains("gastos") || lower.contains("relatório")) {
            if (transactions.isEmpty()) return "Nenhuma movimentação registrada.";
            StringBuilder sb = new StringBuilder("Extrato atual:\n");
            for (Transaction t : transactions) {
                sb.append(String.format("- %s: R$ %s (%s)\n", t.description(), t.amount(), t.type()));
            }
            sb.append("Saldo final: R$ ").append(balance);
            return sb.toString();
        }

        // 3. Registro de Despesa (Ex: "gastei 50 no mercado", "comprei pizza por 60")
        if (lower.contains("gastei") || lower.contains("comprei") || lower.contains("paguei") || lower.contains("despesa")) {
            BigDecimal amount = extractAmount(lower);
            registerExpense("Gasto via voz", amount, "Diversos");
            return "Despesa de R$ " + amount + " registrada com sucesso! Seu novo saldo é R$ " + balance + ".";
        }

        // 4. Registro de Receita (Ex: "recebi 200 de pix", "ganhei 500")
        if (lower.contains("recebi") || lower.contains("ganhei") || lower.contains("depósito") || lower.contains("pix")) {
            BigDecimal amount = extractAmount(lower);
            registerIncome("Entrada via voz", amount, "Diversos");
            return "Receita de R$ " + amount + " adicionada com sucesso! Seu novo saldo é R$ " + balance + ".";
        }

        // Resposta padrão caso seja uma dúvida geral
        return "Olá! Sou seu assistente financeiro. Você pode dizer coisas como: 'Qual é o meu saldo?', 'Gastei 45 no almoço' ou 'Ver extrato'.";
    }

    public void registerExpense(String desc, BigDecimal amount, String category) {
        transactions.add(new Transaction(desc, amount, "DESPESA", category));
        balance = balance.subtract(amount);
    }

    public void registerIncome(String desc, BigDecimal amount, String category) {
        transactions.add(new Transaction(desc, amount, "RECEITA", category));
        balance = balance.add(amount);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    private BigDecimal extractAmount(String text) {
        try {
            String clean = text.replaceAll("[^0-9,\\.]", " ").trim();
            for (String token : clean.split("\\s+")) {
                if (!token.isBlank()) {
                    return new BigDecimal(token.replace(",", "."));
                }
            }
        } catch (Exception ignored) {}
        return new BigDecimal("50.00"); // valor padrão caso não identifique o número exato
    }
}