/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworkdenemeler;

import javax.swing.JFrame;

/**
 *
 * @author Kaan Kızıldağ
 */
public class NeuralNetworkDenemeler extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BasitPerceptron bp = new BasitPerceptron();
        bp.hazirlan();
        Ornekler ornekler = new Ornekler();
        JFrame frame = frameHazila(ornekler);

        ornekler.tahminler = bp.tahminSetiOlustur(ornekler.x0lar, ornekler.x1ler, ornekler.ornekSayisi);
        int i = 0;
        while(ornekler.hata  > 10){
            try {
                
                int index = i % ornekler.ornekSayisi;
                bp.egit(ornekler.x0lar[index], ornekler.x1ler[index], ornekler.yler[index]);
                ornekler.tahminler = bp.tahminSetiOlustur(ornekler.x0lar, ornekler.x1ler, ornekler.ornekSayisi);
                ornekler.perceptronX = bp.kesenX();
                ornekler.perceptronY = bp.kesenY();
                i++;
                frame.repaint();
                Thread.sleep(2);
                System.out.println(ornekler.hata);
            } catch (InterruptedException ex) {

            }
        }

    }

    public static JFrame frameHazila(Ornekler ornekler) {
        JFrame frame = new JFrame();
        frame.add(ornekler);
        frame.setSize(ornekler.w, ornekler.h);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }
}
