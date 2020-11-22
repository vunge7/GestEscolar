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
@Table( name = "turno" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Turno.findAll", query = "SELECT t FROM Turno t" )
    , @NamedQuery( name = "Turno.findByPkTurno", query = "SELECT t FROM Turno t WHERE t.pkTurno = :pkTurno" )
    , @NamedQuery( name = "Turno.findByDesignacao", query = "SELECT t FROM Turno t WHERE t.designacao = :designacao" )
} )
public class Turno implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_turno" )
    private Integer pkTurno;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkTurno" )
    private List<Turma> turmaList;

    public Turno()
    {
    }

    public Turno( Integer pkTurno )
    {
        this.pkTurno = pkTurno;
    }

    public Integer getPkTurno()
    {
        return pkTurno;
    }

    public void setPkTurno( Integer pkTurno )
    {
        this.pkTurno = pkTurno;
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
        hash += ( pkTurno != null ? pkTurno.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Turno ) )
        {
            return false;
        }
        Turno other = ( Turno ) object;
        if ( ( this.pkTurno == null && other.pkTurno != null ) || ( this.pkTurno != null && !this.pkTurno.equals( other.pkTurno ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Turno[ pkTurno=" + pkTurno + " ]";
    }
    
}
