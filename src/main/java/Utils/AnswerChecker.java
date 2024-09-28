package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerChecker {

    public void checkAnswers(String exerciseFile, String answerFile, String gradeFile) throws IOException {
        List<String> exercises = readFile(exerciseFile);
        List<String> answers = readFile(answerFile);

        List<Integer> correct = new ArrayList<>();
        List<Integer> wrong = new ArrayList<>();

        for (int i = 0; i < exercises.size(); i++) {
            String exercise = exercises.get(i).trim();
            String answer = answers.get(i).trim();
            String correctAnswer = new ArithmeticGenerator().calculateAnswer(exercise).trim();

            // 检查用户的答案是否为错误提示
            if (answer.startsWith("错误：")) {
                wrong.add(i + 1); // 用户答案为错误提示，直接记录为错误
            } else {
                // 正常答案的比较
                if (answer.equals(correctAnswer)) {
                    correct.add(i + 1);
                } else {
                    wrong.add(i + 1); // 记录为错误
                }
            }
        }

        // 写入成绩文件
        writeGradeFile(gradeFile, correct, wrong);
    }

    private List<String> readFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.replaceAll("^\\d+\\.\\s*", "").trim()); // 去掉编号和空格
            }
        }
        return lines;
    }

    private void writeGradeFile(String fileName, List<Integer> correct, List<Integer> wrong) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(fileName, false))) {
            writer.printf("Correct: %d (%s)%n", correct.size(), correct.toString().replaceAll("[\\[\\]]", ""));
            writer.printf("Wrong: %d (%s)%n", wrong.size(), wrong.toString().replaceAll("[\\[\\]]", ""));
        }
    }
}
