/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworkdenemeler;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Kaan Kızıldağ
 */
public class Ornekler extends JPanel {

    public float[] x0lar, x1ler;
    public int[] yler;
    int ornekSayisi = 1500, hata = ornekSayisi;
    float perceptronX, perceptronY;
    Random r;
    int[] tahminler;
    int w = 1000, h = 1000;
    BasitPerceptron bp = new BasitPerceptron();

    public Ornekler() {
        x0lar = new float[ornekSayisi];
        x1ler = new float[ornekSayisi];
        yler = new int[ornekSayisi];
        r = new Random();
        perceptronX = bp.kesenX();
        perceptronY = bp.kesenY();

        for (int i = 0; i < ornekSayisi; i++) {
            x0lar[i] = (r.nextFloat() - 0.5f) * 20f; // 10 __ -10 aralığında değer vermesi için
            x1ler[i] = (r.nextFloat() - 0.5f) * 20f;
            if (x0lar[i] < x1ler[i] - 5) {
                yler[i] = 1;
            } else {
                yler[i] = -1;
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.black);
        g.drawLine(0, (int) (h / 2), w, (int) (h / 2));
        g.drawLine((int) (w / 2), 0, (int) (w / 2), h);
        g.drawLine(map(perceptronX), h - map(0), map(0), h - map(perceptronY));
        int err = ornekSayisi;
        for (int i = 0; i < ornekSayisi; i++) {
            
            int x0 = map(x0lar[i]);
            int x1 = h - map(x1ler[i]);
            Color c;
            if (yler[i] == 1) {
                c = Color.green;
            } else {
                c = Color.red;
            }
            g.setColor(c);
            g.fillOval(x0, x1, 20, 20);

            if (tahminler[i] == yler[i]) {
                err--;
                c = Color.white;
            } else {
                c = Color.black;
            }
            g.setColor(c);
            g.fillOval(x0 + 5, x1 + 5, 10, 10);
            
            
        }
        hata = err;
    }

    public int map(float val) {
        return (int) ((val - (-10)) * (h - 0) / (10 - (-10)) + 0);
    }

}
