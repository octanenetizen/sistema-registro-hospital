package ips;

import java.util.ArrayList;
import java.util.List;

public class DoctorManager {
    private static final List<Doctor> DOCTORES = new ArrayList<>();

    public static void guardar(Doctor doctor) {
        for (int i = 0; i < DOCTORES.size(); i++) {
            if (DOCTORES.get(i).getId() == doctor.getId()) {
                DOCTORES.set(i, doctor);
                return;
            }
        }
        DOCTORES.add(doctor);
    }

    public static Doctor buscarPorId(int id) {
        for (Doctor doctor : DOCTORES) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }

    public static List<Doctor> listarTodos() {
        return new ArrayList<>(DOCTORES);
    }

    public static void limpiar() {
        DOCTORES.clear();
    }
}
