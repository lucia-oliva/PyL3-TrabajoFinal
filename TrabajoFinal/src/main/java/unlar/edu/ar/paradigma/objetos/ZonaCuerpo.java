package unlar.edu.ar.paradigma.objetos;

public class ZonaCuerpo {

    private Integer id_zona;
    private Integer codigo;
    private Integer izqder;

    public ZonaCuerpo(Integer id_zona, Integer codigo, Integer izqder) {
        this.id_zona = id_zona;
        this.codigo = codigo;
        this.izqder = izqder;
    }

    public Integer getId_zona() {
        return id_zona;
    }

    public void setId_zona(Integer id_zona) {
        this.id_zona = id_zona;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getIzqder() {
        return izqder;
    }

    public void setIzqder(Integer izqder) {
        this.izqder = izqder;
    }

    @Override
    public String toString() {
        return "ZonaCuerpo [id_zona=" + id_zona + ", codigo=" + codigo + ", izqder=" + izqder + "]";
    }

}
