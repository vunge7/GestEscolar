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
import javax.persistence.Lob;
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
@Table( name = "estudante" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Estudante.findAll", query = "SELECT e FROM Estudante e" )
    , @NamedQuery( name = "Estudante.findByPkEstudante", query = "SELECT e FROM Estudante e WHERE e.pkEstudante = :pkEstudante" )
    , @NamedQuery( name = "Estudante.findByNomeCompleto", query = "SELECT e FROM Estudante e WHERE e.nomeCompleto = :nomeCompleto" )
    , @NamedQuery( name = "Estudante.findByDataNascimento", query = "SELECT e FROM Estudante e WHERE e.dataNascimento = :dataNascimento" )
    , @NamedQuery( name = "Estudante.findByTelefone", query = "SELECT e FROM Estudante e WHERE e.telefone = :telefone" )
    , @NamedQuery( name = "Estudante.findByEmail", query = "SELECT e FROM Estudante e WHERE e.email = :email" )
    , @NamedQuery( name = "Estudante.findByEndereco", query = "SELECT e FROM Estudante e WHERE e.endereco = :endereco" )
    , @NamedQuery( name = "Estudante.findByNbi", query = "SELECT e FROM Estudante e WHERE e.nbi = :nbi" )
    , @NamedQuery( name = "Estudante.findByNomePai", query = "SELECT e FROM Estudante e WHERE e.nomePai = :nomePai" )
    , @NamedQuery( name = "Estudante.findByNomeMae", query = "SELECT e FROM Estudante e WHERE e.nomeMae = :nomeMae" )
    , @NamedQuery( name = "Estudante.findByDataValidadeBI", query = "SELECT e FROM Estudante e WHERE e.dataValidadeBI = :dataValidadeBI" )
    , @NamedQuery( name = "Estudante.findByNaturalidade", query = "SELECT e FROM Estudante e WHERE e.naturalidade = :naturalidade" )
    , @NamedQuery( name = "Estudante.findByDoenca", query = "SELECT e FROM Estudante e WHERE e.doenca = :doenca" )
    , @NamedQuery( name = "Estudante.findByNumeroMatricula", query = "SELECT e FROM Estudante e WHERE e.numeroMatricula = :numeroMatricula" )
} )
public class Estudante implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "pk_estudante" )
    private Integer pkEstudante;
    @Column( name = "nome_completo" )
    private String nomeCompleto;
    @Column( name = "data_nascimento" )
    @Temporal( TemporalType.DATE )
    private Date dataNascimento;
    @Column( name = "telefone" )
    private String telefone;
    @Column( name = "email" )
    private String email;
    @Column( name = "endereco" )
    private String endereco;
    @Column( name = "nbi" )
    private String nbi;
    @Lob
    @Column( name = "foto" )
    private byte[] foto;
    @Column( name = "nomePai" )
    private String nomePai;
    @Column( name = "nomeMae" )
    private String nomeMae;
    @Column( name = "dataValidadeBI" )
    @Temporal( TemporalType.DATE )
    private Date dataValidadeBI;
    @Column( name = "naturalidade" )
    private String naturalidade;
    @Column( name = "doenca" )
    private String doenca;
    @Column( name = "numero_matricula" )
    private String numeroMatricula;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkEstudante" )
    private List<Nota> notaList;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkEstudante" )
    private List<ReciboPropina> reciboPropinaList;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkEstudante" )
    private List<ConfirmacaoMatricula> confirmacaoMatriculaList;
    @JoinColumn( name = "fk_encarregado", referencedColumnName = "pk_encarregado" )
    @ManyToOne( optional = false )
    private Encarregado fkEncarregado;
    @JoinColumn( name = "fk_municipio", referencedColumnName = "pkmunicipio" )
    @ManyToOne( optional = false )
    private Municipio fkMunicipio;
    @JoinColumn( name = "fk_sexo", referencedColumnName = "pk_sexo" )
    @ManyToOne( optional = false )
    private Sexo fkSexo;

    public Estudante()
    {
    }

    public Estudante( Integer pkEstudante )
    {
        this.pkEstudante = pkEstudante;
    }

    public Integer getPkEstudante()
    {
        return pkEstudante;
    }

    public void setPkEstudante( Integer pkEstudante )
    {
        this.pkEstudante = pkEstudante;
    }

    public String getNomeCompleto()
    {
        return nomeCompleto;
    }

    public void setNomeCompleto( String nomeCompleto )
    {
        this.nomeCompleto = nomeCompleto;
    }

    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento( Date dataNascimento )
    {
        this.dataNascimento = dataNascimento;
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

    public String getNbi()
    {
        return nbi;
    }

    public void setNbi( String nbi )
    {
        this.nbi = nbi;
    }

    public byte[] getFoto()
    {
        return foto;
    }

    public void setFoto( byte[] foto )
    {
        this.foto = foto;
    }

    public String getNomePai()
    {
        return nomePai;
    }

    public void setNomePai( String nomePai )
    {
        this.nomePai = nomePai;
    }

    public String getNomeMae()
    {
        return nomeMae;
    }

    public void setNomeMae( String nomeMae )
    {
        this.nomeMae = nomeMae;
    }

    public Date getDataValidadeBI()
    {
        return dataValidadeBI;
    }

    public void setDataValidadeBI( Date dataValidadeBI )
    {
        this.dataValidadeBI = dataValidadeBI;
    }

    public String getNaturalidade()
    {
        return naturalidade;
    }

    public void setNaturalidade( String naturalidade )
    {
        this.naturalidade = naturalidade;
    }

    public String getDoenca()
    {
        return doenca;
    }

    public void setDoenca( String doenca )
    {
        this.doenca = doenca;
    }

    public String getNumeroMatricula()
    {
        return numeroMatricula;
    }

    public void setNumeroMatricula( String numeroMatricula )
    {
        this.numeroMatricula = numeroMatricula;
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

    public Encarregado getFkEncarregado()
    {
        return fkEncarregado;
    }

    public void setFkEncarregado( Encarregado fkEncarregado )
    {
        this.fkEncarregado = fkEncarregado;
    }

    public Municipio getFkMunicipio()
    {
        return fkMunicipio;
    }

    public void setFkMunicipio( Municipio fkMunicipio )
    {
        this.fkMunicipio = fkMunicipio;
    }

    public Sexo getFkSexo()
    {
        return fkSexo;
    }

    public void setFkSexo( Sexo fkSexo )
    {
        this.fkSexo = fkSexo;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( pkEstudante != null ? pkEstudante.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Estudante ) )
        {
            return false;
        }
        Estudante other = ( Estudante ) object;
        if ( ( this.pkEstudante == null && other.pkEstudante != null ) || ( this.pkEstudante != null && !this.pkEstudante.equals( other.pkEstudante ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Estudante[ pkEstudante=" + pkEstudante + " ]";
    }
    
}
