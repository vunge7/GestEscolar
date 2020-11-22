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
@Table( name = "item_turma_professor_disciplina" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "ItemTurmaProfessorDisciplina.findAll", query = "SELECT i FROM ItemTurmaProfessorDisciplina i" )
    , @NamedQuery( name = "ItemTurmaProfessorDisciplina.findByPkProfessorDisciplina", query = "SELECT i FROM ItemTurmaProfessorDisciplina i WHERE i.pkProfessorDisciplina = :pkProfessorDisciplina" )
} )
public class ItemTurmaProfessorDisciplina implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_professor_disciplina" )
    private Integer pkProfessorDisciplina;
    @JoinColumn( name = "fk_disciplina", referencedColumnName = "pk_disciplina" )
    @ManyToOne( optional = false )
    private Disciplina fkDisciplina;
    @JoinColumn( name = "fk_professor", referencedColumnName = "pk_professor" )
    @ManyToOne( optional = false )
    private Professor fkProfessor;
    @JoinColumn( name = "fk_turma", referencedColumnName = "pk_turma" )
    @ManyToOne( optional = false )
    private Turma fkTurma;

    public ItemTurmaProfessorDisciplina()
    {
    }

    public ItemTurmaProfessorDisciplina( Integer pkProfessorDisciplina )
    {
        this.pkProfessorDisciplina = pkProfessorDisciplina;
    }

    public Integer getPkProfessorDisciplina()
    {
        return pkProfessorDisciplina;
    }

    public void setPkProfessorDisciplina( Integer pkProfessorDisciplina )
    {
        this.pkProfessorDisciplina = pkProfessorDisciplina;
    }

    public Disciplina getFkDisciplina()
    {
        return fkDisciplina;
    }

    public void setFkDisciplina( Disciplina fkDisciplina )
    {
        this.fkDisciplina = fkDisciplina;
    }

    public Professor getFkProfessor()
    {
        return fkProfessor;
    }

    public void setFkProfessor( Professor fkProfessor )
    {
        this.fkProfessor = fkProfessor;
    }

    public Turma getFkTurma()
    {
        return fkTurma;
    }

    public void setFkTurma( Turma fkTurma )
    {
        this.fkTurma = fkTurma;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkProfessorDisciplina != null ? pkProfessorDisciplina.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof ItemTurmaProfessorDisciplina ) )
        {
            return false;
        }
        ItemTurmaProfessorDisciplina other = ( ItemTurmaProfessorDisciplina ) object;
        if ( ( this.pkProfessorDisciplina == null && other.pkProfessorDisciplina != null ) || ( this.pkProfessorDisciplina != null && !this.pkProfessorDisciplina.equals( other.pkProfessorDisciplina ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.ItemTurmaProfessorDisciplina[ pkProfessorDisciplina=" + pkProfessorDisciplina + " ]";
    }
    
}
