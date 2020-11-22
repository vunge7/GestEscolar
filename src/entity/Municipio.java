/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mac
 */
@Entity
@Table( name = "municipio" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Municipio.findAll", query = "SELECT m FROM Municipio m" )
    , @NamedQuery( name = "Municipio.findByPkmunicipio", query = "SELECT m FROM Municipio m WHERE m.pkmunicipio = :pkmunicipio" )
    , @NamedQuery( name = "Municipio.findByDesignacao", query = "SELECT m FROM Municipio m WHERE m.designacao = :designacao" )
} )
public class Municipio implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pkmunicipio" )
    private Integer pkmunicipio;
    @Column( name = "designacao" )
    private String designacao;
    @JoinColumn( name = "fk_provincia", referencedColumnName = "pk_provincia" )
    @ManyToOne( optional = false )
    private Provincia fkProvincia;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkMunicipio" )
    private List<Estudante> estudanteList;

    public Municipio()
    {
    }

    public Municipio( Integer pkmunicipio )
    {
        this.pkmunicipio = pkmunicipio;
    }

    public Integer getPkmunicipio()
    {
        return pkmunicipio;
    }

    public void setPkmunicipio( Integer pkmunicipio )
    {
        this.pkmunicipio = pkmunicipio;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao( String designacao )
    {
        this.designacao = designacao;
    }

    public Provincia getFkProvincia()
    {
        return fkProvincia;
    }

    public void setFkProvincia( Provincia fkProvincia )
    {
        this.fkProvincia = fkProvincia;
    }

    @XmlTransient
    public List<Estudante> getEstudanteList()
    {
        return estudanteList;
    }

    public void setEstudanteList( List<Estudante> estudanteList )
    {
        this.estudanteList = estudanteList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkmunicipio != null ? pkmunicipio.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Municipio ) )
        {
            return false;
        }
        Municipio other = ( Municipio ) object;
        if ( ( this.pkmunicipio == null && other.pkmunicipio != null ) || ( this.pkmunicipio != null && !this.pkmunicipio.equals( other.pkmunicipio ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Municipio[ pkmunicipio=" + pkmunicipio + " ]";
    }
    
}
