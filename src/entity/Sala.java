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
@Table( name = "sala" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Sala.findAll", query = "SELECT s FROM Sala s" )
    , @NamedQuery( name = "Sala.findByPkSala", query = "SELECT s FROM Sala s WHERE s.pkSala = :pkSala" )
    , @NamedQuery( name = "Sala.findByDesignacao", query = "SELECT s FROM Sala s WHERE s.designacao = :designacao" )
} )
public class Sala implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_sala" )
    private Integer pkSala;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkSala" )
    private List<Turma> turmaList;

    public Sala()
    {
    }

    public Sala( Integer pkSala )
    {
        this.pkSala = pkSala;
    }

    public Integer getPkSala()
    {
        return pkSala;
    }

    public void setPkSala( Integer pkSala )
    {
        this.pkSala = pkSala;
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
        hash += ( pkSala != null ? pkSala.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Sala ) )
        {
            return false;
        }
        Sala other = ( Sala ) object;
        if ( ( this.pkSala == null && other.pkSala != null ) || ( this.pkSala != null && !this.pkSala.equals( other.pkSala ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Sala[ pkSala=" + pkSala + " ]";
    }
    
}
