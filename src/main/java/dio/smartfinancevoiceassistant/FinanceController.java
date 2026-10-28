package dio.smartfinancevoiceassistant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/assistant")
public class FinanceController {

    private final FinanceAssistantService assistantService;

    public FinanceController(FinanceAssistantService assistantService) {
        this.assistantService = assistantService;
    }

    // 1. Endpoint para enviar comando por texto
    @PostMapping("/chat")
    public ResponseEntity<Map<String, Object>> chat(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        String response = assistantService.processVoiceOrTextMessage(message);

        return ResponseEntity.ok(Map.of(
                "input", message,
                "response", response,
                "currentBalance", assistantService.getBalance()
        ));
    }

    // 2. Endpoint simulando recepção de áudio/voz (requisito do desafio)
    @PostMapping("/voice")
    public ResponseEntity<Map<String, Object>> uploadVoice(@RequestParam("audio") MultipartFile file) {
        // Simulação do Speech-To-Text (Whisper/STT):
        // Converte o arquivo recebido para comando textual
        String simulatedTranscript = "Gastei 75 no combustível";
        String response = assistantService.processVoiceOrTextMessage(simulatedTranscript);

        return ResponseEntity.ok(Map.of(
                "fileName", file.getOriginalFilename(),
                "transcription", simulatedTranscript,
                "response", response,
                "currentBalance", assistantService.getBalance()
        ));
    }

    // 3. Endpoint de consulta direta de saldo e transações
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        return ResponseEntity.ok(Map.of(
                "balance", assistantService.getBalance(),
                "transactions", assistantService.getTransactions()
        ));
    }

    // Endpoint da melhoria: Resumo de despesas agrupadas por categoria
    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getSummary() {
        return ResponseEntity.ok(Map.of(
                "totalBalance", assistantService.getBalance(),
                "expensesByCategory", assistantService.getExpensesByCategory()
        ));
    }
}