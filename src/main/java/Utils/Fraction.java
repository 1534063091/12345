package Utils;

public class Fraction {
    private int numerator; // 分子
    private int denominator; // 分母

    // 构造函数：初始化分数并进行化简
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("分母不能为0"); // 检查分母是否为零
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify(); // 化简分数
    }

    // 加法：与另一个分数相加
    public Fraction add(Fraction other) {
        int commonDenominator = this.denominator * other.denominator; // 计算公分母
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator; // 计算新分子
        return new Fraction(newNumerator, commonDenominator); // 返回新分数
    }

    // 减法：与另一个分数相减
    public Fraction subtract(Fraction other) {
        int commonDenominator = this.denominator * other.denominator; // 计算公分母
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator; // 计算新分子
        return new Fraction(newNumerator, commonDenominator); // 返回新分数
    }

    // 乘法：与另一个分数相乘
    public Fraction multiply(Fraction other) {
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator); // 返回新分数
    }

    // 除法：与另一个分数相除
    public Fraction divide(Fraction other) {
        return new Fraction(this.numerator * other.denominator, this.denominator * other.numerator); // 返回新分数
    }

    // 化简分数：通过最大公约数简化分数
    private void simplify() {
        int gcd = gcd(Math.abs(numerator), denominator); // 计算最大公约数
        numerator /= gcd; // 化简分子
        denominator /= gcd; // 化简分母
    }

    // 计算最大公约数
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b; // 更新 b 为 a 除以 b 的余数
            a = temp; // 更新 a 为 b 的原值
        }
        return a; // 返回最大公约数
    }

    // 返回分数的字符串表示
    @Override
    public String toString() {
        if (numerator < denominator) {
            return numerator + "/" + denominator; // 真分数
        } else if (numerator % denominator == 0) {
            return String.valueOf(numerator / denominator); // 整数形式
        } else {
            int wholeNumber = numerator / denominator; // 带分数的整数部分
            int remainder = numerator % denominator; // 带分数的分子部分
            return wholeNumber + "'" + remainder + "/" + denominator; // 带分数形式
        }
    }
}
