package com.jim.itimerserver.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class CaptchaUtil {

    // 验证码字符集
    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    // 默认验证码长度
    private static final int DEFAULT_CODE_LENGTH = 4;
    // 默认图片宽度
    private static final int DEFAULT_IMAGE_WIDTH = 100;
    // 默认图片高度
    private static final int DEFAULT_IMAGE_HEIGHT = 40;
    // 干扰线数量
    private static final int DEFAULT_LINE_COUNT = 20;

    /**
     * 生成验证码图片
     * @param code 验证码文本
     * @return 验证码图片字节数组
     */
    public static byte[] generateCaptchaImage(String code) throws IOException {
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(
                DEFAULT_IMAGE_WIDTH,
                DEFAULT_IMAGE_HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        // 获取Graphics2D
        Graphics2D g = image.createGraphics();

        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);

        // 设置字体
        g.setFont(new Font("Arial", Font.BOLD, 30));

        // 绘制验证码
        Random random = new Random();
        for (int i = 0; i < code.length(); i++) {
            // 随机颜色
            g.setColor(new Color(
                    random.nextInt(200),
                    random.nextInt(200),
                    random.nextInt(200)));
            // 绘制字符
            g.drawString(String.valueOf(code.charAt(i)),
                    20 * i + 10,
                    30);
        }

        // 绘制干扰线
        for (int i = 0; i < DEFAULT_LINE_COUNT; i++) {
            int x1 = random.nextInt(DEFAULT_IMAGE_WIDTH);
            int y1 = random.nextInt(DEFAULT_IMAGE_HEIGHT);
            int x2 = random.nextInt(DEFAULT_IMAGE_WIDTH);
            int y2 = random.nextInt(DEFAULT_IMAGE_HEIGHT);
            g.setColor(new Color(
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255)));
            g.drawLine(x1, y1, x2, y2);
        }

        // 释放资源
        g.dispose();

        // 转换为字节数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", baos);
        return baos.toByteArray();
    }

    /**
     * 生成随机验证码
     * @param length 验证码长度
     * @return 验证码文本
     */
    public static String generateRandomCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return sb.toString();
    }
}
