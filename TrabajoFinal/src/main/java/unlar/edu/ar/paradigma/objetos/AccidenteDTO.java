package unlar.edu.ar.paradigma.objetos;

import java.sql.Date;

public class AccidenteDTO {

    private Integer numero;
    private Date fecha_del_accidente;
    private String ubicacion;
    private Integer legajo;
    private Integer codigo_motivo;
    private Integer codigo_tipo_accidente;
    private Integer izqder;
    private Integer codigo;

    public AccidenteDTO(Integer numero, Date fecha_del_accidente, String ubicacion, Integer legajo,
            Integer codigo_motivo, Integer codigo_tipo_accidente, Integer izqder, Integer codigo) {
        this.numero = numero;
        this.fecha_del_accidente = fecha_del_accidente;
        this.ubicacion = ubicacion;
        this.legajo = legajo;
        this.codigo_motivo = codigo_motivo;
        this.codigo_tipo_accidente = codigo_tipo_accidente;
        this.izqder = izqder;
        this.codigo = codigo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFecha_del_accidente() {
        return fecha_del_accidente;
    }

    public void setFecha_del_accidente(Date fecha_del_accidente) {
        this.fecha_del_accidente = fecha_del_accidente;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    public Integer getCodigo_motivo() {
        return codigo_motivo;
    }

    public void setCodigo_motivo(Integer codigo_motivo) {
        this.codigo_motivo = codigo_motivo;
    }

    public Integer getCodigo_tipo_accidente() {
        return codigo_tipo_accidente;
    }

    public void setCodigo_tipo_accidente(Integer codigo_tipo_accidente) {
        this.codigo_tipo_accidente = codigo_tipo_accidente;
    }

    public Integer getIzqder() {
        return izqder;
    }

    public void setIzqder(Integer izqder) {
        this.izqder = izqder;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "AccidenteDTO [numero=" + numero + ", fecha_del_accidente=" + fecha_del_accidente + ", ubicacion="
                + ubicacion + ", legajo=" + legajo + ", codigo_motivo=" + codigo_motivo + ", codigo_tipo_accidente="
                + codigo_tipo_accidente + ", izqder=" + izqder + ", codigo=" + codigo + "]";
    }

}
