package unlar.edu.ar.paradigma.objetos;

public class Empleado {

    private Integer codigo;
    private String apellido_nombre;

    public Empleado(Integer codigo, String apellido_nombre) {
        this.codigo = codigo;
        this.apellido_nombre = apellido_nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getApellido_nombre() {
        return apellido_nombre;
    }

    public void setApellido_nombre(String apellido_nombre) {
        this.apellido_nombre = apellido_nombre;
    }

    @Override
    public String toString() {
        return "Empleado [codigo=" + codigo + ", apellido_nombre=" + apellido_nombre + "]";
    }

}
