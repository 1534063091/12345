import Utils.AnswerChecker;
import Utils.ArithmeticGenerator;
import Utils.FileHandler;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // 默认题目数量和数值范围
        int numQuestions = 10;
        int maxRange = 10;

        String exerciseFile = null; // 用于存储题目文件名
        String answerFile = null; // 用于存储答案文件名

        // 解析命令行参数
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-n")) {
                if (i + 1 < args.length) {
                    // 解析题目数量
                    numQuestions = Integer.parseInt(args[++i]);
                } else {
                    System.err.println("错误：-n 选项需要一个值。");
                    return;
                }
            } else if (args[i].equals("-r")) {
                if (i + 1 < args.length) {
                    // 解析数值范围
                    maxRange = Integer.parseInt(args[++i]);
                } else {
                    System.err.println("错误：-r 选项需要一个值。");
                    return;
                }
            } else if (args[i].equals("-e")) {
                if (i + 1 < args.length) {
                    // 获取题目文件名
                    exerciseFile = args[++i];
                } else {
                    System.err.println("错误：-e 选项需要一个值。");
                    return;
                }
            } else if (args[i].equals("-a")) {
                if (i + 1 < args.length) {
                    // 获取答案文件名
                    answerFile = args[++i];
                } else {
                    System.err.println("错误：-a 选项需要一个值。");
                    return;
                }
            } else {
                System.err.println("错误：未知选项 " + args[i]);
                return;
            }
        }

        // 如果提供了题目和答案文件，执行检查
        if (exerciseFile != null && answerFile != null) {
            AnswerChecker checker = new AnswerChecker(); // 创建答案检查器实例
            try {
                // 检查答案并将结果写入Grade.txt文件
                checker.checkAnswers(exerciseFile, answerFile, "Grade.txt");
                System.out.println("检查完成，结果已写入 Grade.txt");
            } catch (IOException e) {
                System.err.println("文件处理错误：" + e.getMessage());
            }
            return; // 结束程序
        }

        // 打印解析后的参数
        System.out.println("题目数量: " + numQuestions);
        System.out.println("数值范围: " + maxRange);

        // 创建生成器实例
        ArithmeticGenerator generator = new ArithmeticGenerator();
        StringBuilder exercises = new StringBuilder(); // 用于存储题目
        StringBuilder answers = new StringBuilder(); // 用于存储答案

        // 生成题目
        for (int i = 0; i < numQuestions; i++) {
            String question = generator.generateQuestion(maxRange); // 生成题目
            String answer = generator.calculateAnswer(question); // 计算答案

            // 将题目和答案添加到对应的字符串构建器中
            exercises.append(i + 1).append(". ").append(question).append("\n");
            answers.append(i + 1).append(". ").append(answer).append("\n");
        }

        // 将题目写入文件
        FileHandler fileHandler = new FileHandler();
        try {
            // 写入题目和答案文件
            fileHandler.writeToFile(new File("Exercises.txt"), exercises.toString());
            fileHandler.writeToFile(new File("Answers.txt"), answers.toString());
            System.out.println("题目和答案已成功写入文件。");
        } catch (IOException e) {
            System.err.println("文件写入错误：" + e.getMessage());
        }
    }
}
