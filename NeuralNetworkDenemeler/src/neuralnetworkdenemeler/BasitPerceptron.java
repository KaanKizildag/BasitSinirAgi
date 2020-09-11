/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworkdenemeler;

import java.util.Random;

/**
 *
 * @author Kaan Kızıldağ
 */
public class BasitPerceptron {
    
    public float w0, w1, wb;
    int bias = 1;
    Random r = new Random();
    float ogrenmeOrani = 0.0005f;
    
    public void hazirlan() {
        w0 = (r.nextFloat() - 0.5f) * 2f;
        w1 = (r.nextFloat() - 0.5f) * 2f;
        wb = (r.nextFloat() - 0.5f) * 2f;
    }
    
    public int tahminEt(float x0, float x1) {
        float toplam = wb * bias + x0 * w0 + x1 * w1; // toplam değeri bulunurken de wb değeri katılmalı

        if (toplam < 0) {
            return -1;
        } else {
            return 1;
        }
        
    }
    
    public int[] tahminSetiOlustur(float[] x0lar, float[] x1ler, int ornekSayisi) {
        int[] sonuclar = new int[ornekSayisi];
        for (int i = 0; i < ornekSayisi; i++) {
            sonuclar[i] = tahminEt(x0lar[i], x1ler[i]);
        }
        return sonuclar;
    }
    
    public void egit(float x0, float x1, int o) {
        int t = tahminEt(x0, x1);
        // lineer bir hata hesabi yapmak yerine mean sqrt error kullanarak 
        //hata hesabi yapmak büyük hatalarda büyük sonuçlar verirken küçük değerlerde
        //daha küçük değerler elde etmemizi sağlar
        
        
        float delta = Math.signum(o - t) * 0.5f * (float) Math.pow(o - t, 2); 
        wb += delta * bias * ogrenmeOrani; // wb her eğitim aşamasında eğitilecek.
        w0 += delta * ogrenmeOrani * x0;
        w1 += delta * ogrenmeOrani * x1;
        
    }
    
    public float kesenX() {
        return (-(w1 * 0) - wb) / w0;
    }
    
    public float kesenY() {
        return (-(w0 * 0) - wb) / w1;
    }
    
    public float kesenY(float x) {
        return (-(w0 * x) - wb) / w1;
    }
}
