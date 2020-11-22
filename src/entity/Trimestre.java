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
@Table( name = "trimestre" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Trimestre.findAll", query = "SELECT t FROM Trimestre t" )
    , @NamedQuery( name = "Trimestre.findByPkSemestre", query = "SELECT t FROM Trimestre t WHERE t.pkSemestre = :pkSemestre" )
    , @NamedQuery( name = "Trimestre.findByDesignacao", query = "SELECT t FROM Trimestre t WHERE t.designacao = :designacao" )
} )
public class Trimestre implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_semestre" )
    private Integer pkSemestre;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkTrimestre" )
    private List<Nota> notaList;

    public Trimestre()
    {
    }

    public Trimestre( Integer pkSemestre )
    {
        this.pkSemestre = pkSemestre;
    }

    public Integer getPkSemestre()
    {
        return pkSemestre;
    }

    public void setPkSemestre( Integer pkSemestre )
    {
        this.pkSemestre = pkSemestre;
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
    public List<Nota> getNotaList()
    {
        return notaList;
    }

    public void setNotaList( List<Nota> notaList )
    {
        this.notaList = notaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkSemestre != null ? pkSemestre.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Trimestre ) )
        {
            return false;
        }
        Trimestre other = ( Trimestre ) object;
        if ( ( this.pkSemestre == null && other.pkSemestre != null ) || ( this.pkSemestre != null && !this.pkSemestre.equals( other.pkSemestre ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Trimestre[ pkSemestre=" + pkSemestre + " ]";
    }
    
}
