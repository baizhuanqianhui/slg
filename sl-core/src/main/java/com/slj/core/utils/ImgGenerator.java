package com.slj.core.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author tg
 * @date 2014-2-20
 * @version 1.0
 */
public class ImgGenerator {
	protected static Log log = LogFactory.getLog(com.slj.core.utils.ImgGenerator.class);
	
    public ImgGenerator()
    {
    }

    public static Color getRandColor(int fc, int bc)
    {
        Random random = new Random();
        if(fc > 255)
            fc = 255;
        if(bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public static void createPic(OutputStream os, String token)
        throws IOException
    {
        try
        {
            int width = 15 * token.length() + 8;
            int height = 25;
            BufferedImage image = new BufferedImage(width, height, 1);
            Graphics g = image.getGraphics();
            Random random = new Random();
            g.setColor(new Color(217, 217, 255));
            g.fillRect(0, 0, width, height);
            g.setFont(new Font("Comic San MS", 0, 18));
            g.setColor(getRandColor(160, 200));
            for(int i = 0; i < 10; i++)
            {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                int xl = random.nextInt(12);
                int yl = random.nextInt(12);
                g.drawOval(x, y, x + xl, y + yl);
            }

            for(int i = 0; i < token.length(); i++)
            {
                char key = token.charAt(i);
                String rand = String.valueOf(key);
                g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
                g.drawString(rand, 15 * i + 6, 16);
            }

            g.dispose();
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(image);
        }
        catch(Exception e)
        {
        	log.error("The token's pic generate error.", e);
        }
    }

    static void invokeCaptchaService(Object service, String token, OutputStream out)
        throws IOException
    {
        Method mtd = null;
        try
        {
            mtd = service.getClass().getMethod("write", new Class[] {
                java.lang.String.class, java.io.OutputStream.class
            });
        }
        catch(Exception e)
        {
            if(e instanceof RuntimeException)
            {
                throw (RuntimeException)e;
            } else
            {
                IllegalStateException t = new IllegalStateException();
                t.initCause(e);
                throw t;
            }
        }
        try
        {
            mtd.invoke(service, new Object[] {
                token, out
            });
        }
        catch(IllegalAccessException e)
        {
            IllegalStateException t = new IllegalStateException();
            t.initCause(e);
            throw t;
        }
        catch(InvocationTargetException e)
        {
            if(e.getTargetException() instanceof IOException)
                throw (IOException)e.getTargetException();
            if(e.getTargetException() instanceof RuntimeException)
            {
                throw (RuntimeException)e.getTargetException();
            } else
            {
                IllegalStateException t = new IllegalStateException();
                t.initCause(e);
                throw t;
            }
        }
    }
}
