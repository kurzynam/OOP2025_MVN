package org.example;

import images.ImageProcessor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        ImageProcessor ip = new ImageProcessor();
        ip.loadImage("cat.jpg");
        ip.increaseBrightnessMultiThread(-70);
        ip.saveImage("brightened_cat_multithread.jpg");
    }
}
