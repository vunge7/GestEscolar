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
@Table( name = "ano_lectivo" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "AnoLectivo.findAll", query = "SELECT a FROM AnoLectivo a" )
    , @NamedQuery( name = "AnoLectivo.findByPkAnoLectivo", query = "SELECT a FROM AnoLectivo a WHERE a.pkAnoLectivo = :pkAnoLectivo" )
    , @NamedQuery( name = "AnoLectivo.findByDesignacao", query = "SELECT a FROM AnoLectivo a WHERE a.designacao = :designacao" )
} )
public class AnoLectivo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_ano_lectivo" )
    private Integer pkAnoLectivo;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkAnoLectivo" )
    private List<Nota> notaList;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkAnoLectivo" )
    private List<Turma> turmaList;

    public AnoLectivo()
    {
    }

    public AnoLectivo( Integer pkAnoLectivo )
    {
        this.pkAnoLectivo = pkAnoLectivo;
    }

    public Integer getPkAnoLectivo()
    {
        return pkAnoLectivo;
    }

    public void setPkAnoLectivo( Integer pkAnoLectivo )
    {
        this.pkAnoLectivo = pkAnoLectivo;
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

    @XmlTransient
    public List<Turma> getTurmaList()
    {
        return turmaList;
    }

    public void setTurmaList( List<Turma> turmaList )
    {
        this.turmaList = turmaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkAnoLectivo != null ? pkAnoLectivo.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof AnoLectivo ) )
        {
            return false;
        }
        AnoLectivo other = ( AnoLectivo ) object;
        if ( ( this.pkAnoLectivo == null && other.pkAnoLectivo != null ) || ( this.pkAnoLectivo != null && !this.pkAnoLectivo.equals( other.pkAnoLectivo ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.AnoLectivo[ pkAnoLectivo=" + pkAnoLectivo + " ]";
    }
    
}
