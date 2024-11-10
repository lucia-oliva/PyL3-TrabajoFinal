package unlar.edu.ar.paradigma.objetos;

public class Motivo {

    private Integer codigo;
    private String motivo;

    public Motivo(Integer codigo, String motivo) {
        this.codigo = codigo;
        this.motivo = motivo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Empleado [codigo=" + codigo + ", motivo=" + motivo + "]";
    }

}
