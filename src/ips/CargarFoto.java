package ips;

import java.util.ArrayList;
import java.util.List;

public class PacienteManager {
    private static final List<Paciente> PACIENTES = new ArrayList<>();

    public static void guardar(Paciente paciente) {
        for (int i = 0; i < PACIENTES.size(); i++) {
            if (PACIENTES.get(i).getId() == paciente.getId()) {
                PACIENTES.set(i, paciente);
                return;
            }
        }
        PACIENTES.add(paciente);
    }

    public static Paciente buscarPorId(int id) {
        for (Paciente paciente : PACIENTES) {
            if (paciente.getId() == id) {
                return paciente;
            }
        }
        return null;
    }

    public static List<Paciente> listarTodos() {
        return new ArrayList<>(PACIENTES);
    }

    public static void limpiar() {
        PACIENTES.clear();
    }
}
