/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mac
 */
@Entity
@Table( name = "confirmacao_matricula" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "ConfirmacaoMatricula.findAll", query = "SELECT c FROM ConfirmacaoMatricula c" )
    , @NamedQuery( name = "ConfirmacaoMatricula.findByPkConfirmacaoMatricula", query = "SELECT c FROM ConfirmacaoMatricula c WHERE c.pkConfirmacaoMatricula = :pkConfirmacaoMatricula" )
    , @NamedQuery( name = "ConfirmacaoMatricula.findByData", query = "SELECT c FROM ConfirmacaoMatricula c WHERE c.data = :data" )
    , @NamedQuery( name = "ConfirmacaoMatricula.findByHora", query = "SELECT c FROM ConfirmacaoMatricula c WHERE c.hora = :hora" )
} )
public class ConfirmacaoMatricula implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_confirmacao_matricula" )
    private Integer pkConfirmacaoMatricula;
    @Column( name = "data" )
    @Temporal( TemporalType.DATE )
    private Date data;
    @Column( name = "hora" )
    @Temporal( TemporalType.TIME )
    private Date hora;
    @JoinColumn( name = "fk_estudante", referencedColumnName = "pk_estudante" )
    @ManyToOne( optional = false )
    private Estudante fkEstudante;
    @JoinColumn( name = "fk_preco_confirmacao", referencedColumnName = "pk_preco_confirmacao" )
    @ManyToOne( optional = false )
    private PrecoConfirmacao fkPrecoConfirmacao;
    @JoinColumn( name = "fk_tipo_confirmacao", referencedColumnName = "pk_tipo_confirmacao" )
    @ManyToOne( optional = false )
    private TipoConfirmacao fkTipoConfirmacao;
    @JoinColumn( name = "fk_turma", referencedColumnName = "pk_turma" )
    @ManyToOne( optional = false )
    private Turma fkTurma;
    @JoinColumn( name = "fk_usuario", referencedColumnName = "pk_usuario" )
    @ManyToOne( optional = false )
    private Usuario fkUsuario;

    public ConfirmacaoMatricula()
    {
    }

    public ConfirmacaoMatricula( Integer pkConfirmacaoMatricula )
    {
        this.pkConfirmacaoMatricula = pkConfirmacaoMatricula;
    }

    public Integer getPkConfirmacaoMatricula()
    {
        return pkConfirmacaoMatricula;
    }

    public void setPkConfirmacaoMatricula( Integer pkConfirmacaoMatricula )
    {
        this.pkConfirmacaoMatricula = pkConfirmacaoMatricula;
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

    public Estudante getFkEstudante()
    {
        return fkEstudante;
    }

    public void setFkEstudante( Estudante fkEstudante )
    {
        this.fkEstudante = fkEstudante;
    }

    public PrecoConfirmacao getFkPrecoConfirmacao()
    {
        return fkPrecoConfirmacao;
    }

    public void setFkPrecoConfirmacao( PrecoConfirmacao fkPrecoConfirmacao )
    {
        this.fkPrecoConfirmacao = fkPrecoConfirmacao;
    }

    public TipoConfirmacao getFkTipoConfirmacao()
    {
        return fkTipoConfirmacao;
    }

    public void setFkTipoConfirmacao( TipoConfirmacao fkTipoConfirmacao )
    {
        this.fkTipoConfirmacao = fkTipoConfirmacao;
    }

    public Turma getFkTurma()
    {
        return fkTurma;
    }

    public void setFkTurma( Turma fkTurma )
    {
        this.fkTurma = fkTurma;
    }

    public Usuario getFkUsuario()
    {
        return fkUsuario;
    }

    public void setFkUsuario( Usuario fkUsuario )
    {
        this.fkUsuario = fkUsuario;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkConfirmacaoMatricula != null ? pkConfirmacaoMatricula.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof ConfirmacaoMatricula ) )
        {
            return false;
        }
        ConfirmacaoMatricula other = ( ConfirmacaoMatricula ) object;
        if ( ( this.pkConfirmacaoMatricula == null && other.pkConfirmacaoMatricula != null ) || ( this.pkConfirmacaoMatricula != null && !this.pkConfirmacaoMatricula.equals( other.pkConfirmacaoMatricula ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.ConfirmacaoMatricula[ pkConfirmacaoMatricula=" + pkConfirmacaoMatricula + " ]";
    }
    
}
