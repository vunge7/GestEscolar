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
@Table( name = "encarregado" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Encarregado.findAll", query = "SELECT e FROM Encarregado e" )
    , @NamedQuery( name = "Encarregado.findByPkEncarregado", query = "SELECT e FROM Encarregado e WHERE e.pkEncarregado = :pkEncarregado" )
    , @NamedQuery( name = "Encarregado.findByNomeCompleto", query = "SELECT e FROM Encarregado e WHERE e.nomeCompleto = :nomeCompleto" )
    , @NamedQuery( name = "Encarregado.findByLocalTrabalho", query = "SELECT e FROM Encarregado e WHERE e.localTrabalho = :localTrabalho" )
    , @NamedQuery( name = "Encarregado.findByResidencia", query = "SELECT e FROM Encarregado e WHERE e.residencia = :residencia" )
    , @NamedQuery( name = "Encarregado.findByTelefone", query = "SELECT e FROM Encarregado e WHERE e.telefone = :telefone" )
} )
public class Encarregado implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_encarregado" )
    private Integer pkEncarregado;
    @Column( name = "nomeCompleto" )
    private String nomeCompleto;
    @Column( name = "localTrabalho" )
    private String localTrabalho;
    @Column( name = "residencia" )
    private String residencia;
    @Column( name = "telefone" )
    private String telefone;
    @JoinColumn( name = "fk_profissao", referencedColumnName = "pk_profissao" )
    @ManyToOne( optional = false )
    private Profissao fkProfissao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkEncarregado" )
    private List<Estudante> estudanteList;

    public Encarregado()
    {
    }

    public Encarregado( Integer pkEncarregado )
    {
        this.pkEncarregado = pkEncarregado;
    }

    public Integer getPkEncarregado()
    {
        return pkEncarregado;
    }

    public void setPkEncarregado( Integer pkEncarregado )
    {
        this.pkEncarregado = pkEncarregado;
    }

    public String getNomeCompleto()
    {
        return nomeCompleto;
    }

    public void setNomeCompleto( String nomeCompleto )
    {
        this.nomeCompleto = nomeCompleto;
    }

    public String getLocalTrabalho()
    {
        return localTrabalho;
    }

    public void setLocalTrabalho( String localTrabalho )
    {
        this.localTrabalho = localTrabalho;
    }

    public String getResidencia()
    {
        return residencia;
    }

    public void setResidencia( String residencia )
    {
        this.residencia = residencia;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone( String telefone )
    {
        this.telefone = telefone;
    }

    public Profissao getFkProfissao()
    {
        return fkProfissao;
    }

    public void setFkProfissao( Profissao fkProfissao )
    {
        this.fkProfissao = fkProfissao;
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
        hash += ( pkEncarregado != null ? pkEncarregado.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Encarregado ) )
        {
            return false;
        }
        Encarregado other = ( Encarregado ) object;
        if ( ( this.pkEncarregado == null && other.pkEncarregado != null ) || ( this.pkEncarregado != null && !this.pkEncarregado.equals( other.pkEncarregado ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Encarregado[ pkEncarregado=" + pkEncarregado + " ]";
    }
    
}
