package Utils;

import java.util.Random;

public class ArithmeticGenerator {
    private final Random random = new Random(); // 随机数生成器

    // 生成一个算式，可能包含分数或整数
    public String generateQuestion(int maxRange) {
        boolean isFraction = random.nextBoolean(); // 随机决定是否生成分数
        String question;

        if (isFraction) {
            // 生成分数的随机数值
            int numerator1 = random.nextInt(maxRange - 1) + 1; // 第一个分子的随机值
            int denominator1 = random.nextInt(maxRange - 1) + 2; // 第一个分母的随机值
            int numerator2 = random.nextInt(maxRange - 1) + 1; // 第二个分子的随机值
            int denominator2 = random.nextInt(maxRange - 1) + 2; // 第二个分母的随机值

            String operator = getRandomOperator(); // 随机获取运算符
            question = numerator1 + "/" + denominator1 + " " + operator + " " + numerator2 + "/" + denominator2; // 生成分数算式
        } else {
            // 生成整数的随机数值
            int num1 = random.nextInt(maxRange); // 第一个整数的随机值
            int num2 = random.nextInt(maxRange); // 第二个整数的随机值
            String operator = getRandomOperator(); // 随机获取运算符
            question = num1 + " " + operator + " " + num2; // 生成整数算式
        }

        return question + " = ?"; // 返回算式并添加等号
    }

    // 计算算式的答案
    public String calculateAnswer(String question) {
        String[] parts = question.split(" "); // 将算式拆分为各部分
        String operator = parts[1]; // 获取运算符

        if (parts[0].contains("/")) {
            // 处理分数运算
            String[] fraction1 = parts[0].split("/"); // 第一个分数
            String[] fraction2 = parts[2].split("/"); // 第二个分数

            int numerator1 = Integer.parseInt(fraction1[0]); // 第一个分子的值
            int denominator1 = Integer.parseInt(fraction1[1]); // 第一个分母的值
            int numerator2 = Integer.parseInt(fraction2[0]); // 第二个分子的值
            int denominator2 = Integer.parseInt(fraction2[1]); // 第二个分母的值
            int resultNumerator; // 结果分子
            int resultDenominator; // 结果分母

            // 根据运算符执行相应的分数运算
            switch (operator) {
                case "+":
                    resultNumerator = numerator1 * denominator2 + numerator2 * denominator1;
                    resultDenominator = denominator1 * denominator2;
                    break;
                case "-":
                    resultNumerator = numerator1 * denominator2 - numerator2 * denominator1;
                    resultDenominator = denominator1 * denominator2;
                    break;
                case "*":
                    resultNumerator = numerator1 * numerator2;
                    resultDenominator = denominator1 * denominator2;
                    break;
                case "/":
                    resultNumerator = numerator1 * denominator2;
                    resultDenominator = numerator2 * denominator1;
                    break;
                default:
                    return "错误：未知运算符"; // 处理未知运算符的情况
            }

            return simplifyFraction(resultNumerator, resultDenominator); // 简化并返回结果分数
        } else {
            // 处理整数运算
            int num1 = Integer.parseInt(parts[0]); // 第一个整数
            int num2 = Integer.parseInt(parts[2]); // 第二个整数
            int result; // 结果

            // 根据运算符执行相应的整数运算
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    if (result < 0) {
                        return "错误：结果为负数"; // 处理负数结果的情况
                    }
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        return "错误：除以零"; // 处理除以零的情况
                    }
                    result = num1 / num2;
                    break;
                default:
                    return "错误：未知运算符"; // 处理未知运算符的情况
            }
            return String.valueOf(result); // 返回结果
        }
    }

    // 随机获取运算符
    private String getRandomOperator() {
        String[] operators = {"+", "-", "*", "/"}; // 可用运算符数组
        return operators[random.nextInt(operators.length)]; // 返回随机运算符
    }

    // 简化分数
    private String simplifyFraction(int numerator, int denominator) {
        if (denominator == 0) {
            return "错误：除以零"; // 处理分母为零的情况
        }

        // 处理负数分数
        if (numerator < 0 && denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        } else if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        // 求最大公约数并简化分数
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;

        // 返回简化后的分数或整数
        if (denominator == 1) {
            return String.valueOf(numerator); // 只返回分子
        }

        return numerator + "/" + denominator; // 返回简化后的分数
    }

    // 计算最大公约数
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a; // 返回最大公约数
    }
}
