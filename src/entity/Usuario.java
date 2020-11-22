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
@Table( name = "usuario" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Usuario.findAll", query = "SELECT u FROM Usuario u" )
    , @NamedQuery( name = "Usuario.findByPkUsuario", query = "SELECT u FROM Usuario u WHERE u.pkUsuario = :pkUsuario" )
    , @NamedQuery( name = "Usuario.findByNomeCompleto", query = "SELECT u FROM Usuario u WHERE u.nomeCompleto = :nomeCompleto" )
    , @NamedQuery( name = "Usuario.findByUser", query = "SELECT u FROM Usuario u WHERE u.user = :user" )
    , @NamedQuery( name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha" )
} )
public class Usuario implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_usuario" )
    private Integer pkUsuario;
    @Column( name = "nome_completo" )
    private String nomeCompleto;
    @Column( name = "user" )
    private String user;
    @Column( name = "senha" )
    private String senha;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkUsuario" )
    private List<ReciboPropina> reciboPropinaList;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkUsuario" )
    private List<ConfirmacaoMatricula> confirmacaoMatriculaList;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkUsuario" )
    private List<PrecoPropina> precoPropinaList;

    public Usuario()
    {
    }

    public Usuario( Integer pkUsuario )
    {
        this.pkUsuario = pkUsuario;
    }

    public Integer getPkUsuario()
    {
        return pkUsuario;
    }

    public void setPkUsuario( Integer pkUsuario )
    {
        this.pkUsuario = pkUsuario;
    }

    public String getNomeCompleto()
    {
        return nomeCompleto;
    }

    public void setNomeCompleto( String nomeCompleto )
    {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser( String user )
    {
        this.user = user;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha( String senha )
    {
        this.senha = senha;
    }

    @XmlTransient
    public List<ReciboPropina> getReciboPropinaList()
    {
        return reciboPropinaList;
    }

    public void setReciboPropinaList( List<ReciboPropina> reciboPropinaList )
    {
        this.reciboPropinaList = reciboPropinaList;
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

    @XmlTransient
    public List<PrecoPropina> getPrecoPropinaList()
    {
        return precoPropinaList;
    }

    public void setPrecoPropinaList( List<PrecoPropina> precoPropinaList )
    {
        this.precoPropinaList = precoPropinaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkUsuario != null ? pkUsuario.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Usuario ) )
        {
            return false;
        }
        Usuario other = ( Usuario ) object;
        if ( ( this.pkUsuario == null && other.pkUsuario != null ) || ( this.pkUsuario != null && !this.pkUsuario.equals( other.pkUsuario ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Usuario[ pkUsuario=" + pkUsuario + " ]";
    }
    
}
