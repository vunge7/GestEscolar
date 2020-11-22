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
@Table( name = "curso" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Curso.findAll", query = "SELECT c FROM Curso c" )
    , @NamedQuery( name = "Curso.findByPkCurso", query = "SELECT c FROM Curso c WHERE c.pkCurso = :pkCurso" )
    , @NamedQuery( name = "Curso.findByDesignacao", query = "SELECT c FROM Curso c WHERE c.designacao = :designacao" )
} )
public class Curso implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_curso" )
    private Integer pkCurso;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkCurso" )
    private List<PrecoConfirmacao> precoConfirmacaoList;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkCurso" )
    private List<PrecoPropina> precoPropinaList;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkCurso" )
    private List<Turma> turmaList;

    public Curso()
    {
    }

    public Curso( Integer pkCurso )
    {
        this.pkCurso = pkCurso;
    }

    public Integer getPkCurso()
    {
        return pkCurso;
    }

    public void setPkCurso( Integer pkCurso )
    {
        this.pkCurso = pkCurso;
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
        hash += ( pkCurso != null ? pkCurso.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Curso ) )
        {
            return false;
        }
        Curso other = ( Curso ) object;
        if ( ( this.pkCurso == null && other.pkCurso != null ) || ( this.pkCurso != null && !this.pkCurso.equals( other.pkCurso ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Curso[ pkCurso=" + pkCurso + " ]";
    }
    
}
