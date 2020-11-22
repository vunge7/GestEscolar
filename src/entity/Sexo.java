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
@Table( name = "sexo" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Sexo.findAll", query = "SELECT s FROM Sexo s" )
    , @NamedQuery( name = "Sexo.findByPkSexo", query = "SELECT s FROM Sexo s WHERE s.pkSexo = :pkSexo" )
    , @NamedQuery( name = "Sexo.findByDesignacao", query = "SELECT s FROM Sexo s WHERE s.designacao = :designacao" )
} )
public class Sexo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_sexo" )
    private Integer pkSexo;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkSexo" )
    private List<Estudante> estudanteList;

    public Sexo()
    {
    }

    public Sexo( Integer pkSexo )
    {
        this.pkSexo = pkSexo;
    }

    public Integer getPkSexo()
    {
        return pkSexo;
    }

    public void setPkSexo( Integer pkSexo )
    {
        this.pkSexo = pkSexo;
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
        hash += ( pkSexo != null ? pkSexo.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Sexo ) )
        {
            return false;
        }
        Sexo other = ( Sexo ) object;
        if ( ( this.pkSexo == null && other.pkSexo != null ) || ( this.pkSexo != null && !this.pkSexo.equals( other.pkSexo ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Sexo[ pkSexo=" + pkSexo + " ]";
    }
    
}
