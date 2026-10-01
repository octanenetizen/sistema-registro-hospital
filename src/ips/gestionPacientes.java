package ips;

import java.awt.Dimension;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CargarFoto extends JFrame {
    private final JFileChooser fileChooser;

    public CargarFoto() {
        super("Cargar foto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(700, 500));
        setLocationRelativeTo(null);

        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes JPG, PNG y JPEG", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);
        fileChooser.setApproveButtonText("Abrir");
        fileChooser.setDialogTitle("Seleccionar imagen del doctor");
        this.add(fileChooser);
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }
}
