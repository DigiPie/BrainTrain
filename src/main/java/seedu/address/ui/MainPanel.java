package seedu.address.ui;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.quiz.QuizMode;
import seedu.address.model.quiz.QuizUiDisplayFormatter;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class MainPanel extends UiPart<Region> {
    public static final String MESSAGE_QUESTION = "%1$s: %2$s\n";
    public static final String MESSAGE_QUESTION_ANSWER = "%1$s: %2$s\n%3$s: %4$s\n";

    private static final String FXML = "MainPanel.fxml";
    private static final String boldStyle = "-fx-font-weight: bold;";

    @FXML
    private TextFlow mainPanel;

    private QuizUiDisplayFormatter formatter;

    public MainPanel() {
        super(FXML);
    }

    public void setFeedbackToUser(QuizUiDisplayFormatter formatter) {
        mainPanel.getChildren().clear();

        if (formatter != null) {
            // contains only question
            this.formatter = formatter;

            QuizMode mode = formatter.getMode();
            Text questionAnswer = new Text();

            switch (mode) {
            case PREVIEW:
                questionAnswer.setText(String.format(MESSAGE_QUESTION_ANSWER, formatter.getQuestionHeader(),
                    formatter.getQuestion(), formatter.getAnswerHeader(), formatter.getAnswer()));

                if (!formatter.isWrongTwice()) {
                    Text text = new Text("Press Enter to go to the next question");
                    mainPanel.getChildren().addAll(questionAnswer, text);
                    break;
                }

                mainPanel.getChildren().add(questionAnswer);
                setAnswerPrompt();
                break;
            case REVIEW:
                questionAnswer.setText(String.format(MESSAGE_QUESTION, formatter.getQuestionHeader(),
                    formatter.getQuestion()));
                mainPanel.getChildren().add(questionAnswer);
                setAnswerPrompt();
                break;
            default:
                break;
            }
        } else {
            mainPanel.getChildren().add(new Text(""));

        }
    }

    private void setAnswerPrompt() {
        Text text1 = new Text("Type the ");
        Text answer = new Text(formatter.getAnswerHeader() + " ");
        Text text2 = new Text("for the ");
        Text question = new Text(formatter.getQuestionHeader() + " ");
        Text text3 = new Text("above and press Enter");
        answer.setStyle(boldStyle);
        List<Text> texts = Arrays.asList(text1, answer, text2, question, text3);
        mainPanel.getChildren().addAll(texts);
    }
}
