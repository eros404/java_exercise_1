import java.nio.file.Paths;
import java.util.*;

public class Predict implements Command{
    @Override
    public String name() {
        return "predict";
    }

    @Override
    public Boolean run(Scanner scanner) {
        System.out.println("Veuillez entrer un chemin de fichier.");
        var input = scanner.nextLine();
        try {
            var content = java.nio.file.Files.readString(Paths.get(input));
            var predictions = getPrediction(getWords(content));
            System.out.println("Veuillez entrer un mot.");
            input = scanner.nextLine();
            if (!predictions.containsKey(input)) {
                System.out.println("Erreur, ce mot ne fait pas partie de la liste fournie.");
            }
            else {
                System.out.println(buildSentence(input, predictions));
            }
        } catch (Exception e) {
            System.out.println("Unreadable file: " + e.getClass().getName() + " " + e.getMessage());
        }
        return true;
    }

    public String[] getWords(String content) {
        return Arrays.stream(content.replaceAll("[^\\w\\s]|\\n", " ")
                .split(" ")).filter(s -> !s.isBlank()).toArray(String[]::new);
    }

    public Map<String, String> getPrediction(String[] content) {
        var result = new HashMap<String, String>();
        for (String word : content) {
            if (!result.containsKey(word)) {
                var nextWord = getNextWord(word, content);
                if (nextWord != null) {
                    result.put(word, nextWord);
                }
            }
        }
        return result;
    }

    public String getNextWord(String word, String[] content) {
        var frequencies = new HashMap<String, Long>();
        var lastWasWord = false;
        for (String w : content) {
            if (lastWasWord) {
                if (frequencies.containsKey(w)) {
                    frequencies.replace(w, frequencies.get(w) + 1);
                } else {
                    frequencies.put(w, 1L);
                }
                lastWasWord = false;
            }
            if (w.equals(word)) {
                lastWasWord = true;
            }
        }
        var optional = frequencies.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey);
        return optional.orElse(null);
    }

    public String buildSentence(String start, Map<String, String> predictions) {
        var sentence = new ArrayList<String>();
        for (int i = 0; i < 20; i++)
        {
            if (predictions.containsKey(start)) {
                sentence.add(predictions.get(start));
                start = predictions.get(start);
            } else {
                break;
            }
        }
        return String.join(" ", sentence);
    }
}
