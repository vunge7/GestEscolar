/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.util;

/**
 *
 * @author Domingos Dala Vunge
 */
public class MiniPautaUtil
{
    private String nome_completo, sexo;
    private double mac_1, cpp_1;
    private double mac_2, cpp_2;
    private double mac_3, cpp_3;
    private double cap, cpe_ce;

    public MiniPautaUtil()
    {
    }

    public String getNome_completo()
    {
        return nome_completo;
    }

    public void setNome_completo( String nome_completo )
    {
        this.nome_completo = nome_completo;
    }

    public String getSexo()
    {
        return sexo;
    }

    public double getCap()
    {
        return cap;
    }

    public void setCap( double cap )
    {
        this.cap = cap;
    }

    public double getCpe_ce()
    {
        return cpe_ce;
    }

    public void setCpe_ce( double cpe_ce )
    {
        this.cpe_ce = cpe_ce;
    }

    public void setSexo( String sexo )
    {
        this.sexo = sexo;
    }

    public double getMac_1()
    {
        return mac_1;
    }

    public void setMac_1( double mac_1 )
    {
        this.mac_1 = mac_1;
    }

    public double getCpp_1()
    {
        return cpp_1;
    }

    public void setCpp_1( double cpp_1 )
    {
        this.cpp_1 = cpp_1;
    }

    public double getMac_2()
    {
        return mac_2;
    }

    public void setMac_2( double mac_2 )
    {
        this.mac_2 = mac_2;
    }

    public double getCpp_2()
    {
        return cpp_2;
    }

    public void setCpp_2( double cpp_2 )
    {
        this.cpp_2 = cpp_2;
    }

    public double getMac_3()
    {
        return mac_3;
    }

    public void setMac_3( double mac_3 )
    {
        this.mac_3 = mac_3;
    }

    public double getCpp_3()
    {
        return cpp_3;
    }

    public void setCpp_3( double cpp_3 )
    {
        this.cpp_3 = cpp_3;
    }
    
    public static void main( String[] args )
    {
        new MiniPautaUtil();
    }
    
}
