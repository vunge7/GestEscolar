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
@Table( name = "disciplina" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Disciplina.findAll", query = "SELECT d FROM Disciplina d" )
    , @NamedQuery( name = "Disciplina.findByPkDisciplina", query = "SELECT d FROM Disciplina d WHERE d.pkDisciplina = :pkDisciplina" )
    , @NamedQuery( name = "Disciplina.findByDesignacao", query = "SELECT d FROM Disciplina d WHERE d.designacao = :designacao" )
} )
public class Disciplina implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_disciplina" )
    private Integer pkDisciplina;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkDisciplina" )
    private List<Nota> notaList;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkDisciplina" )
    private List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaList;

    public Disciplina()
    {
    }

    public Disciplina( Integer pkDisciplina )
    {
        this.pkDisciplina = pkDisciplina;
    }

    public Integer getPkDisciplina()
    {
        return pkDisciplina;
    }

    public void setPkDisciplina( Integer pkDisciplina )
    {
        this.pkDisciplina = pkDisciplina;
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
        hash += ( pkDisciplina != null ? pkDisciplina.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Disciplina ) )
        {
            return false;
        }
        Disciplina other = ( Disciplina ) object;
        if ( ( this.pkDisciplina == null && other.pkDisciplina != null ) || ( this.pkDisciplina != null && !this.pkDisciplina.equals( other.pkDisciplina ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Disciplina[ pkDisciplina=" + pkDisciplina + " ]";
    }
    
}
