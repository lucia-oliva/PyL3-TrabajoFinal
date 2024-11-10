package unlar.edu.ar.paradigma.objetos;

public class Empleado {

    private Integer legajo;
    private String apellido_nombre;

    public Empleado(Integer legajo, String apellido_nombre) {
        this.legajo = legajo;
        this.apellido_nombre = apellido_nombre;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    public String getApellido_nombre() {
        return apellido_nombre;
    }

    public void setApellido_nombre(String apellido_nombre) {
        this.apellido_nombre = apellido_nombre;
    }

    @Override
    public String toString() {
        return "Empleado [legajo=" + legajo + ", apellido_nombre=" + apellido_nombre + "]";
    }

}
