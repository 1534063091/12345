package Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    // 将内容写入指定的文件，覆盖之前的内容
    public void writeToFile(File file, String content) throws IOException {
        try (FileWriter writer = new FileWriter(file, false)) { // 使用 FileWriter 创建文件写入器，覆盖模式
            writer.write(content); // 将内容写入文件
        }
    }

    // 从指定文件读取内容并返回
    public String readFromFile(File file) throws IOException {
        StringBuilder contentBuilder = new StringBuilder(); // 用于构建文件内容的字符串
        try (Scanner scanner = new Scanner(file)) { // 创建扫描器读取文件
            while (scanner.hasNextLine()) { // 逐行读取文件
                contentBuilder.append(scanner.nextLine()).append(System.lineSeparator()); // 添加到内容构建器
            }
        }
        return contentBuilder.toString().trim(); // 返回文件内容并去掉最后的换行符
    }
}
