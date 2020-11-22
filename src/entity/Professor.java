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
@Table( name = "professor" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Professor.findAll", query = "SELECT p FROM Professor p" )
    , @NamedQuery( name = "Professor.findByPkProfessor", query = "SELECT p FROM Professor p WHERE p.pkProfessor = :pkProfessor" )
    , @NamedQuery( name = "Professor.findByNomeComplero", query = "SELECT p FROM Professor p WHERE p.nomeComplero = :nomeComplero" )
    , @NamedQuery( name = "Professor.findByTelefone", query = "SELECT p FROM Professor p WHERE p.telefone = :telefone" )
    , @NamedQuery( name = "Professor.findByEmail", query = "SELECT p FROM Professor p WHERE p.email = :email" )
    , @NamedQuery( name = "Professor.findByEndereco", query = "SELECT p FROM Professor p WHERE p.endereco = :endereco" )
    , @NamedQuery( name = "Professor.findByUserName", query = "SELECT p FROM Professor p WHERE p.userName = :userName" )
    , @NamedQuery( name = "Professor.findBySenha", query = "SELECT p FROM Professor p WHERE p.senha = :senha" )
} )
public class Professor implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_professor" )
    private Integer pkProfessor;
    @Column( name = "nome_complero" )
    private String nomeComplero;
    @Column( name = "telefone" )
    private String telefone;
    @Column( name = "email" )
    private String email;
    @Column( name = "endereco" )
    private String endereco;
    @Column( name = "user_name" )
    private String userName;
    @Column( name = "senha" )
    private String senha;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkProfessor" )
    private List<Nota> notaList;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkProfessor" )
    private List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaList;

    public Professor()
    {
    }

    public Professor( Integer pkProfessor )
    {
        this.pkProfessor = pkProfessor;
    }

    public Integer getPkProfessor()
    {
        return pkProfessor;
    }

    public void setPkProfessor( Integer pkProfessor )
    {
        this.pkProfessor = pkProfessor;
    }

    public String getNomeComplero()
    {
        return nomeComplero;
    }

    public void setNomeComplero( String nomeComplero )
    {
        this.nomeComplero = nomeComplero;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone( String telefone )
    {
        this.telefone = telefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco( String endereco )
    {
        this.endereco = endereco;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
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
    public List<Nota> getNotaList()
    {
        return notaList;
    }

    public void setNotaList( List<Nota> notaList )
    {
        this.notaList = notaList;
    }

    @XmlTransient
    public List<ItemTurmaProfessorDisciplina> getItemTurmaProfessorDisciplinaList()
    {
        return itemTurmaProfessorDisciplinaList;
    }

    public void setItemTurmaProfessorDisciplinaList( List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaList )
    {
        this.itemTurmaProfessorDisciplinaList = itemTurmaProfessorDisciplinaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkProfessor != null ? pkProfessor.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Professor ) )
        {
            return false;
        }
        Professor other = ( Professor ) object;
        if ( ( this.pkProfessor == null && other.pkProfessor != null ) || ( this.pkProfessor != null && !this.pkProfessor.equals( other.pkProfessor ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Professor[ pkProfessor=" + pkProfessor + " ]";
    }
    
}
