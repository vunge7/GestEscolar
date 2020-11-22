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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table( name = "turma" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Turma.findAll", query = "SELECT t FROM Turma t" )
    , @NamedQuery( name = "Turma.findByPkTurma", query = "SELECT t FROM Turma t WHERE t.pkTurma = :pkTurma" )
    , @NamedQuery( name = "Turma.findByDesignacao", query = "SELECT t FROM Turma t WHERE t.designacao = :designacao" )
} )
public class Turma implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_turma" )
    private Integer pkTurma;
    @Column( name = "designacao" )
    private String designacao;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkTurma" )
    private List<ConfirmacaoMatricula> confirmacaoMatriculaList;
    @JoinColumn( name = "fk_ano_lectivo", referencedColumnName = "pk_ano_lectivo" )
    @ManyToOne( optional = false )
    private AnoLectivo fkAnoLectivo;
    @JoinColumn( name = "fk_classe", referencedColumnName = "pk_classe" )
    @ManyToOne( optional = false )
    private Classe fkClasse;
    @JoinColumn( name = "fk_curso", referencedColumnName = "pk_curso" )
    @ManyToOne( optional = false )
    private Curso fkCurso;
    @JoinColumn( name = "fk_sala", referencedColumnName = "pk_sala" )
    @ManyToOne( optional = false )
    private Sala fkSala;
    @JoinColumn( name = "fk_turno", referencedColumnName = "pk_turno" )
    @ManyToOne( optional = false )
    private Turno fkTurno;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkTurma" )
    private List<ItemTurmaProfessorDisciplina> itemTurmaProfessorDisciplinaList;

    public Turma()
    {
    }

    public Turma( Integer pkTurma )
    {
        this.pkTurma = pkTurma;
    }

    public Integer getPkTurma()
    {
        return pkTurma;
    }

    public void setPkTurma( Integer pkTurma )
    {
        this.pkTurma = pkTurma;
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
    public List<ConfirmacaoMatricula> getConfirmacaoMatriculaList()
    {
        return confirmacaoMatriculaList;
    }

    public void setConfirmacaoMatriculaList( List<ConfirmacaoMatricula> confirmacaoMatriculaList )
    {
        this.confirmacaoMatriculaList = confirmacaoMatriculaList;
    }

    public AnoLectivo getFkAnoLectivo()
    {
        return fkAnoLectivo;
    }

    public void setFkAnoLectivo( AnoLectivo fkAnoLectivo )
    {
        this.fkAnoLectivo = fkAnoLectivo;
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

    public Sala getFkSala()
    {
        return fkSala;
    }

    public void setFkSala( Sala fkSala )
    {
        this.fkSala = fkSala;
    }

    public Turno getFkTurno()
    {
        return fkTurno;
    }

    public void setFkTurno( Turno fkTurno )
    {
        this.fkTurno = fkTurno;
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
        hash += ( pkTurma != null ? pkTurma.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Turma ) )
        {
            return false;
        }
        Turma other = ( Turma ) object;
        if ( ( this.pkTurma == null && other.pkTurma != null ) || ( this.pkTurma != null && !this.pkTurma.equals( other.pkTurma ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Turma[ pkTurma=" + pkTurma + " ]";
    }
    
}
