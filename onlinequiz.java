import java.util.*;

// Class representing a user
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

// Class representing a quiz
class Quiz {
    private String title;
    private List<Question> questions;

    public Quiz(String title, List<Question> questions) {
        this.title = title;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

// Class representing a question
class Question {
    private String text;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String text, List<String> options, int correctOptionIndex) {
        this.text = text;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

// Class representing a quiz service
class QuizService {
    private List<Quiz> quizzes;

    public QuizService() {
        quizzes = new ArrayList<>();
    }

    public void createQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public Quiz getQuizByTitle(String title) {
        for (Quiz quiz : quizzes) {
            if (quiz.getTitle().equals(title)) {
                return quiz;
            }
        }
        return null;
    }
}

// Class representing a quiz taking service
class QuizTakingService {
    private Map<String, Integer> scores;

    public QuizTakingService() {
        scores = new HashMap<>();
    }

    public void submitAnswer(String username, Quiz quiz, List<Integer> selectedOptions) {
        int score = calculateScore(quiz, selectedOptions);
        scores.put(username, scores.getOrDefault(username, 0) + score);
    }

    private int calculateScore(Quiz quiz, List<Integer> selectedOptions) {
        int score = 0;
        List<Question> questions = quiz.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getCorrectOptionIndex() == selectedOptions.get(i)) {
                score++;
            }
        }
        return score;
    }

    public int getScore(String username) {
        return scores.getOrDefault(username, 0);
    }
}



public class onlinequiz {
    public static void main(String[] args) {
        // Sample quiz creation
        QuizService quizService = new QuizService();
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is 2 + 2?", Arrays.asList("1", "2", "3", "4"), 3));
        questions.add(new Question("What is the capital of France?", Arrays.asList("London", "Berlin", "Paris", "Madrid"), 2));
        Quiz quiz = new Quiz("General Knowledge Quiz", questions);
        quizService.createQuiz(quiz);

        // Sample user authentication
        User user = new User("john_doe", "password");

        // Sample quiz taking
        QuizTakingService quizTakingService = new QuizTakingService();
        List<Integer> selectedOptions = Arrays.asList(3, 2); // Answers to the questions
        quizTakingService.submitAnswer(user.getUsername(), quiz, selectedOptions);

        // Sample score retrieval
        int score = quizTakingService.getScore(user.getUsername());
        System.out.println("User's score: " + score);
    }
}
