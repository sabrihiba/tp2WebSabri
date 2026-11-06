
package ma.emsi.sabri.tp2websabri.llm;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.service.AiServices;

import jakarta.enterprise.context.Dependent;

import java.io.Serializable;

@Dependent
public class LlmClient implements Serializable {

    private static final long serialVersionUID = 1L;

    // Rôle système choisi par l'utilisateur
    private String systemRole;

    // Service IA défini par l'interface Assistant
    private Assistant assistant;

    // Mémoire de la conversation
    private ChatMemory chatMemory;

    /**
     * Constructeur de LlmClient.
     * Initialise le modèle Gemini, la mémoire et le service IA.
     */
    public LlmClient() {

        // Récupération de la clé API Gemini
        String apiKey = System.getenv("GEMINI_KEY");

        // Création du modèle Gemini
        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-3.8-flash")
                .build();

        // Création de la mémoire conversationnelle
        this.chatMemory = MessageWindowChatMemory.withMaxMessages(20);

        // Création du service IA avec LangChain4j
        this.assistant = AiServices.builder(Assistant.class)
                .chatModel(model)
                .chatMemory(chatMemory)
                .build();
    }

    /**
     * Définit le rôle système du LLM.
     * Vide la mémoire avant d'ajouter le nouveau rôle.
     *
     * @param systemRole rôle système choisi par l'utilisateur
     */
    public void setSystemRole(String systemRole) {

        this.systemRole = systemRole;

        // Effacer l'ancien historique de conversation
        this.chatMemory.clear();

        // Ajouter le rôle système à la mémoire
        this.chatMemory.add(SystemMessage.from(systemRole));
    }

    /**
     * Envoie une question au LLM et retourne sa réponse.
     *
     * @param prompt question envoyée au LLM
     * @return réponse du LLM
     */
    public String chat(String prompt) {

        return this.assistant.chat(prompt);
    }
}
