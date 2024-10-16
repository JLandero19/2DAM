package org.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"matricula", "marca", "modelo"})
public class Coche {
    private String matricula;
    private String marca;
    private String modelo;

    // XML/JAXB necesita un constructor por defecto vacío
    public Coche() {}

    public Coche(String matricula, String marca, String modelo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
    }

    // Se puede anotar en los Getter o en los Setter
    // No es necesario la anotación @XmlElement
    @XmlAttribute
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @XmlElement
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @XmlElement
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
