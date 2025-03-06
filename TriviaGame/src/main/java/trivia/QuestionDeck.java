package trivia;

import java.util.LinkedList;

public class QuestionDeck {
    private final LinkedList<Question> questions = new LinkedList<>();

    public void addQuestion(Question question) {
        questions.addLast(question);
    }

    public Question drawQuestion() {
        return questions.removeFirst();
    }

    public boolean isEmpty() {
        return questions.isEmpty();
    }
}