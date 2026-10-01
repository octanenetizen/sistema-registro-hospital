package ips;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class gestionPacientes extends JFrame {
    private JTextField txtId, txtNombres, txtApellidos, txtTelefono, txtDireccion, txtFechaNac;
    private JRadioButton rbMasculino, rbFemenino;
    private JLabel lblFoto;
    private ImageIcon fotoActual;

    public gestionPacientes() {
        setTitle("Gestión de Pacientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel("CLÍNICA SAN FELIPE - PACIENTES");
        title.setFont(new Font("Tahoma", Font.PLAIN, 30));
        title.setHorizontalAlignment(JLabel.CENTER);
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField(12);
        txtNombres = new JTextField(18);
        txtApellidos = new JTextField(18);
        txtTelefono = new JTextField(18);
        txtDireccion = new JTextField(18);
        txtFechaNac = new JTextField(12);

        rbMasculino = new JRadioButton("Masculino");
        rbFemenino = new JRadioButton("Femenino");
        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(rbMasculino);
        grupoSexo.add(rbFemenino);

        lblFoto = new JLabel();
        fotoActual = crearFotoPorDefecto(150, 150);
        lblFoto.setIcon(fotoActual);
        lblFoto.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        int col = 0;
        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("Identificación:"), gbc);
        gbc.gridx = 1; form.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; form.add(new JLabel("Nombres:"), gbc);
        gbc.gridx = 1; form.add(txtNombres, gbc);

        gbc.gridx = 0; gbc.gridy = 2; form.add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 1; form.add(txtApellidos, gbc);

        gbc.gridx = 0; gbc.gridy = 3; form.add(new JLabel("Fecha nacimiento:"), gbc);
        gbc.gridx = 1; form.add(txtFechaNac, gbc);

        gbc.gridx = 0; gbc.gridy = 4; form.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1; form.add(txtTelefono, gbc);

        gbc.gridx = 0; gbc.gridy = 5; form.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1; form.add(txtDireccion, gbc);

        gbc.gridx = 0; gbc.gridy = 6; form.add(new JLabel("Sexo:"), gbc);
        JPanel pnSexo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnSexo.setBackground(Color.WHITE);
        pnSexo.add(rbMasculino);
        pnSexo.add(rbFemenino);
        gbc.gridx = 1; form.add(pnSexo, gbc);

        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 7; gbc.fill = GridBagConstraints.NONE;
        form.add(lblFoto, gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        buttons.setBackground(Color.WHITE);
        JButton grabar = new JButton("Grabar");
        JButton consultar = new JButton("Consultar");
        JButton listar = new JButton("Listar");
        JButton limpiar = new JButton("Limpiar");
        JButton salir = new JButton("Salir");

        grabar.addActionListener(e -> guardarPaciente());
        consultar.addActionListener(e -> consultarPaciente());
        listar.addActionListener(e -> listarPacientes());
        limpiar.addActionListener(e -> limpiarCampos());
        salir.addActionListener(e -> dispose());

        buttons.add(grabar);
        buttons.add(consultar);
        buttons.add(listar);
        buttons.add(limpiar);
        buttons.add(salir);

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    private void guardarPaciente() {
        String idText = txtId.getText().trim();
        if (idText.isEmpty() || txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar los campos obligatorios.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            String nombres = txtNombres.getText().trim();
            String apellidos = txtApellidos.getText().trim();
            char sexo = rbMasculino.isSelected() ? 'M' : (rbFemenino.isSelected() ? 'F' : ' ');
            Date fecha = parseFecha(txtFechaNac.getText());
            String telefono = txtTelefono.getText().trim();
            String direccion = txtDireccion.getText().trim();

            Paciente paciente = new Paciente(id, nombres, apellidos, sexo, fecha, telefono, direccion, fotoActual);
            PacienteManager.guardar(paciente);
            JOptionPane.showMessageDialog(this, "Paciente guardado correctamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La identificación debe ser numérica.");
        }
    }

    private void consultarPaciente() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            Paciente paciente = PacienteManager.buscarPorId(id);
            if (paciente == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el paciente.");
                return;
            }
            txtNombres.setText(paciente.getNombres());
            txtApellidos.setText(paciente.getApellidos());
            txtTelefono.setText(paciente.getTelefono());
            txtDireccion.setText(paciente.getDireccion());
            txtFechaNac.setText(formatearFecha(paciente.getFechaNacimiento()));
            if (paciente.getSexo() == 'M') rbMasculino.setSelected(true); else rbFemenino.setSelected(true);
            if (paciente.getFoto() != null) {
                lblFoto.setIcon(paciente.getFoto());
                fotoActual = paciente.getFoto();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese una identificación válida.");
        }
    }

    private void listarPacientes() {
        if (PacienteManager.listarTodos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay pacientes registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Paciente p : PacienteManager.listarTodos()) {
            sb.append("ID: ").append(p.getId())
              .append(" | ").append(p.getNombres()).append(" ").append(p.getApellidos())
              .append(" | Sexo: ").append(p.getSexo())
              .append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Pacientes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtFechaNac.setText("");
        rbMasculino.setSelected(false);
        rbFemenino.setSelected(false);
        fotoActual = crearFotoPorDefecto(150, 150);
        lblFoto.setIcon(fotoActual);
    }

    private static Date parseFecha(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return new Date();
        }
        try {
            return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(valor);
        } catch (ParseException e) {
            return new Date();
        }
    }

    private static String formatearFecha(Date fecha) {
        if (fecha == null) {
            return "";
        }
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(fecha);
    }

    private ImageIcon crearFotoPorDefecto(int width, int height) {
        java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g2d = image.createGraphics();
        g2d.setColor(new Color(76, 167, 230));
        g2d.fillOval(0, 0, width, height);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(width / 4, height / 8, width / 2, height / 2);
        g2d.fillOval(width / 6, (height * 3) / 5, width * 2 / 3, height / 5);

        g2d.setColor(new Color(30, 80, 140));
        g2d.fillRect(width / 2 - 8, 0, 16, height / 2);
        g2d.fillRect(width / 2 - 28, height / 4 - 8, 56, 16);

        g2d.dispose();
        return new ImageIcon(image);
    }
}
