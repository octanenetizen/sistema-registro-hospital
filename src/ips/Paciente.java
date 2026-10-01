package ips;

import java.util.Date;
import javax.swing.ImageIcon;

public class Doctor {
    private int id;
    private String nombres;
    private String apellidos;
    private char sexo;
    private Date fechaGrado;
    private String especialidad;
    private ImageIcon foto;

    public Doctor(int id, String nombres, String apellidos, char sexo, Date fechaGrado, String especialidad, ImageIcon foto) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaGrado = fechaGrado;
        this.especialidad = especialidad;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getFechaGrado() {
        return fechaGrado;
    }

    public void setFechaGrado(Date fechaGrado) {
        this.fechaGrado = fechaGrado;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public ImageIcon getFoto() {
        return foto;
    }

    public void setFoto(ImageIcon foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Doctor{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", sexo=" + sexo + ", especialidad=" + especialidad + '}';
    }
}
