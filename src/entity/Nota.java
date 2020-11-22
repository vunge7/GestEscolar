/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mac
 */
@Entity
@Table( name = "nota" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Nota.findAll", query = "SELECT n FROM Nota n" )
    , @NamedQuery( name = "Nota.findByPkNota", query = "SELECT n FROM Nota n WHERE n.pkNota = :pkNota" )
    , @NamedQuery( name = "Nota.findByValor", query = "SELECT n FROM Nota n WHERE n.valor = :valor" )
    , @NamedQuery( name = "Nota.findByData", query = "SELECT n FROM Nota n WHERE n.data = :data" )
    , @NamedQuery( name = "Nota.findByHora", query = "SELECT n FROM Nota n WHERE n.hora = :hora" )
} )
public class Nota implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_nota" )
    private Integer pkNota;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column( name = "valor" )
    private Double valor;
    @Column( name = "data" )
    @Temporal( TemporalType.DATE )
    private Date data;
    @Column( name = "hora" )
    @Temporal( TemporalType.TIME )
    private Date hora;
    @JoinColumn( name = "fk_tipo_nota", referencedColumnName = "pk_tipo_nota" )
    @ManyToOne( optional = false )
    private TipoNota fkTipoNota;
    @JoinColumn( name = "fk_ano_lectivo", referencedColumnName = "pk_ano_lectivo" )
    @ManyToOne( optional = false )
    private AnoLectivo fkAnoLectivo;
    @JoinColumn( name = "fk_disciplina", referencedColumnName = "pk_disciplina" )
    @ManyToOne( optional = false )
    private Disciplina fkDisciplina;
    @JoinColumn( name = "fk_estudante", referencedColumnName = "pk_estudante" )
    @ManyToOne( optional = false )
    private Estudante fkEstudante;
    @JoinColumn( name = "fk_trimestre", referencedColumnName = "pk_semestre" )
    @ManyToOne( optional = false )
    private Trimestre fkTrimestre;
    @JoinColumn( name = "fk_professor", referencedColumnName = "pk_professor" )
    @ManyToOne( optional = false )
    private Professor fkProfessor;

    public Nota()
    {
    }

    public Nota( Integer pkNota )
    {
        this.pkNota = pkNota;
    }

    public Integer getPkNota()
    {
        return pkNota;
    }

    public void setPkNota( Integer pkNota )
    {
        this.pkNota = pkNota;
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

    public TipoNota getFkTipoNota()
    {
        return fkTipoNota;
    }

    public void setFkTipoNota( TipoNota fkTipoNota )
    {
        this.fkTipoNota = fkTipoNota;
    }

    public AnoLectivo getFkAnoLectivo()
    {
        return fkAnoLectivo;
    }

    public void setFkAnoLectivo( AnoLectivo fkAnoLectivo )
    {
        this.fkAnoLectivo = fkAnoLectivo;
    }

    public Disciplina getFkDisciplina()
    {
        return fkDisciplina;
    }

    public void setFkDisciplina( Disciplina fkDisciplina )
    {
        this.fkDisciplina = fkDisciplina;
    }

    public Estudante getFkEstudante()
    {
        return fkEstudante;
    }

    public void setFkEstudante( Estudante fkEstudante )
    {
        this.fkEstudante = fkEstudante;
    }

    public Trimestre getFkTrimestre()
    {
        return fkTrimestre;
    }

    public void setFkTrimestre( Trimestre fkTrimestre )
    {
        this.fkTrimestre = fkTrimestre;
    }

    public Professor getFkProfessor()
    {
        return fkProfessor;
    }

    public void setFkProfessor( Professor fkProfessor )
    {
        this.fkProfessor = fkProfessor;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkNota != null ? pkNota.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Nota ) )
        {
            return false;
        }
        Nota other = ( Nota ) object;
        if ( ( this.pkNota == null && other.pkNota != null ) || ( this.pkNota != null && !this.pkNota.equals( other.pkNota ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Nota[ pkNota=" + pkNota + " ]";
    }
    
}
