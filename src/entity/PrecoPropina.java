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
@Table( name = "preco_propina" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "PrecoPropina.findAll", query = "SELECT p FROM PrecoPropina p" )
    , @NamedQuery( name = "PrecoPropina.findByPkPrecoPropina", query = "SELECT p FROM PrecoPropina p WHERE p.pkPrecoPropina = :pkPrecoPropina" )
    , @NamedQuery( name = "PrecoPropina.findByValor", query = "SELECT p FROM PrecoPropina p WHERE p.valor = :valor" )
    , @NamedQuery( name = "PrecoPropina.findByData", query = "SELECT p FROM PrecoPropina p WHERE p.data = :data" )
    , @NamedQuery( name = "PrecoPropina.findByHora", query = "SELECT p FROM PrecoPropina p WHERE p.hora = :hora" )
} )
public class PrecoPropina implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_preco_propina" )
    private Integer pkPrecoPropina;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column( name = "valor" )
    private Double valor;
    @Column( name = "data" )
    @Temporal( TemporalType.DATE )
    private Date data;
    @Column( name = "hora" )
    @Temporal( TemporalType.TIME )
    private Date hora;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkPrecoPropina" )
    private List<Propina> propinaList;
    @JoinColumn( name = "fk_classe", referencedColumnName = "pk_classe" )
    @ManyToOne( optional = false )
    private Classe fkClasse;
    @JoinColumn( name = "fk_curso", referencedColumnName = "pk_curso" )
    @ManyToOne( optional = false )
    private Curso fkCurso;
    @JoinColumn( name = "fk_usuario", referencedColumnName = "pk_usuario" )
    @ManyToOne( optional = false )
    private Usuario fkUsuario;

    public PrecoPropina()
    {
    }

    public PrecoPropina( Integer pkPrecoPropina )
    {
        this.pkPrecoPropina = pkPrecoPropina;
    }

    public Integer getPkPrecoPropina()
    {
        return pkPrecoPropina;
    }

    public void setPkPrecoPropina( Integer pkPrecoPropina )
    {
        this.pkPrecoPropina = pkPrecoPropina;
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

    @XmlTransient
    public List<Propina> getPropinaList()
    {
        return propinaList;
    }

    public void setPropinaList( List<Propina> propinaList )
    {
        this.propinaList = propinaList;
    }

    public Classe getFkClasse()
    {
        return fkClasse;
    }

    public void setFkClasse( Classe fkClasse )
    {
        this.fkClasse = fkClasse;
    }

    public Curso getFkCurso()
    {
        return fkCurso;
    }

    public void setFkCurso( Curso fkCurso )
    {
        this.fkCurso = fkCurso;
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
        hash += ( pkPrecoPropina != null ? pkPrecoPropina.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof PrecoPropina ) )
        {
            return false;
        }
        PrecoPropina other = ( PrecoPropina ) object;
        if ( ( this.pkPrecoPropina == null && other.pkPrecoPropina != null ) || ( this.pkPrecoPropina != null && !this.pkPrecoPropina.equals( other.pkPrecoPropina ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.PrecoPropina[ pkPrecoPropina=" + pkPrecoPropina + " ]";
    }
    
}
