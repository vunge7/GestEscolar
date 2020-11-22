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
@Table( name = "classe" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Classe.findAll", query = "SELECT c FROM Classe c" )
    , @NamedQuery( name = "Classe.findByPkClasse", query = "SELECT c FROM Classe c WHERE c.pkClasse = :pkClasse" )
    , @NamedQuery( name = "Classe.findByDesignacao", query = "SELECT c FROM Classe c WHERE c.designacao = :designacao" )
} )
public class Classe implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_classe" )
    private Integer pkClasse;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkClasse" )
    private List<PrecoPropina> precoPropinaList;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkClasse" )
    private List<Turma> turmaList;

    public Classe()
    {
    }

    public Classe( Integer pkClasse )
    {
        this.pkClasse = pkClasse;
    }

    public Integer getPkClasse()
    {
        return pkClasse;
    }

    public void setPkClasse( Integer pkClasse )
    {
        this.pkClasse = pkClasse;
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
    public List<PrecoPropina> getPrecoPropinaList()
    {
        return precoPropinaList;
    }

    public void setPrecoPropinaList( List<PrecoPropina> precoPropinaList )
    {
        this.precoPropinaList = precoPropinaList;
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
        hash += ( pkClasse != null ? pkClasse.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Classe ) )
        {
            return false;
        }
        Classe other = ( Classe ) object;
        if ( ( this.pkClasse == null && other.pkClasse != null ) || ( this.pkClasse != null && !this.pkClasse.equals( other.pkClasse ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Classe[ pkClasse=" + pkClasse + " ]";
    }
    
}
