package unlar.edu.ar.paradigma.objetos;

public class ParteCuerpo {

    private Integer codigo;
    private String parte;
    
    public ParteCuerpo() {
        
    }

    public ParteCuerpo(Integer codigo, String parte) {
        this.codigo = codigo;
        this.parte = parte;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getParte() {
        return parte;
    }

    public void setParte(String parte) {
        this.parte = parte;
    }

    @Override
    public String toString() {
        return "ParteCuerpo [codigo=" + codigo + ", parte=" + parte + "]";
    }

}
