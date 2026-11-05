
package ma.emsi.sabri.tp2websabri.llm;

import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;

import jakarta.enterprise.context.Dependent;
import java.io.Serializable;

@Dependent
public class LlmClient implements Serializable {

    private static final long serialVersionUID = 1L;

    private String systemRole;
    private Assistant assistant;
    private ChatMemory chatMemory;

    public LlmClient() {

        // Récupération de la clé API Gemini
        String apiKey = System.getenv("GEMINI_KEY");

        // Création du modèle Gemini
        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .build();

        // Création de la mémoire conversationnelle
        this.chatMemory = MessageWindowChatMemory.withMaxMessages(20);

        // Création du service IA
        this.assistant = AiServices.builder(Assistant.class)
                .chatModel(model)
                .chatMemory(chatMemory)
                .build();
    }
}
