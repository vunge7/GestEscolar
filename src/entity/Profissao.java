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
@Table( name = "profissao" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Profissao.findAll", query = "SELECT p FROM Profissao p" )
    , @NamedQuery( name = "Profissao.findByPkProfissao", query = "SELECT p FROM Profissao p WHERE p.pkProfissao = :pkProfissao" )
    , @NamedQuery( name = "Profissao.findByDesignacao", query = "SELECT p FROM Profissao p WHERE p.designacao = :designacao" )
} )
public class Profissao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_profissao" )
    private Integer pkProfissao;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkProfissao" )
    private List<Encarregado> encarregadoList;

    public Profissao()
    {
    }

    public Profissao( Integer pkProfissao )
    {
        this.pkProfissao = pkProfissao;
    }

    public Integer getPkProfissao()
    {
        return pkProfissao;
    }

    public void setPkProfissao( Integer pkProfissao )
    {
        this.pkProfissao = pkProfissao;
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
    public List<Encarregado> getEncarregadoList()
    {
        return encarregadoList;
    }

    public void setEncarregadoList( List<Encarregado> encarregadoList )
    {
        this.encarregadoList = encarregadoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkProfissao != null ? pkProfissao.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Profissao ) )
        {
            return false;
        }
        Profissao other = ( Profissao ) object;
        if ( ( this.pkProfissao == null && other.pkProfissao != null ) || ( this.pkProfissao != null && !this.pkProfissao.equals( other.pkProfissao ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Profissao[ pkProfissao=" + pkProfissao + " ]";
    }
    
}
