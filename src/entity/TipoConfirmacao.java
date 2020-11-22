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
@Table( name = "tipo_confirmacao" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "TipoConfirmacao.findAll", query = "SELECT t FROM TipoConfirmacao t" )
    , @NamedQuery( name = "TipoConfirmacao.findByPkTipoConfirmacao", query = "SELECT t FROM TipoConfirmacao t WHERE t.pkTipoConfirmacao = :pkTipoConfirmacao" )
    , @NamedQuery( name = "TipoConfirmacao.findByDesignacao", query = "SELECT t FROM TipoConfirmacao t WHERE t.designacao = :designacao" )
} )
public class TipoConfirmacao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_tipo_confirmacao" )
    private Integer pkTipoConfirmacao;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkTipoConfirmacao" )
    private List<PrecoConfirmacao> precoConfirmacaoList;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkTipoConfirmacao" )
    private List<ConfirmacaoMatricula> confirmacaoMatriculaList;

    public TipoConfirmacao()
    {
    }

    public TipoConfirmacao( Integer pkTipoConfirmacao )
    {
        this.pkTipoConfirmacao = pkTipoConfirmacao;
    }

    public Integer getPkTipoConfirmacao()
    {
        return pkTipoConfirmacao;
    }

    public void setPkTipoConfirmacao( Integer pkTipoConfirmacao )
    {
        this.pkTipoConfirmacao = pkTipoConfirmacao;
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
    public List<PrecoConfirmacao> getPrecoConfirmacaoList()
    {
        return precoConfirmacaoList;
    }

    public void setPrecoConfirmacaoList( List<PrecoConfirmacao> precoConfirmacaoList )
    {
        this.precoConfirmacaoList = precoConfirmacaoList;
    }

    @XmlTransient
    public List<ConfirmacaoMatricula> getConfirmacaoMatriculaList()
    {
        return confirmacaoMatriculaList;
    }

    public void setConfirmacaoMatriculaList( List<ConfirmacaoMatricula> confirmacaoMatriculaList )
    {
        this.confirmacaoMatriculaList = confirmacaoMatriculaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkTipoConfirmacao != null ? pkTipoConfirmacao.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof TipoConfirmacao ) )
        {
            return false;
        }
        TipoConfirmacao other = ( TipoConfirmacao ) object;
        if ( ( this.pkTipoConfirmacao == null && other.pkTipoConfirmacao != null ) || ( this.pkTipoConfirmacao != null && !this.pkTipoConfirmacao.equals( other.pkTipoConfirmacao ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.TipoConfirmacao[ pkTipoConfirmacao=" + pkTipoConfirmacao + " ]";
    }
    
}
