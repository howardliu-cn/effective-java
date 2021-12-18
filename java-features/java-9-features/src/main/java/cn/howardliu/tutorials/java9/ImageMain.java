package cn.howardliu.tutorials.java9;

import java.awt.Image;
import java.awt.image.BaseMultiResolutionImage;
import java.awt.image.MultiResolutionImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2021/12/18 11:42
 */
public class ImageMain {
    public static void main(String[] args) throws IOException {
        final List<Image> images = List.of(
                ImageIO.read(new URL("https://static.howardliu.cn/about/kanshanshuo_2.png")),
                ImageIO.read(new URL("https://static.howardliu.cn/about/hellokanshan.png")),
                ImageIO.read(new URL("https://static.howardliu.cn/about/evil%20coder.jpg"))
        );

        // 读取所有图片
        final MultiResolutionImage multiResolutionImage = new BaseMultiResolutionImage(images.toArray(new Image[0]));

        // 获取图片的所有分辨率
        final List<Image> variants = multiResolutionImage.getResolutionVariants();

        System.out.println("Total number of images: " + variants.size());

        for (Image img : variants) {
            System.out.println(img);
        }

        // 根据不同尺寸获取对应的图像分辨率
        Image variant1 = multiResolutionImage.getResolutionVariant(100, 100);
        System.out.printf("\nImage for destination[%d,%d]: [%d,%d]",
                100, 100, variant1.getWidth(null), variant1.getHeight(null));

        Image variant2 = multiResolutionImage.getResolutionVariant(200, 200);
        System.out.printf("\nImage for destination[%d,%d]: [%d,%d]",
                200, 200, variant2.getWidth(null), variant2.getHeight(null));

        Image variant3 = multiResolutionImage.getResolutionVariant(300, 300);
        System.out.printf("\nImage for destination[%d,%d]: [%d,%d]",
                300, 300, variant3.getWidth(null), variant3.getHeight(null));

        Image variant4 = multiResolutionImage.getResolutionVariant(400, 400);
        System.out.printf("\nImage for destination[%d,%d]: [%d,%d]",
                400, 400, variant4.getWidth(null), variant4.getHeight(null));

        Image variant5 = multiResolutionImage.getResolutionVariant(500, 500);
        System.out.printf("\nImage for destination[%d,%d]: [%d,%d]",
                500, 500, variant5.getWidth(null), variant5.getHeight(null));
    }
}
