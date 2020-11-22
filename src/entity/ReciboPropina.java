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
@Table( name = "recibo_propina" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "ReciboPropina.findAll", query = "SELECT r FROM ReciboPropina r" )
    , @NamedQuery( name = "ReciboPropina.findByPkReciboPropina", query = "SELECT r FROM ReciboPropina r WHERE r.pkReciboPropina = :pkReciboPropina" )
    , @NamedQuery( name = "ReciboPropina.findByData", query = "SELECT r FROM ReciboPropina r WHERE r.data = :data" )
    , @NamedQuery( name = "ReciboPropina.findByHora", query = "SELECT r FROM ReciboPropina r WHERE r.hora = :hora" )
    , @NamedQuery( name = "ReciboPropina.findByTotal", query = "SELECT r FROM ReciboPropina r WHERE r.total = :total" )
    , @NamedQuery( name = "ReciboPropina.findByMulta", query = "SELECT r FROM ReciboPropina r WHERE r.multa = :multa" )
    , @NamedQuery( name = "ReciboPropina.findByDesconto", query = "SELECT r FROM ReciboPropina r WHERE r.desconto = :desconto" )
    , @NamedQuery( name = "ReciboPropina.findByTroco", query = "SELECT r FROM ReciboPropina r WHERE r.troco = :troco" )
    , @NamedQuery( name = "ReciboPropina.findByObs", query = "SELECT r FROM ReciboPropina r WHERE r.obs = :obs" )
    , @NamedQuery( name = "ReciboPropina.findByNumBolderoux", query = "SELECT r FROM ReciboPropina r WHERE r.numBolderoux = :numBolderoux" )
} )
public class ReciboPropina implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_recibo_propina" )
    private Integer pkReciboPropina;
    @Column( name = "data" )
    @Temporal( TemporalType.DATE )
    private Date data;
    @Column( name = "hora" )
    @Temporal( TemporalType.TIME )
    private Date hora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column( name = "total" )
    private Double total;
    @Column( name = "multa" )
    private Double multa;
    @Column( name = "desconto" )
    private Double desconto;
    @Column( name = "troco" )
    private Double troco;
    @Column( name = "obs" )
    private String obs;
    @Column( name = "num_bolderoux" )
    private String numBolderoux;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkReciboPropina" )
    private List<Propina> propinaList;
    @JoinColumn( name = "fk_estudante", referencedColumnName = "pk_estudante" )
    @ManyToOne( optional = false )
    private Estudante fkEstudante;
    @JoinColumn( name = "fk_usuario", referencedColumnName = "pk_usuario" )
    @ManyToOne( optional = false )
    private Usuario fkUsuario;

    public ReciboPropina()
    {
    }

    public ReciboPropina( Integer pkReciboPropina )
    {
        this.pkReciboPropina = pkReciboPropina;
    }

    public Integer getPkReciboPropina()
    {
        return pkReciboPropina;
    }

    public void setPkReciboPropina( Integer pkReciboPropina )
    {
        this.pkReciboPropina = pkReciboPropina;
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

    public Double getTotal()
    {
        return total;
    }

    public void setTotal( Double total )
    {
        this.total = total;
    }

    public Double getMulta()
    {
        return multa;
    }

    public void setMulta( Double multa )
    {
        this.multa = multa;
    }

    public Double getDesconto()
    {
        return desconto;
    }

    public void setDesconto( Double desconto )
    {
        this.desconto = desconto;
    }

    public Double getTroco()
    {
        return troco;
    }

    public void setTroco( Double troco )
    {
        this.troco = troco;
    }

    public String getObs()
    {
        return obs;
    }

    public void setObs( String obs )
    {
        this.obs = obs;
    }

    public String getNumBolderoux()
    {
        return numBolderoux;
    }

    public void setNumBolderoux( String numBolderoux )
    {
        this.numBolderoux = numBolderoux;
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

    public Estudante getFkEstudante()
    {
        return fkEstudante;
    }

    public void setFkEstudante( Estudante fkEstudante )
    {
        this.fkEstudante = fkEstudante;
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
        hash += ( pkReciboPropina != null ? pkReciboPropina.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof ReciboPropina ) )
        {
            return false;
        }
        ReciboPropina other = ( ReciboPropina ) object;
        if ( ( this.pkReciboPropina == null && other.pkReciboPropina != null ) || ( this.pkReciboPropina != null && !this.pkReciboPropina.equals( other.pkReciboPropina ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.ReciboPropina[ pkReciboPropina=" + pkReciboPropina + " ]";
    }
    
}
