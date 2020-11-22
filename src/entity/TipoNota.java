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
@Table( name = "tipo_nota" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "TipoNota.findAll", query = "SELECT t FROM TipoNota t" )
    , @NamedQuery( name = "TipoNota.findByPkTipoNota", query = "SELECT t FROM TipoNota t WHERE t.pkTipoNota = :pkTipoNota" )
    , @NamedQuery( name = "TipoNota.findByDesignacao", query = "SELECT t FROM TipoNota t WHERE t.designacao = :designacao" )
} )
public class TipoNota implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic( optional = false )
    @Column( name = "pk_tipo_nota" )
    private Integer pkTipoNota;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkTipoNota" )
    private List<Nota> notaList;

    public TipoNota()
    {
    }

    public TipoNota( Integer pkTipoNota )
    {
        this.pkTipoNota = pkTipoNota;
    }

    public Integer getPkTipoNota()
    {
        return pkTipoNota;
    }

    public void setPkTipoNota( Integer pkTipoNota )
    {
        this.pkTipoNota = pkTipoNota;
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
    public List<Nota> getNotaList()
    {
        return notaList;
    }

    public void setNotaList( List<Nota> notaList )
    {
        this.notaList = notaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkTipoNota != null ? pkTipoNota.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof TipoNota ) )
        {
            return false;
        }
        TipoNota other = ( TipoNota ) object;
        if ( ( this.pkTipoNota == null && other.pkTipoNota != null ) || ( this.pkTipoNota != null && !this.pkTipoNota.equals( other.pkTipoNota ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.TipoNota[ pkTipoNota=" + pkTipoNota + " ]";
    }
    
}
