# Utilisation de l'intelligence artificielle — TP2 Web

## Outil utilisé

ChatGPT (OpenAI).

## 1. Adaptation du backing bean du TP0

**Problème rencontré :** Adapter le backing bean `Bb.java` du TP0 pour remplacer le générateur de réponses simulées par un service communiquant avec Gemini.

**Raison de l'utilisation de l'IA :** Obtenir de l'aide pour conserver la structure du code fourni par le professeur tout en réalisant les modifications demandées dans le TP2.

**Prompt utilisé :**

« on passe mtn au bb voici le code du tp 0 [...] mis on doit modifier le code de la méthode envoyer() pour faire appel à la classe LlmClient décrite ci-dessous pour poser une question au LLM et recevoir une réponse. »

**Réponse de l'IA (extrait pertinent) :**

L'IA a proposé de remplacer l'injection de `GenerateurReponse` par celle de `LlmClient`, puis d'appeler `setSystemRole(roleSysteme)` au début de la conversation et `chat(question)` pour obtenir la réponse du modèle.

**Ce que j'ai appris (à reformuler personnellement) :**

J'ai compris que le backing bean gère les interactions avec l'interface JSF, tandis que la classe `LlmClient` encapsule la communication avec le modèle de langage. L'injection CDI permet de relier ces deux classes.

## 2. Création du service IA avec LangChain4j

**Problème rencontré :** Comprendre comment créer un service IA et configurer la mémoire conversationnelle avec LangChain4j.

**Raison de l'utilisation de l'IA :** Comprendre l'enchaînement des étapes et adapter les instructions du TP au projet Maven.

**Prompt utilisé :**

« Variables d'instance de LlmClient [...] Constructeur de LlmClient [...] est ce que c est ca ou avant »

**Réponse de l'IA (extrait pertinent) :**

L'IA a proposé un constructeur récupérant la clé API avec `System.getenv("GEMINI_KEY")`, créant le modèle `GoogleGenAiChatModel`, initialisant `MessageWindowChatMemory.withMaxMessages(20)` et construisant un service IA avec `AiServices.builder(Assistant.class)`.

**Ce que j'ai appris (à reformuler personnellement) :**

J'ai compris que LangChain4j peut générer une implémentation de l'interface `Assistant` et gérer automatiquement les messages de la conversation grâce à `ChatMemory`.

## 3. Prise en compte du rôle système

**Problème rencontré :** Comprendre comment transmettre le rôle sélectionné dans la page JSF au modèle Gemini.

**Raison de l'utilisation de l'IA :** Vérifier que le rôle système était correctement ajouté à la mémoire conversationnelle.

**Prompt utilisé :**

« Méthodes de LlmClient [...] est ce que ce st ca 6.4 aussi n oublie pas les commits »

**Réponse de l'IA (extrait pertinent) :**

L'IA a proposé d'utiliser `chatMemory.clear()` pour vider la mémoire et `chatMemory.add(SystemMessage.from(systemRole))` pour enregistrer le rôle système avant les échanges.

**Ce que j'ai appris (à reformuler personnellement) :**

J'ai compris que stocker le rôle dans une variable Java ne suffit pas : il doit être transmis au modèle sous forme de `SystemMessage`. Le test du rôle Traducteur m'a permis de vérifier ce fonctionnement.

## 4. Configuration Maven et GitHub

**Problème rencontré :** Configurer les dépendances de l'application Web et relier le projet IntelliJ à un nouveau dépôt GitHub.

**Raison de l'utilisation de l'IA :** Obtenir des instructions adaptées à mon projet et éviter les erreurs de configuration.

**Prompts utilisés :**

« [Contenu du pom.xml du projet] »

« mtn comment je fais le commit je pense je dois lier avant le projet avce le rpo.. »

**Réponse de l'IA (extrait pertinent) :**

L'IA a proposé les dépendances Jakarta EE, PrimeFaces, LangChain4j et Gemini, ainsi que les commandes Git pour configurer le dépôt distant, créer les commits et effectuer les push.

**Ce que j'ai appris (à reformuler personnellement) :**

J'ai compris la différence entre un commit local et un push vers GitHub, ainsi que le rôle des dépendances Maven dans une application Jakarta EE.