/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mac
 */
@Entity
@Table( name = "preco_confirmacao" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "PrecoConfirmacao.findAll", query = "SELECT p FROM PrecoConfirmacao p" )
    , @NamedQuery( name = "PrecoConfirmacao.findByPkPrecoConfirmacao", query = "SELECT p FROM PrecoConfirmacao p WHERE p.pkPrecoConfirmacao = :pkPrecoConfirmacao" )
    , @NamedQuery( name = "PrecoConfirmacao.findByValor", query = "SELECT p FROM PrecoConfirmacao p WHERE p.valor = :valor" )
    , @NamedQuery( name = "PrecoConfirmacao.findByData", query = "SELECT p FROM PrecoConfirmacao p WHERE p.data = :data" )
    , @NamedQuery( name = "PrecoConfirmacao.findByHora", query = "SELECT p FROM PrecoConfirmacao p WHERE p.hora = :hora" )
} )
public class PrecoConfirmacao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_preco_confirmacao" )
    private Integer pkPrecoConfirmacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column( name = "valor" )
    private Double valor;
    @Column( name = "data" )
    @Temporal( TemporalType.DATE )
    private Date data;
    @Column( name = "hora" )
    @Temporal( TemporalType.TIME )
    private Date hora;
    @JoinColumn( name = "fk_curso", referencedColumnName = "pk_curso" )
    @ManyToOne( optional = false )
    private Curso fkCurso;
    @JoinColumn( name = "fk_tipo_confirmacao", referencedColumnName = "pk_tipo_confirmacao" )
    @ManyToOne( optional = false )
    private TipoConfirmacao fkTipoConfirmacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkPrecoConfirmacao" )
    private List<ConfirmacaoMatricula> confirmacaoMatriculaList;

    public PrecoConfirmacao()
    {
    }

    public PrecoConfirmacao( Integer pkPrecoConfirmacao )
    {
        this.pkPrecoConfirmacao = pkPrecoConfirmacao;
    }

    public Integer getPkPrecoConfirmacao()
    {
        return pkPrecoConfirmacao;
    }

    public void setPkPrecoConfirmacao( Integer pkPrecoConfirmacao )
    {
        this.pkPrecoConfirmacao = pkPrecoConfirmacao;
    }

    public Double getValor()
    {
        return valor;
    }

    public void setValor( Double valor )
    {
        this.valor = valor;
    }

    public Date getData()
    {
        return data;
    }

    public void setData( Date data )
    {
        this.data = data;
    }

    public Date getHora()
    {
        return hora;
    }

    public void setHora( Date hora )
    {
        this.hora = hora;
    }

    public Curso getFkCurso()
    {
        return fkCurso;
    }

    public void setFkCurso( Curso fkCurso )
    {
        this.fkCurso = fkCurso;
    }

    public TipoConfirmacao getFkTipoConfirmacao()
    {
        return fkTipoConfirmacao;
    }

    public void setFkTipoConfirmacao( TipoConfirmacao fkTipoConfirmacao )
    {
        this.fkTipoConfirmacao = fkTipoConfirmacao;
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
        hash += ( pkPrecoConfirmacao != null ? pkPrecoConfirmacao.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof PrecoConfirmacao ) )
        {
            return false;
        }
        PrecoConfirmacao other = ( PrecoConfirmacao ) object;
        if ( ( this.pkPrecoConfirmacao == null && other.pkPrecoConfirmacao != null ) || ( this.pkPrecoConfirmacao != null && !this.pkPrecoConfirmacao.equals( other.pkPrecoConfirmacao ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.PrecoConfirmacao[ pkPrecoConfirmacao=" + pkPrecoConfirmacao + " ]";
    }
    
}
