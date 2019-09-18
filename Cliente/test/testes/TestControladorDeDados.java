/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import controladores.ControladorDeDados;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Teeu Guima
 */
public class TestControladorDeDados {
    public ControladorDeDados dados;
    
    public TestControladorDeDados() throws IOException, FileNotFoundException, ClassNotFoundException {
        dados = new ControladorDeDados();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void testCadastrarSenha() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        byte[] result = dados.cadastrarSenha("0000");
        byte[] verify = dados.validarSenha("0000");
        
        assertArrayEquals(verify, result);
        
    }
    
}
