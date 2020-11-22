/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mac
 */
@Entity
@Table( name = "propina" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Propina.findAll", query = "SELECT p FROM Propina p" )
    , @NamedQuery( name = "Propina.findByPkPropina", query = "SELECT p FROM Propina p WHERE p.pkPropina = :pkPropina" )
    , @NamedQuery( name = "Propina.findByDesconto", query = "SELECT p FROM Propina p WHERE p.desconto = :desconto" )
    , @NamedQuery( name = "Propina.findByMulta", query = "SELECT p FROM Propina p WHERE p.multa = :multa" )
} )
public class Propina implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_propina" )
    private Integer pkPropina;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column( name = "desconto" )
    private Double desconto;
    @Column( name = "multa" )
    private Double multa;
    @JoinColumn( name = "fk_mes", referencedColumnName = "pk_mes" )
    @ManyToOne( optional = false )
    private Mes fkMes;
    @JoinColumn( name = "fk_preco_propina", referencedColumnName = "pk_preco_propina" )
    @ManyToOne( optional = false )
    private PrecoPropina fkPrecoPropina;
    @JoinColumn( name = "fk_recibo_propina", referencedColumnName = "pk_recibo_propina" )
    @ManyToOne( optional = false )
    private ReciboPropina fkReciboPropina;

    public Propina()
    {
    }

    public Propina( Integer pkPropina )
    {
        this.pkPropina = pkPropina;
    }

    public Integer getPkPropina()
    {
        return pkPropina;
    }

    public void setPkPropina( Integer pkPropina )
    {
        this.pkPropina = pkPropina;
    }

    public Double getDesconto()
    {
        return desconto;
    }

    public void setDesconto( Double desconto )
    {
        this.desconto = desconto;
    }

    public Double getMulta()
    {
        return multa;
    }

    public void setMulta( Double multa )
    {
        this.multa = multa;
    }

    public Mes getFkMes()
    {
        return fkMes;
    }

    public void setFkMes( Mes fkMes )
    {
        this.fkMes = fkMes;
    }

    public PrecoPropina getFkPrecoPropina()
    {
        return fkPrecoPropina;
    }

    public void setFkPrecoPropina( PrecoPropina fkPrecoPropina )
    {
        this.fkPrecoPropina = fkPrecoPropina;
    }

    public ReciboPropina getFkReciboPropina()
    {
        return fkReciboPropina;
    }

    public void setFkReciboPropina( ReciboPropina fkReciboPropina )
    {
        this.fkReciboPropina = fkReciboPropina;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkPropina != null ? pkPropina.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Propina ) )
        {
            return false;
        }
        Propina other = ( Propina ) object;
        if ( ( this.pkPropina == null && other.pkPropina != null ) || ( this.pkPropina != null && !this.pkPropina.equals( other.pkPropina ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Propina[ pkPropina=" + pkPropina + " ]";
    }
    
}
