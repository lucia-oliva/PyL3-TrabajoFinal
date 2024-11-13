package unlar.edu.ar.paradigma.objetos;

public class TipoAccidente {

    private Integer codigo;
    private String tipo;
    
    
    public TipoAccidente() {
        
    }

    public TipoAccidente(Integer codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "TipoAccidente [codigo=" + codigo + ", tipo=" + tipo + "]";
    }

}
