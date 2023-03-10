import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Descargas extends JFrame implements ActionListener {

    private JProgressBar progressBar1, progressBar2, progressBar3;
    private JButton btnDescarga1;
    public Descargas() {
        super("Descargas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Configuración de los controles ProgressBar
        progressBar1 = new JProgressBar(0, 50);
        progressBar1.setStringPainted(true);
        progressBar2 = new JProgressBar(0, 50);
        progressBar2.setStringPainted(true);
        progressBar3 = new JProgressBar(0, 50);
        progressBar3.setStringPainted(true);
        // Configuración de los botones de descarga
        btnDescarga1 = new JButton("Descargar Archivos");
        btnDescarga1.addActionListener(this);

        // Paneles
        JPanel panelProgress = new JPanel(new GridLayout(1, 3));
        panelProgress.add(progressBar1);
        panelProgress.add(progressBar2);
        panelProgress.add(progressBar3);
        JPanel panelButtons = new JPanel(new GridLayout(1, 3));
        panelButtons.add(btnDescarga1);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(panelProgress, BorderLayout.NORTH);
        panel.add(panelButtons, BorderLayout.SOUTH);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDescarga1) {
            Descarga descarga1 = new Descarga(progressBar1);
            descarga1.setPriority(Thread.MAX_PRIORITY);
            descarga1.start();
            Descarga descarga2 = new Descarga(progressBar2);
            descarga2.setPriority(Thread.NORM_PRIORITY);
            descarga2.start();
            Descarga descarga3 = new Descarga(progressBar3);
            descarga3.setPriority(Thread.MIN_PRIORITY);
            descarga3.start();
        }
    }

    private class Descarga extends Thread {
        private JProgressBar progressBar;
        private Random random;
        public Descarga(JProgressBar progressBar) {
            this.progressBar = progressBar;
            this.random = new Random();
        }

        public void run() {
            int progreso = 0;
            while (progreso < 50) {
                progreso += random.nextInt(5);
                progressBar.setValue(progreso);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            progressBar.setString("Descarga completa");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Descargas();
            }
        });
    }
}