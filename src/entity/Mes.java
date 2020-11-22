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
@Table( name = "mes" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Mes.findAll", query = "SELECT m FROM Mes m" )
    , @NamedQuery( name = "Mes.findByPkMes", query = "SELECT m FROM Mes m WHERE m.pkMes = :pkMes" )
    , @NamedQuery( name = "Mes.findByDesignacao", query = "SELECT m FROM Mes m WHERE m.designacao = :designacao" )
} )
public class Mes implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_mes" )
    private Integer pkMes;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkMes" )
    private List<Propina> propinaList;

    public Mes()
    {
    }

    public Mes( Integer pkMes )
    {
        this.pkMes = pkMes;
    }

    public Integer getPkMes()
    {
        return pkMes;
    }

    public void setPkMes( Integer pkMes )
    {
        this.pkMes = pkMes;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao( String designacao )
    {
        this.designacao = designacao;
    }

    @XmlTransient
    public List<Propina> getPropinaList()
    {
        return propinaList;
    }

    public void setPropinaList( List<Propina> propinaList )
    {
        this.propinaList = propinaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkMes != null ? pkMes.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Mes ) )
        {
            return false;
        }
        Mes other = ( Mes ) object;
        if ( ( this.pkMes == null && other.pkMes != null ) || ( this.pkMes != null && !this.pkMes.equals( other.pkMes ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Mes[ pkMes=" + pkMes + " ]";
    }
    
}
